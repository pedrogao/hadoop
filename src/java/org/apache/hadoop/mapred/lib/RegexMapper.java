

package org.apache.hadoop.mapred.lib;

import java.io.IOException;

import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.MapReduceBase;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.UTF8;


import java.util.regex.Pattern;
import java.util.regex.Matcher;


/** A {@link Mapper} that extracts text matching a regular expression. */
public class RegexMapper extends MapReduceBase implements Mapper {

  private Pattern pattern;
  private int group;

  public void configure(JobConf job) {
    pattern = Pattern.compile(job.get("mapred.mapper.regex"));
    group = job.getInt("mapred.mapper.regex.group", 0);
  }

  public void map(WritableComparable key, Writable value,
                  OutputCollector output, Reporter reporter)
    throws IOException {
    String text = ((UTF8)value).toString();
    Matcher matcher = pattern.matcher(text);
    while (matcher.find()) {
      output.collect(new UTF8(matcher.group(group)), new LongWritable(1));
    }
  }

}
