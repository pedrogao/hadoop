

package org.apache.hadoop.mapred;

import java.io.*;

/** 
 * Protocol that a TaskTracker and the central JobTracker use to communicate.
 * The JobTracker is the Server, which implements this protocol.
 */ 
interface InterTrackerProtocol {
  public final static int TRACKERS_OK = 0;
  public final static int UNKNOWN_TASKTRACKER = 1;

  /** 
   * Called regularly by the task tracker to update the status of its tasks
   * within the job tracker.  JobTracker responds with a code that tells the 
   * TaskTracker whether all is well.
   *
   * TaskTracker must also indicate whether this is the first interaction
   * (since state refresh)
   */
  int emitHeartbeat(TaskTrackerStatus status, boolean initialContact);

  /** Called to get new tasks from from the job tracker for this tracker.*/
  Task pollForNewTask(String trackerName);

  /** Called to find which tasks that have been run by this tracker should now
   * be closed because their job is complete.  This is used to, e.g., 
   * notify a map task that its output is no longer needed and may 
   * be removed. */
  String pollForTaskWithClosedJob(String trackerName);

  /** Called by a reduce task to find which map tasks are completed.
   *
   * @param taskId the reduce task id
   * @param mapTasksNeeded an array of UTF8 naming map task ids whose output is needed.
   * @return an array of MapOutputLocation
   */
  MapOutputLocation[] locateMapOutputs(String taskId, String[][] mapTasksNeeded);

  /**
   * The task tracker calls this once, to discern where it can find
   * files referred to by the JobTracker
   */
  public String getFilesystemName() throws IOException;
}


