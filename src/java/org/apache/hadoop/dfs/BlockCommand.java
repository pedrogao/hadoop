package org.apache.hadoop.dfs;

import org.apache.hadoop.io.*;

import java.io.*;

/****************************************************
 * A BlockCommand is an instruction to a datanode 
 * regarding some blocks under its control.  It tells
 * the DataNode to either invalidate a set of indicated
 * blocks, or to copy a set of indicated blocks to 
 * another DataNode.
 *
 * @author Mike Cafarella
 ****************************************************/
class BlockCommand implements Writable {

  static {                                      // register a ctor
    WritableFactories.setFactory
      (BlockCommand.class,
        new WritableFactory() {
          public Writable newInstance() {
            return new BlockCommand();
          }
        });
  }

  boolean transferBlocks = false;
  boolean invalidateBlocks = false;
  Block blocks[];
  DatanodeInfo targets[][];

  public BlockCommand() {
    this.transferBlocks = false;
    this.invalidateBlocks = false;
    this.blocks = new Block[0];
    this.targets = new DatanodeInfo[0][];
  }

  public BlockCommand(Block blocks[], DatanodeInfo targets[][]) {
    this.transferBlocks = true;
    this.invalidateBlocks = false;
    this.blocks = blocks;
    this.targets = targets;
  }

  public BlockCommand(Block blocks[]) {
    this.transferBlocks = false;
    this.invalidateBlocks = true;
    this.blocks = blocks;
    this.targets = new DatanodeInfo[0][];
  }

  public boolean transferBlocks() {
    return transferBlocks;
  }

  public boolean invalidateBlocks() {
    return invalidateBlocks;
  }

  public Block[] getBlocks() {
    return blocks;
  }

  public DatanodeInfo[][] getTargets() {
    return targets;
  }

  ///////////////////////////////////////////
  // Writable
  ///////////////////////////////////////////
  public void write(DataOutput out) throws IOException {
    out.writeBoolean(transferBlocks);
    out.writeBoolean(invalidateBlocks);
    out.writeInt(blocks.length);
    for (int i = 0; i < blocks.length; i++) {
      blocks[i].write(out);
    }
    out.writeInt(targets.length);
    for (int i = 0; i < targets.length; i++) {
      out.writeInt(targets[i].length);
      for (int j = 0; j < targets[i].length; j++) {
        targets[i][j].write(out);
      }
    }
  }

  public void readFields(DataInput in) throws IOException {
    this.transferBlocks = in.readBoolean();
    this.invalidateBlocks = in.readBoolean();
    this.blocks = new Block[in.readInt()];
    for (int i = 0; i < blocks.length; i++) {
      blocks[i] = new Block();
      blocks[i].readFields(in);
    }

    this.targets = new DatanodeInfo[in.readInt()][];
    for (int i = 0; i < targets.length; i++) {
      this.targets[i] = new DatanodeInfo[in.readInt()];
      for (int j = 0; j < targets[i].length; j++) {
        targets[i][j] = new DatanodeInfo();
        targets[i][j].readFields(in);
      }
    }
  }
}
