

package org.apache.hadoop.mapred;

import java.io.IOException;

/** Expert: Permits greater control of map processing. For example,
 * implementations might perform multi-threaded, asynchronous mappings. */
public interface MapRunnable extends JobConfigurable {
  /** Called to execute mapping.  Mapping is complete when this returns.
   * @param input the {@link RecordReader} with input key/value pairs.
   * @param output the {@link OutputCollector} for mapped key/value pairs.
   */
  void run(RecordReader input, OutputCollector output, Reporter reporter)
    throws IOException;
}
