

package org.apache.hadoop.mapred;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/** Partitions the key space.  A partition is created for each reduce task. */
public interface Partitioner extends JobConfigurable {
  /** Returns the paritition number for a given entry given the total number of
   * partitions.  Typically a hash function on a all or a subset of the key.
   *
   * @param key the entry key
   * @param value the entry value
   * @param numPartitions the number of partitions
   * @return the partition number
   */
  int getPartition(WritableComparable key, Writable value, int numPartitions);
}
