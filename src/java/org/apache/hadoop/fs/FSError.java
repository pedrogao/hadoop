package org.apache.hadoop.fs;

/**
 * Thrown for unexpected filesystem errors, presumed to reflect disk errors
 * in the native filesystem.
 * 文件系统错误
 */
public class FSError extends Error {
    FSError(Throwable cause) {
        super(cause);
    }
}
