

package org.apache.hadoop.mapred;

import java.io.IOException;

/** Protocol that task child process uses to contact its parent process.  The
 * parent is a daemon which which polls the central master for a new map or
 * reduce task and runs it as a child process.  All communication between child
 * and parent is via this protocol. */ 
interface TaskUmbilicalProtocol {

  /** Called when a child task process starts, to get its task.*/
  Task getTask(String taskid) throws IOException;

  /** Report child's progress to parent.
   * @param taskid the id of the task
   * @param progress value between zero and one
   * @param state description of task's current state
   */
  void progress(String taskid, float progress, String state)
    throws IOException;

  /** Report error messages back to parent.  Calls should be sparing, since all
   *  such messages are held in the job tracker.
   *  @param taskid the id of the task involved
   *  @param trace the text to report
   */
  void reportDiagnosticInfo(String taskid, String trace) throws IOException;

  /** Periodically called by child to check if parent is still alive. */
  void ping(String taskid) throws IOException;

  /** Report that the task is successfully completed.  Failure is assumed if
   * the task process exits without calling this. */
  void done(String taskid) throws IOException;

  /** Report that the task encounted a local filesystem error.*/
  void fsError(String message) throws IOException;

}
