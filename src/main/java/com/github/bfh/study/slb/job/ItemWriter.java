package com.github.bfh.study.slb.job;

import java.io.Serializable;
import java.util.List;

/**
 * write an object to the database.
 *
 * @author Samuel Ackermann <saemi.ackermann@gmail.com>
 */
public class ItemWriter extends ReaderWriterBase implements javax.batch.api.chunk.ItemWriter {
  @Override
  public void open(Serializable checkpoint) throws Exception {
    this.checkpoint = checkpoint;
  }

  @Override
  public void close() throws Exception {
    String test = "nothing to close! it is a dummy!";
  }

  @Override
  public void writeItems(List<Object> items) throws Exception {
    items.forEach(o -> o.toString());
  }

  @Override
  public Serializable checkpointInfo() throws Exception {
    return checkpoint;
  }
}
