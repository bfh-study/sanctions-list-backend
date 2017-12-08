package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;

import javax.batch.api.chunk.ItemProcessor;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

public class EntityProcessor implements ItemProcessor {

    @Inject
    private JobContext jobContext;

    private ImportContext context;

    /*public EntityProcessor() {
        context = null;
    }*/

    @Override
    public Object processItem(Object item) throws Exception {
        if (context == null) {
            context = (ImportContext) jobContext.getTransientUserData();
        }

        return item;
    }
}
