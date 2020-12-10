package org.apache.hadoop.dfs;

import java.io.*;


/*****************************************************************
 * DFSFile is a traditional java File that's been annotated with
 * some extra information.
 *
 * @author Mike Cafarella
 *****************************************************************/
class DFSFile extends File {
  DFSFileInfo info;

  /**
   * Separator used in DFS filenames.
   */
  public static final String DFS_FILE_SEPARATOR = "/";

  /**
   *
   */
  public DFSFile(DFSFileInfo info) {
    super(info.getPath());
    this.info = info;
  }

  /**
   * A number of File methods are unsupported in this subclass
   */
  public boolean canRead() {
    return false;
  }

  public boolean canWrite() {
    return false;
  }

  public boolean createNewFile() {
    return false;
  }

  public boolean delete() {
    return false;
  }

  public void deleteOnExit() {
  }

  public boolean isHidden() {
    return false;
  }

  public boolean isAbsolute() {
    return true;
  }

  /**
   * We need to reimplement some of them
   */
  public boolean isDirectory() {
    return info.isDir();
  }

  public boolean isFile() {
    return !isDirectory();
  }

  public long length() {
    return info.getLen();
  }

  /**
   * And add a few extras
   */
  public long getContentsLength() {
    return info.getContentsLen();
  }

  /**
   * Retrieving parent path from DFS path string
   *
   * @param path - DFS path
   * @return - parent path of DFS path, or null if no parent exist.
   */
  public static String getDFSParent(String path) {
    if (path == null)
      return null;
    if (DFS_FILE_SEPARATOR.equals(path))
      return null;
    int index = path.lastIndexOf(DFS_FILE_SEPARATOR);
    if (index == -1)
      return null;
    if (index == 0)
      return DFS_FILE_SEPARATOR;
    return path.substring(0, index);
  }
}
