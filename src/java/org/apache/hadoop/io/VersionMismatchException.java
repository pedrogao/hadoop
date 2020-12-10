package org.apache.hadoop.io;

import java.io.IOException;

/**
 * Thrown by {@link VersionedWritable#readFields)} when the
 * version of an object being read does not match the current implementation
 * version as returned by {@link VersionedWritable#getVersion()}.
 */
public class VersionMismatchException extends IOException {

  private byte expectedVersion;
  private byte foundVersion;

  public VersionMismatchException(byte expectedVersionIn, byte foundVersionIn) {
    expectedVersion = expectedVersionIn;
    foundVersion = foundVersionIn;
  }

  /**
   * Returns a string representation of this object.
   */
  public String toString() {
    return "A record version mismatch occured. Expecting v"
      + expectedVersion + ", found v" + foundVersion;
  }
}
