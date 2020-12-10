

package org.apache.hadoop.mapred;

/** That what may be configured. */
public interface JobConfigurable {
  /** Initializes a new instance from a {@link JobConf}.
   *
   * @param job the configuration
   */
  void configure(JobConf job);
}
