

package org.apache.hadoop.mapred;

import java.io.IOException;
import java.io.File;
import java.io.DataInput;
import java.io.DataOutput;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.UTF8;
import org.apache.hadoop.fs.FileSystem;

/** A section of an input file.  Returned by {@link
 * InputFormat#getSplits(FileSystem, JobConf, int)} and passed to
 * InputFormat#getRecordReader(FileSystem,FileSplit,JobConf,Reporter). */
public class FileSplit implements Writable {
  private File file;
  private long start;
  private long length;
  
  FileSplit() {}

  /** Constructs a split.
   *
   * @param file the file name
   * @param start the position of the first byte in the file to process
   * @param length the number of bytes in the file to process
   */
  public FileSplit(File file, long start, long length) {
    this.file = file;
    this.start = start;
    this.length = length;
  }
  
  /** The file containing this split's data. */
  public File getFile() { return file; }
  
  /** The position of the first byte in the file to process. */
  public long getStart() { return start; }
  
  /** The number of bytes in the file to process. */
  public long getLength() { return length; }

  public String toString() { return file + ":" + start + "+" + length; }

  ////////////////////////////////////////////
  // Writable methods
  ////////////////////////////////////////////

  public void write(DataOutput out) throws IOException {
    UTF8.writeString(out, file.toString());
    out.writeLong(start);
    out.writeLong(length);
  }
  public void readFields(DataInput in) throws IOException {
    file = new File(UTF8.readString(in));
    start = in.readLong();
    length = in.readLong();
  }


}
