package org.apache.hadoop.fs;

import java.io.*;

/****************************************************************
 * FSOutputStream is an OutputStream that can track its position.
 *
 * @author Mike Cafarella
 *****************************************************************/
public abstract class FSOutputStream extends OutputStream {
    /**
     * Return the current offset from the start of the file
     */
    public abstract long getPos() throws IOException;
}
