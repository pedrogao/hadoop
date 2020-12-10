

package org.apache.hadoop.mapred;

import java.io.*;

/** 
 * Includes details on a running MapReduce job.  A client can
 * track a living job using this object.
 *
 * @author Mike Cafarella
 */
public interface RunningJob {
    /**
     * Returns an identifier for the job
     */
    public String getJobID();

    /**
     * Returns the path of the submitted job.
     */
    public String getJobFile();

    /**
     * Returns a URL where some job progress information will be displayed.
     */
    public String getTrackingURL();

    /**
     * Returns a float between 0.0 and 1.0, indicating progress on
     * the map portion of the job.  When all map tasks have completed,
     * the function returns 1.0.
     */
    public float mapProgress() throws IOException;

    /**
     * Returns a float between 0.0 and 1.0, indicating progress on
     * the reduce portion of the job.  When all reduce tasks have completed,
     * the function returns 1.0.
     */
    public float reduceProgress() throws IOException;

    /**
     * Non-blocking function to check whether the job is finished or not.
     */
    public boolean isComplete() throws IOException;

    /**
     * True iff job completed successfully.
     */
    public boolean isSuccessful() throws IOException;

    /**
     * Blocks until the job is complete.
     */
    public void waitForCompletion() throws IOException;

    /**
     * Kill the running job.  Blocks until all job tasks have been
     * killed as well.  If the job is no longer running, it simply returns.
     */
    public void killJob() throws IOException;
}
