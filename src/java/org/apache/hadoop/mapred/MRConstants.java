
package org.apache.hadoop.mapred;

/*******************************
 * Some handy constants
 * 
 * @author Mike Cafarella
 *******************************/
interface MRConstants {
    //
    // Timeouts, constants
    //
    public static final long HEARTBEAT_INTERVAL = 10 * 1000;
    public static final long TASKTRACKER_EXPIRY_INTERVAL = 10 * 60 * 1000;

    //
    // Result codes
    //
    public static int SUCCESS = 0;
    public static int FILE_NOT_FOUND = -1;
}
