

package org.apache.hadoop.mapred;

import java.io.IOException;

import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.conf.Configuration;

/** An {@link RecordReader} for {@link SequenceFile}s. */
public class SequenceFileRecordReader implements RecordReader {
  private SequenceFile.Reader in;
  private long end;
  private boolean more = true;

  public SequenceFileRecordReader(Configuration conf, FileSplit split)
    throws IOException {
    FileSystem fs = FileSystem.get(conf);
    this.in = new SequenceFile.Reader(fs, split.getFile().toString(), conf);
    this.end = split.getStart() + split.getLength();

    if (split.getStart() > in.getPosition())
      in.sync(split.getStart());                  // sync to start

    more = in.getPosition() < end;
  }


  /** The class of key that must be passed to {@link
   * #next(Writable,Writable)}.. */
  public Class getKeyClass() { return in.getKeyClass(); }

  /** The class of value that must be passed to {@link
   * #next(Writable,Writable)}.. */
  public Class getValueClass() { return in.getValueClass(); }
  
  public synchronized boolean next(Writable key, Writable value)
    throws IOException {
    if (!more) return false;
    long pos = in.getPosition();
    boolean eof = in.next(key, value);
    if (pos >= end && in.syncSeen()) {
      more = false;
    } else {
      more = eof;
    }
    return more;
  }
  
  public synchronized long getPos() throws IOException {
    return in.getPosition();
  }
  
  public synchronized void close() throws IOException { in.close(); }
  
}

