package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;

import java.io.Serializable;
import java.util.List;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * write an object to the database.
 *
 * @author Samuel Ackermann
 */
public class ItemWriter extends ReaderWriterBase implements javax.batch.api.chunk.ItemWriter {

    private EntityManager entityManager;

    @Inject
    private JobContext jobContext;

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
