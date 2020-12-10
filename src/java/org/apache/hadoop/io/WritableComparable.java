package org.apache.hadoop.io;

/**
 * An interface which extends both {@link Writable} and {@link Comparable}.
 * 可读，可比较
 *
 * @author Doug Cutting
 */
public interface WritableComparable extends Writable, Comparable {
}
