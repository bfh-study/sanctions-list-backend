package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;

import java.io.Serializable;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * base class for all Steps.
 *
 * @author Samuel Ackermann
 */
abstract class BaseStep {

    @Inject
    JobContext jobContext;

    EntityManager entityManager;

    Serializable checkpoint;

    ImportContext context;

    BaseStep() {
        context = null;
    }
}
