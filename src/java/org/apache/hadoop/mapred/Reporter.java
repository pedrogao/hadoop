

package org.apache.hadoop.mapred;

import java.io.IOException;

/** Passed to application code to permit alteration of status. */
public interface Reporter {
  /** Alter the application's status description.
   *
   * @param status a brief description of the current status
   */
  void setStatus(String status) throws IOException;
}
