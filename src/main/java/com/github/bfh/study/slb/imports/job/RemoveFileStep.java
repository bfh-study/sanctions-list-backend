package com.github.bfh.study.slb.imports.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.File;
import java.util.Properties;
import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;

/**
 * removes the tmp file after importing sanctions
 *
 * @author Samuel Ackermann
 */
public class RemoveFileStep extends BaseStep implements Batchlet {

    private static final Logger logger = LoggerFactory.getLogger(RemoveFileStep.class);

    @Override
    public String process() throws Exception {
        Boolean isDeleted = false;
        Properties properties = BatchRuntime.getJobOperator().getParameters(
            jobContext.getExecutionId()
        );
        try {
            File file = new File(properties.getProperty(JobProperties.PATH_NAME_PROPERTY));
            isDeleted = file.delete();
        } catch(Exception e) {
            logger.error("can not remove tmp file", e);
        }
        if (isDeleted) {
            return BatchStatus.COMPLETED.toString();
        }
        return BatchStatus.FAILED.toString();
    }

    @Override
    public void stop() throws Exception {
        throw new NotImplementedException();
    }
}
