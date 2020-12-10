package org.apache.hadoop.conf;

/**
 * Something that may be configured with a {@link Configuration}.
 * 可配置接口
 */
public interface Configurable {

    /**
     * Set the configuration to be used by this object.
     */
    void setConf(Configuration conf);

    /**
     * Return the configuration used by this object.
     */
    Configuration getConf();
}
