package org.apache.hadoop.io;

/**
 * A factory for a class of Writable.
 *
 * @see WritableFactories
 */
public interface WritableFactory {
  /**
   * Return a new instance.
   */
  Writable newInstance();
}

