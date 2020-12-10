

package org.apache.hadoop.mapred.lib;

import java.io.IOException;

import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.MapReduceBase;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Writable;


/** A {@link Mapper} that swaps keys and values. */
public class InverseMapper extends MapReduceBase implements Mapper {

  /** The inverse function.  Input keys and values are swapped.*/
  public void map(WritableComparable key, Writable value,
                  OutputCollector output, Reporter reporter)
    throws IOException {
    output.collect((WritableComparable)value, key);
  }
  
}
