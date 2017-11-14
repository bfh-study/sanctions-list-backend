package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;
import com.github.bfh.study.slb.imports.SourceNotFoundException;
import javax.batch.api.Batchlet;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Prepare and configure import context
 *
 * @author Samuel Ackermann
 */
public class PrepareStep implements Batchlet {

    private static final Logger logger = LoggerFactory.getLogger(PrepareStep.class);

    public static final String SOURCE_NAME_PROPERTY = "source_name";

    @Inject
    private JobContext jobContext;

    @Override
    public String process() throws Exception {
        String sourceName = jobContext.getProperties().getProperty(SOURCE_NAME_PROPERTY);
        Thread.sleep(2000L);
        try {
            jobContext.setTransientUserData(ImportContext.importContextFactory(sourceName));
        } catch (SourceNotFoundException e) {
            logger.warn(String.format("%s Job will stop", e.getMessage()));
            return BatchStatus.FAILED.toString();
        }

        return BatchStatus.COMPLETED.toString();
  }

  @Override
  public void stop() throws Exception { throw new NotImplementedException(); }
}
