package org.apache.hadoop.dfs;

import java.io.*;

/**********************************************************************
 * Protocol that a DFS datanode uses to communicate with the NameNode.
 * It's used to upload current load information and block reports.
 *
 * The only way a NameNode can communicate with a DataNode is by
 * returning values from these functions.
 *
 * @author Michael Cafarella
 **********************************************************************/
interface DatanodeProtocol {
  /**
   * sendHeartbeat() tells the NameNode that the DataNode is still
   * alive and well.  Includes some status info, too.
   */
  public void sendHeartbeat(String sender, long capacity, long remaining) throws IOException;

  /**
   * blockReport() tells the NameNode about all the locally-stored blocks.
   * The NameNode returns an array of Blocks that have become obsolete
   * and should be deleted.  This function is meant to upload *all*
   * the locally-stored blocks.  It's invoked upon startup and then
   * infrequently afterwards.
   */
  public Block[] blockReport(String sender, Block blocks[]) throws IOException;

  /**
   * blockReceived() allows the DataNode to tell the NameNode about
   * recently-received block data.  For example, whenever client code
   * writes a new Block here, or another DataNode copies a Block to
   * this DataNode, it will call blockReceived().
   */
  public void blockReceived(String sender, Block blocks[]) throws IOException;

  /**
   * errorReport() tells the NameNode about something that has gone
   * awry.  Useful for debugging.
   */
  public void errorReport(String sender, String msg) throws IOException;

  /**
   * The DataNode periodically calls getBlockwork().  It includes a
   * small amount of status information, but mainly gives the NameNode
   * a chance to return a "BlockCommand" object.  A BlockCommand tells
   * the DataNode to invalidate local block(s), or to copy them to other
   * DataNodes, etc.
   */
  public BlockCommand getBlockwork(String sender, int xmitsInProgress) throws IOException;
}
