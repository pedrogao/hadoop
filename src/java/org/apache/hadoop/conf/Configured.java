package org.apache.hadoop.conf;

/**
 * Base class for things that may be configured with a {@link Configuration}.
 * 可配置基础类
 */
public class Configured implements Configurable {

    private Configuration conf;

    /**
     * Construct a Configured.
     */
    public Configured(Configuration conf) {
        setConf(conf);
    }

    // inherit javadoc
    public void setConf(Configuration conf) {
        this.conf = conf;
    }

    // inherit javadoc
    public Configuration getConf() {
        return conf;
    }

}
