package org.apache.hadoop.io;

import java.io.DataOutput;
import java.io.DataInput;
import java.io.IOException;

/**
 * A simple, efficient, serialization protocol, based on {@link DataInput} and
 * {@link DataOutput}.
 * <p>
 * 简单、高效的序列化协议
 *
 * <p>Implementations typically implement a static <code>read(DataInput)</code>
 * method which constructs a new instance, calls {@link
 * #readFields(DataInput)}, and returns the instance.
 *
 * @author Doug Cutting
 */
public interface Writable {
  /**
   * Writes the fields of this object to <code>out</code>.
   * 写
   */
  void write(DataOutput out) throws IOException;

  /**
   * Reads the fields of this object from <code>in</code>.  For efficiency,
   * implementations should attempt to re-use storage in the existing object
   * where possible.
   * 读
   */
  void readFields(DataInput in) throws IOException;
}
