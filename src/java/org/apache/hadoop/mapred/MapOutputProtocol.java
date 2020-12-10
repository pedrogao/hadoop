

package org.apache.hadoop.mapred;

import java.io.IOException;

import org.apache.hadoop.io.*;

/** Protocol that a reduce task uses to retrieve output data from a map task's
 * tracker. */ 
interface MapOutputProtocol {

  /** Returns the output from the named map task destined for this partition.*/
  MapOutputFile getFile(String mapTaskId, String reduceTaskId,
                        IntWritable partition) throws IOException;

}
