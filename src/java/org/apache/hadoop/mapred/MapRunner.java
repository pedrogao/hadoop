

package org.apache.hadoop.mapred;

import java.io.IOException;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

/** Default {@link MapRunnable} implementation.*/
public class MapRunner implements MapRunnable {
  private JobConf job;
  private Mapper mapper;
  private Class inputKeyClass;
  private Class inputValueClass;

  public void configure(JobConf job) {
    this.job = job;
    this.mapper = (Mapper)job.newInstance(job.getMapperClass());
    this.inputKeyClass = job.getInputKeyClass();
    this.inputValueClass = job.getInputValueClass();
  }

  public void run(RecordReader input, OutputCollector output,
                  Reporter reporter)
    throws IOException {
    try {
      // allocate key & value instances that are re-used for all entries
      WritableComparable key =
        (WritableComparable)job.newInstance(inputKeyClass);
      Writable value = (Writable)job.newInstance(inputValueClass);
      while (input.next(key, value)) {
        // map pair to output
        mapper.map(key, value, output, reporter);
      }
    } finally {
        mapper.close();
    }
  }

}
