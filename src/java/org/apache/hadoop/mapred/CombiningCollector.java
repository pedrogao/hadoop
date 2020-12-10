

package org.apache.hadoop.mapred;

import java.io.*;
import java.util.*;

import org.apache.hadoop.io.*;

/** Implements partial value reduction during mapping.  This can minimize the
 * size of intermediate data.  Buffers a list of values for each unique key,
 * then invokes the combiner's reduce method to merge some values before
 * they're transferred to a reduce node. */
class CombiningCollector implements OutputCollector {
  private int limit;

  private int count = 0;
  private Map keyToValues;                        // the buffer

  private JobConf job;
  private OutputCollector out;
  private Reducer combiner;
  private Reporter reporter;

  public CombiningCollector(JobConf job, OutputCollector out,
                            Reporter reporter) {
    this.job = job;
    this.out = out;
    this.reporter = reporter;
    this.combiner = (Reducer)job.newInstance(job.getCombinerClass());
    this.keyToValues = new TreeMap(job.getOutputKeyComparator());
    this.limit = job.getInt("mapred.combine.buffer.size", 100000);
  }

  public synchronized void collect(WritableComparable key, Writable value)
    throws IOException {

    // buffer new value in map
    ArrayList values = (ArrayList)keyToValues.get(key);
    Writable valueClone = WritableUtils.clone(value, job);
    if (values == null) {
      // this is a new key, so create a new list
      values = new ArrayList(1);
      values.add(valueClone);
      Writable keyClone = WritableUtils.clone(key, job);
      keyToValues.put(keyClone, values);
    } else {
      // other values for this key, so just add.
      values.add(valueClone);
    }

    count++;

    if (count >= this.limit) {                         // time to flush
      flush();
    }
  }

  public synchronized void flush() throws IOException {
    Iterator pairs = keyToValues.entrySet().iterator();
    while (pairs.hasNext()) {
      Map.Entry pair = (Map.Entry)pairs.next();
      combiner.reduce((WritableComparable)pair.getKey(),
                      ((ArrayList)pair.getValue()).iterator(),
                      out, reporter);
    }
    keyToValues.clear();
    count = 0;
  }
  
  public synchronized void close() throws IOException { 
    combiner.close(); 
  }

}
