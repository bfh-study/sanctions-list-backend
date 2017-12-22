package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;

import java.io.Serializable;
import java.util.List;

/**
 * write an object to the database.
 *
 * @author Samuel Ackermann
 */
public class ItemWriterStep extends BaseStep implements javax.batch.api.chunk.ItemWriter {

    @Override
    public void open(Serializable checkpoint) throws Exception {
        this.checkpoint = checkpoint;

        ImportContext context = (ImportContext) jobContext.getTransientUserData();
        assert context != null : "context must not be null";
        entityManager = context.getEntityManager();
    }

    @Override
    public void close() throws Exception {
        entityManager.flush();
    }

    @Override
    public void writeItems(List<Object> items) throws Exception {
        items.forEach((item) -> {
            entityManager.persist(item);
        });
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
