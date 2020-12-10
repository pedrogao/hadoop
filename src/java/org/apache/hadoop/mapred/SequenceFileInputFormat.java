

package org.apache.hadoop.mapred;

import java.io.IOException;
import java.io.File;

import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.MapFile;

/** An {@link InputFormat} for {@link SequenceFile}s. */
public class SequenceFileInputFormat extends InputFormatBase {

  public SequenceFileInputFormat() {
    setMinSplitSize(SequenceFile.SYNC_INTERVAL);
  }

  protected File[] listFiles(FileSystem fs, JobConf job)
    throws IOException {

    File[] files = super.listFiles(fs, job);
    for (int i = 0; i < files.length; i++) {
      File file = files[i];
      if (file.isDirectory()) {                   // it's a MapFile
        files[i] = new File(file, MapFile.DATA_FILE_NAME); // use the data file
      }
    }
    return files;
  }

  public RecordReader getRecordReader(FileSystem fs, FileSplit split,
                                      JobConf job, Reporter reporter)
    throws IOException {

    reporter.setStatus(split.toString());

    return new SequenceFileRecordReader(job, split);
  }

}

