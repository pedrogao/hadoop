
package org.apache.hadoop.mapred;

import org.apache.hadoop.conf.*;

import java.io.*;

/** Runs a map task. */
class MapTaskRunner extends TaskRunner {
  private MapOutputFile mapOutputFile;

  public MapTaskRunner(Task task, TaskTracker tracker, Configuration conf) {
    super(task, tracker, conf);
    this.mapOutputFile = new MapOutputFile();
    this.mapOutputFile.setConf(conf);
  }
  
  /** Delete any temporary files from previous failed attempts. */
  public boolean prepare() throws IOException {
    this.mapOutputFile.removeAll(getTask().getTaskId());
    return true;
  }

  /** Delete all of the temporary map output files. */
  public void close() throws IOException {
    LOG.info(getTask()+" done; removing files.");
    this.mapOutputFile.removeAll(getTask().getTaskId());
  }
}
