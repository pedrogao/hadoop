package org.apache.hadoop.fs;

import java.io.*;

/**
 * Stream that permits seeking.
 */
public interface Seekable {
    /**
     * Seek to the given offset from the start of the file.
     * The next read() will be from that location.  Can't
     * seek past the end of the file.
     */
    void seek(long pos) throws IOException;
}
