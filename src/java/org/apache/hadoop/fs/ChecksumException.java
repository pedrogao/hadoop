package org.apache.hadoop.fs;

import java.io.IOException;

/**
 * Thrown for checksum errors.
 * 校验和异常
 */
public class ChecksumException extends IOException {
    public ChecksumException(String description) {
        super(description);
    }
}
