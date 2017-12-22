package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;
import com.github.bfh.study.slb.imports.SourceNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Properties;
import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;

/**
 * Prepare and configure import context.
 *
 * @author Samuel Ackermann
 */
public class PrepareStep extends BaseStep implements Batchlet {

    private static final Logger logger = LoggerFactory.getLogger(PrepareStep.class);

    @Override
    public String process() {
        Properties properties = BatchRuntime.getJobOperator().getParameters(
            jobContext.getExecutionId()
        );
        String sourceName = properties.getProperty(JobProperties.SOURCE_NAME_PROPERTY);
        try {
            jobContext.setTransientUserData(ImportContext.importContextFactory(sourceName));
        } catch (SourceNotFoundException e) {
            logger.warn(String.format("%s Job will stop", e.getMessage()));
            BatchRuntime.getJobOperator().stop(jobContext.getExecutionId());
            return BatchStatus.FAILED.toString();
        }

        return BatchStatus.COMPLETED.toString();
    }

    @Override
    public void stop() {
        throw new NotImplementedException();
    }
}
