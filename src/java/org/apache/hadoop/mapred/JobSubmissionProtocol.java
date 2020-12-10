

package org.apache.hadoop.mapred;

import java.io.*;

/** 
 * Protocol that a JobClient and the central JobTracker use to communicate.  The
 * JobClient can use these methods to submit a Job for execution, and learn about
 * the current system status.
 */ 
interface JobSubmissionProtocol {
    /**
     * Submit a Job for execution.  Returns the latest profile for
     * that job.
     */
    public JobStatus submitJob(String jobFile) throws IOException;

    /**
     * Get the current status of the cluster
     * @return summary of the state of the cluster
     */
    public ClusterStatus getClusterStatus();
    
    /**
     * Kill the indicated job
     */
    public void killJob(String jobid);

    /**
     * Grab a handle to a job that is already known to the JobTracker
     */
    public JobProfile getJobProfile(String jobid);

    /**
     * Grab a handle to a job that is already known to the JobTracker
     */
    public JobStatus getJobStatus(String jobid);

    /**
     * Grab a bunch of info on the tasks that make up the job
     */
    public TaskReport[] getMapTaskReports(String jobid);
    public TaskReport[] getReduceTaskReports(String jobid);

    /**
     * A MapReduce system always operates on a single filesystem.  This 
     * function returns the fs name.  ('local' if the localfs; 'addr:port' 
     * if dfs).  The client can then copy files into the right locations 
     * prior to submitting the job.
     */
    public String getFilesystemName() throws IOException;
}
