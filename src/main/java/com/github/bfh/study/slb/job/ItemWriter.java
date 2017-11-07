package com.github.bfh.study.slb.job;

import java.io.Serializable;
import java.util.List;

/**
 * Created by samuel on 07.11.17.
 */
public class ItemWriter extends ReaderWriterBase implements javax.batch.api.chunk.ItemWriter {
    @Override
    public void open(Serializable checkpoint) throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public void writeItems(List<Object> items) throws Exception {

    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return null;
    }
}
