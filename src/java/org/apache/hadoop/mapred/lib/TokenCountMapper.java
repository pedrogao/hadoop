

package org.apache.hadoop.mapred.lib;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.MapReduceBase;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.UTF8;


/** A {@link Mapper} that maps text values into <token,freq> pairs.  Uses
 * {@link StringTokenizer} to break text into tokens. */
public class TokenCountMapper extends MapReduceBase implements Mapper {

  public void map(WritableComparable key, Writable value,
                  OutputCollector output, Reporter reporter)
    throws IOException {
    // get input text
    String text = ((UTF8)value).toString();       // value is line of text

    // tokenize the value
    StringTokenizer st = new StringTokenizer(text);
    while (st.hasMoreTokens()) {
      // output <token,1> pairs
      output.collect(new UTF8(st.nextToken()), new LongWritable(1));
    }  
  }
  
}
