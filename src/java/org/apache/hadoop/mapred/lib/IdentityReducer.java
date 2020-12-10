package org.apache.hadoop.mapred.lib;

import java.io.IOException;

import java.util.Iterator;

import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.MapReduceBase;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/**
 * Performs no reduction, writing all input values directly to the output.
 */
public class IdentityReducer extends MapReduceBase implements Reducer {

  /**
   * Writes all keys and values directly to output.
   */
  public void reduce(WritableComparable key, Iterator values,
                     OutputCollector output, Reporter reporter)
    throws IOException {
    while (values.hasNext()) {
      output.collect(key, (Writable) values.next());
    }
  }

}
