

package org.apache.hadoop.mapred;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;

/** An output data format.  Output files are stored in a {@link
 * FileSystem}. */
public interface OutputFormat {

  /** Construct a {@link RecordWriter}.
   *
   * @param fs the file system to write to
   * @param job the job whose output is being written
   * @param name the unique name for this part of the output
   * @return a {@link RecordWriter}
   */
  RecordWriter getRecordWriter(FileSystem fs, JobConf job, String name)
    throws IOException;

  /** Check whether the output specification for a job is appropriate.  Called
   * when a job is submitted.  Typically checks that it does not already exist,
   * throwing an exception when it already exists, so that output is not
   * overwritten.
   *
   * @param job the job whose output will be written
   * @throws IOException when output should not be attempted
   */
  void checkOutputSpecs(FileSystem fs, JobConf job) throws IOException;
}

