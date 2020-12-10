

package org.apache.hadoop.io;

import java.io.*;
import java.util.*;
import junit.framework.TestCase;
import java.util.logging.*;

import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;

/** Support for flat files of binary key/value pairs. */
public class TestSetFile extends TestCase {
  private static Logger LOG = SequenceFile.LOG;
  private static String FILE =
    System.getProperty("test.build.data",".") + "/test.set";

  private static Configuration conf = new Configuration();
  
  public TestSetFile(String name) { super(name); }

  public void testSetFile() throws Exception {
    FileSystem fs = new LocalFileSystem(conf);
    try {
        RandomDatum[] data = generate(10000);
        writeTest(fs, data, FILE);
        readTest(fs, data, FILE);
    } finally {
        fs.close();
    }
  }

  private static RandomDatum[] generate(int count) {
    LOG.fine("generating " + count + " records in memory");
    RandomDatum[] data = new RandomDatum[count];
    RandomDatum.Generator generator = new RandomDatum.Generator();
    for (int i = 0; i < count; i++) {
      generator.next();
      data[i] = generator.getValue();
    }
    LOG.fine("sorting " + count + " records in memory");
    Arrays.sort(data);
    return data;
  }

  private static void writeTest(FileSystem fs, RandomDatum[] data, String file)
    throws IOException {
    MapFile.delete(fs, file);
    LOG.fine("creating with " + data.length + " records");
    SetFile.Writer writer = new SetFile.Writer(fs, file, RandomDatum.class);
    for (int i = 0; i < data.length; i++)
      writer.append(data[i]);
    writer.close();
  }

  private static void readTest(FileSystem fs, RandomDatum[] data, String file)
    throws IOException {
    RandomDatum v = new RandomDatum();
    LOG.fine("reading " + data.length + " records");
    SetFile.Reader reader = new SetFile.Reader(fs, file, conf);
    for (int i = 0; i < data.length; i++) {
      if (!reader.seek(data[i]))
        throw new RuntimeException("wrong value at " + i);
    }
    reader.close();
    LOG.fine("done reading " + data.length + " records");
  }


  /** For debugging and testing. */
  public static void main(String[] args) throws Exception {
    int count = 1024 * 1024;
    boolean create = true;
    boolean check = true;
    String file = FILE;
    String usage = "Usage: TestSetFile (-local | -dfs <namenode:port>) [-count N] [-nocreate] [-nocheck] file";
      
    if (args.length == 0) {
      System.err.println(usage);
      System.exit(-1);
    }
      
    int i = 0;
    FileSystem fs = FileSystem.parseArgs(args, i, conf);      
    try {
      for (; i < args.length; i++) {       // parse command line
        if (args[i] == null) {
          continue;
        } else if (args[i].equals("-count")) {
          count = Integer.parseInt(args[++i]);
        } else if (args[i].equals("-nocreate")) {
          create = false;
        } else if (args[i].equals("-nocheck")) {
          check = false;
        } else {
          // file is required parameter
          file = args[i];
        }

        LOG.info("count = " + count);
        LOG.info("create = " + create);
        LOG.info("check = " + check);
        LOG.info("file = " + file);

        LOG.setLevel(Level.FINE);

        RandomDatum[] data = generate(count);

        if (create) {
          writeTest(fs, data, file);
        }

        if (check) {
          readTest(fs, data, file);
        }
      }
    } finally {
      fs.close();
    }
  }
}