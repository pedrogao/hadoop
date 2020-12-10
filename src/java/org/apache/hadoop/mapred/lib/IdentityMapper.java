package org.apache.hadoop.mapred.lib;

import java.io.IOException;

import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.MapReduceBase;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/**
 * Implements the identity function, mapping inputs directly to outputs.
 */
public class IdentityMapper extends MapReduceBase implements Mapper {

  /**
   * The identify function.  Input key/value pair is written directly to
   * output.
   */
  public void map(WritableComparable key, Writable val,
                  OutputCollector output, Reporter reporter)
    throws IOException {
    output.collect(key, val);
  }
}
