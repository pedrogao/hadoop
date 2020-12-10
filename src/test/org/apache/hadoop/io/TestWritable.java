

package org.apache.hadoop.io;

import java.io.*;
import java.util.Random;
import junit.framework.TestCase;

/** Unit tests for Writable. */
public class TestWritable extends TestCase {
  public TestWritable(String name) { super(name); }

  /** Example class used in test cases below. */
  public static class SimpleWritable implements Writable {
    private static final Random RANDOM = new Random();

    int state = RANDOM.nextInt();

    public void write(DataOutput out) throws IOException {
      out.writeInt(state);
    }

    public void readFields(DataInput in) throws IOException {
      this.state = in.readInt();
    }

    public static SimpleWritable read(DataInput in) throws IOException {
      SimpleWritable result = new SimpleWritable();
      result.readFields(in);
      return result;
    }

    /** Required by test code, below. */
    public boolean equals(Object o) {
      if (!(o instanceof SimpleWritable))
        return false;
      SimpleWritable other = (SimpleWritable)o;
      return this.state == other.state;
    }
  }

  /** Test 1: Check that SimpleWritable. */
  public void testSimpleWritable() throws Exception {
    testWritable(new SimpleWritable());
  }

  /** Utility method for testing writables. */
  public static void testWritable(Writable before) throws Exception {
      DataOutputBuffer dob = new DataOutputBuffer();
      before.write(dob);

      DataInputBuffer dib = new DataInputBuffer();
      dib.reset(dob.getData(), dob.getLength());
    
      Writable after = (Writable)before.getClass().newInstance();
      after.readFields(dib);

      assertEquals(before, after);
  }
	
}
