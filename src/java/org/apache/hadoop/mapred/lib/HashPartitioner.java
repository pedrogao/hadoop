package org.apache.hadoop.mapred.lib;

import org.apache.hadoop.mapred.Partitioner;
import org.apache.hadoop.mapred.JobConf;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Writable;

/**
 * Partition keys by their {@link Object#hashCode()}.
 */
public class HashPartitioner implements Partitioner {

  public void configure(JobConf job) {
  }

  /**
   * Use {@link Object#hashCode()} to partition.
   */
  public int getPartition(WritableComparable key, Writable value,
                          int numReduceTasks) {
    return (key.hashCode() & Integer.MAX_VALUE) % numReduceTasks;
  }

}
