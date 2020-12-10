

package org.apache.hadoop.mapred;

import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;


/** Passed to {@link Mapper} and {@link Reducer} implementations to collect
 * output data. */
public interface OutputCollector {
  /** Adds a key/value pair to the output.
   *
   * @param key the key to add
   * @param value to value to add
   */
  void collect(WritableComparable key, Writable value) throws IOException;
}
