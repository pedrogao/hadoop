package org.apache.hadoop.fs;

import java.io.*;

/****************************************************************
 * FSInputStream is a generic old InputStream with a little bit
 * of RAF-style seek ability.
 *
 * @author Mike Cafarella
 *****************************************************************/
public abstract class FSInputStream extends InputStream implements Seekable {
    /**
     * Seek to the given offset from the start of the file.
     * The next read() will be from that location.  Can't
     * seek past the end of the file.
     */
    public abstract void seek(long pos) throws IOException;

    /**
     * Return the current offset from the start of the file
     */
    public abstract long getPos() throws IOException;
}
