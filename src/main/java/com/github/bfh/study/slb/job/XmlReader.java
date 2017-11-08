package com.github.bfh.study.slb.job;

import java.io.Serializable;
import javax.batch.api.chunk.ItemReader;

/**
 * write an object to the database.
 *
 * @author Samuel Ackermann
 */
public class XmlReader extends ReaderWriterBase implements ItemReader {
    @Override
    public void open(Serializable checkpoint) throws Exception {
        this.checkpoint = checkpoint;
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public Object readItem() throws Exception {
        return new Object();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
