
package org.apache.hadoop.mapred;

import org.apache.hadoop.io.*;

import java.io.*;
import java.net.*;

/**************************************************
 * A JobProfile is a MapReduce primitive.  Tracks a job,
 * whether living or dead.
 *
 * @author Mike Cafarella
 **************************************************/
class JobProfile implements Writable {

    static {                                      // register a ctor
      WritableFactories.setFactory
        (JobProfile.class,
         new WritableFactory() {
           public Writable newInstance() { return new JobProfile(); }
         });
    }

    String user;
    String jobid;
    String jobFile;
    String url;
    String name;

    /**
     */
    public JobProfile() {
    }

    /**
     */
    public JobProfile(String user, String jobid, String jobFile, String url,
                      String name) {
        this.user = user;
        this.jobid = jobid;
        this.jobFile = jobFile;
        this.url = url;
        this.name = name;
    }

    /**
     * Get the user id.
     */
    public String getUser() {
      return user;
    }
    
    /**
     */
    public String getJobId() {
        return jobid;
    }

    /**
     */
    public String getJobFile() {
        return jobFile;
    }


    /**
     */
    public URL getURL() {
        try {
            return new URL(url.toString());
        } catch (IOException ie) {
            return null;
        }
    }

    /**
     * Get the user-specified job name.
     */
    public String getJobName() {
      return name;
    }
    
    ///////////////////////////////////////
    // Writable
    ///////////////////////////////////////
    public void write(DataOutput out) throws IOException {
        UTF8.writeString(out, jobid);
        UTF8.writeString(out, jobFile);
        UTF8.writeString(out, url);
        UTF8.writeString(out, user);
        UTF8.writeString(out, name);
    }
    public void readFields(DataInput in) throws IOException {
        this.jobid = UTF8.readString(in);
        this.jobFile = UTF8.readString(in);
        this.url = UTF8.readString(in);
        this.user = UTF8.readString(in);
        this.name = UTF8.readString(in);
    }
}


