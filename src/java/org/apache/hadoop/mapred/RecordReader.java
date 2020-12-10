

package org.apache.hadoop.mapred;

import java.io.IOException;
import java.io.DataInput;

import org.apache.hadoop.io.Writable;

/** Reads key/value pairs from an input file {@link FileSplit}.
 * Implemented by {@link InputFormat} implementations. */
public interface RecordReader {
  /** Reads the next key/value pair.
   *
   * @param key the key to read data into
   * @param value the value to read data into
   * @return true iff a key/value was read, false if at EOF
   *
   * @see Writable#readFields(DataInput)
   */      
  boolean next(Writable key, Writable value) throws IOException;

  /** Returns the current position in the input. */
  long getPos() throws IOException;

  /** Close this to future operations.*/ 
  public void close() throws IOException;

}
