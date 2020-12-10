

package org.apache.hadoop.mapred.lib;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.MapReduceBase;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.LongWritable;

/** A {@link Reducer} that sums long values. */
public class LongSumReducer extends MapReduceBase implements Reducer {

  public void reduce(WritableComparable key, Iterator values,
                     OutputCollector output, Reporter reporter)
    throws IOException {

    // sum all values for this key
    long sum = 0;
    while (values.hasNext()) {
      sum += ((LongWritable)values.next()).get();
    }

    // output sum
    output.collect(key, new LongWritable(sum));
  }

}
