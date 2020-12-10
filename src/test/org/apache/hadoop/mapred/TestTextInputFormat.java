

package org.apache.hadoop.mapred;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;
import java.util.logging.*;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.conf.*;

public class TestTextInputFormat extends TestCase {
  private static final Logger LOG = InputFormatBase.LOG;

  private static int MAX_LENGTH = 10000;
  private static Configuration conf = new Configuration();
  
  public void testFormat() throws Exception {
    JobConf job = new JobConf(conf);
    FileSystem fs = FileSystem.getNamed("local", conf);
    File dir = new File(System.getProperty("test.build.data",".") + "/mapred");
    File file = new File(dir, "test.txt");

    Reporter reporter = new Reporter() {
        public void setStatus(String status) throws IOException {}
      };
    
    int seed = new Random().nextInt();
    //LOG.info("seed = "+seed);
    Random random = new Random(seed);

    fs.delete(dir);
    job.setInputDir(dir);

    // for a variety of lengths
    for (int length = 0; length < MAX_LENGTH;
         length+= random.nextInt(MAX_LENGTH/10)+1) {

      //LOG.info("creating; entries = " + length);

      // create a file with length entries
      Writer writer = new OutputStreamWriter(fs.create(file));
      try {
        for (int i = 0; i < length; i++) {
          writer.write(Integer.toString(i));
          writer.write("\n");
        }
      } finally {
        writer.close();
      }

      // try splitting the file in a variety of sizes
      InputFormat format = new TextInputFormat();
      LongWritable key = new LongWritable();
      UTF8 value = new UTF8();
      for (int i = 0; i < 3; i++) {
        int numSplits = random.nextInt(MAX_LENGTH/20)+1;
        //LOG.info("splitting: requesting = " + numSplits);
        FileSplit[] splits = format.getSplits(fs, job, numSplits);
        //LOG.info("splitting: got =        " + splits.length);

        // check each split
        BitSet bits = new BitSet(length);
        for (int j = 0; j < splits.length; j++) {
          RecordReader reader =
            format.getRecordReader(fs, splits[j], job, reporter);
          try {
            int count = 0;
            while (reader.next(key, value)) {
              int v = Integer.parseInt(value.toString());
              //             if (bits.get(v)) {
              //               LOG.info("splits["+j+"]="+splits[j]+" : " + v);
              //               LOG.info("@"+reader.getPos());
              //             }
              assertFalse("Key in multiple partitions.", bits.get(v));
              bits.set(v);
              count++;
            }
            //LOG.info("splits["+j+"]="+splits[j]+" count=" + count);
          } finally {
            reader.close();
          }
        }
        assertEquals("Some keys in no partition.", length, bits.cardinality());
      }

    }
  }

  public static void main(String[] args) throws Exception {
    new TestTextInputFormat().testFormat();
  }
}
