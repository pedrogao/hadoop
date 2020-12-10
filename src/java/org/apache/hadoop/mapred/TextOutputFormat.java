

package org.apache.hadoop.mapred;

import java.io.IOException;
import java.io.File;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FSDataOutputStream;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Writable;

/** An {@link OutputFormat} that writes plain text files. */
public class TextOutputFormat extends OutputFormatBase {

  public RecordWriter getRecordWriter(FileSystem fs, JobConf job,
                                      String name) throws IOException {

    File file = new File(job.getOutputDir(), name);

    final FSDataOutputStream out = fs.create(file);

    return new RecordWriter() {
        public synchronized void write(WritableComparable key, Writable value)
          throws IOException {
          out.write(key.toString().getBytes("UTF-8"));
          out.writeByte('\t');
          out.write(value.toString().getBytes("UTF-8"));
          out.writeByte('\n');
        }
        public synchronized void close(Reporter reporter) throws IOException {
          out.close();
        }
      };
  }      
}

