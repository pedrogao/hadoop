package org.apache.hadoop.io;

import java.io.IOException;

// NOTE: Replace with java.io.Closeable when we upgrade to Java 1.5.

/**
 * That which can be closed.
 */
public interface Closeable {
  /**
   * Called after the last call to any other method on this object to free
   * and/or flush resources.  Typical implementations do nothing.
   */
  void close() throws IOException;
}
