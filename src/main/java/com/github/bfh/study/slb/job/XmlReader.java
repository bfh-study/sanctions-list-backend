package com.github.bfh.study.slb.job;

import javax.batch.api.chunk.ItemReader;
import java.io.Serializable;

/**
 *
 */
public class XmlReader extends ReaderWriterBase implements ItemReader {
    @Override
    public void open(Serializable checkpoint) throws Exception {
        this.checkpoint = checkpoint;
    }

    @Override
    public void close() throws Exception {
        String message = "nothing to close!";
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
