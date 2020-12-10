
package org.apache.hadoop.mapred;

import org.apache.hadoop.io.*;

import java.io.*;

/** A report on the state of a task. */
public class TaskReport implements Writable {
  private String taskid;
  private float progress;
  private String state;
  private String[] diagnostics;

  public TaskReport() {}

  TaskReport(String taskid, float progress, String state,
             String[] diagnostics) {
    this.taskid = taskid;
    this.progress = progress;
    this.state = state;
    this.diagnostics = diagnostics;
  }
    
  /** The id of the task. */
  public String getTaskId() { return taskid; }
  /** The amount completed, between zero and one. */
  public float getProgress() { return progress; }
  /** The most recent state, reported by a {@link Reporter}. */
  public String getState() { return state; }
  /** A list of error messages. */
  public String[] getDiagnostics() { return diagnostics; }

  //////////////////////////////////////////////
  // Writable
  //////////////////////////////////////////////
  public void write(DataOutput out) throws IOException {
    UTF8.writeString(out, taskid);
    out.writeFloat(progress);
    UTF8.writeString(out, state);
    new ObjectWritable(diagnostics).write(out);
  }

  public void readFields(DataInput in) throws IOException {
    this.taskid = UTF8.readString(in);
    this.progress = in.readFloat();
    this.state = UTF8.readString(in);

    ObjectWritable wrapper = new ObjectWritable();
    wrapper.readFields(in);
    diagnostics = (String[])wrapper.get();
  }
}
