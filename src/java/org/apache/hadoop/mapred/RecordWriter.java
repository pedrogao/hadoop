

package org.apache.hadoop.mapred;

import java.io.IOException;
import java.io.DataOutput;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Writable;

/** Writes key/value pairs to an output file.  Implemented by {@link
 * OutputFormat} implementations. */
public interface RecordWriter {
  /** Writes a key/value pair.
   *
   * @param key the key to write
   * @param value the value to write
   *
   * @see Writable#write(DataOutput)
   */      
  void write(WritableComparable key, Writable value) throws IOException;

  /** Close this to future operations.*/ 
  void close(Reporter reporter) throws IOException;
}
