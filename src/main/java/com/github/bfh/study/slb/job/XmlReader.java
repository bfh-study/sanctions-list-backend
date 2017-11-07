package com.github.bfh.study.slb.job;

import javax.batch.api.chunk.ItemReader;
import java.io.Serializable;

/**
 * Created by samuel on 07.11.17.
 */
public class XmlReader extends ReaderWriterBase implements ItemReader {
    @Override
    public void open(Serializable checkpoint) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public Object readItem() throws Exception {
        return null;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
