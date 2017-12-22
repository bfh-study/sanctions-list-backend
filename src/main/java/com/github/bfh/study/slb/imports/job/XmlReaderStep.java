package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;
import com.github.bfh.study.slb.imports.parser.PartialXmlParser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Properties;
import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.BatchRuntime;
import javax.xml.stream.XMLStreamException;

/**
 * Parse a defined xml and get the elements back.
 *
 * @author Samuel Ackermann
 */
public class XmlReaderStep extends BaseStep implements ItemReader {

    private static final Logger logger = LoggerFactory.getLogger(XmlReaderStep.class);

    private PartialXmlParser parser;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        if (checkpoint == null) {
            checkpoint = 0;
        }
        this.checkpoint = checkpoint;
        ImportContext context;
        try {
            context = (ImportContext) jobContext.getTransientUserData();
        } catch (ClassCastException e) {
            abortExecution("No import context found.");
            return;
        }

        Properties properties = BatchRuntime.getJobOperator().getParameters(
            jobContext.getExecutionId());
        if (!properties.containsKey(JobProperties.PATH_NAME_PROPERTY)) {
            abortExecution(
                String.format("No property found with name '%s'", JobProperties.PATH_NAME_PROPERTY)
            );
        }

        parser = new PartialXmlParser(context.getProcessingElements());
        try {
            parser.open(properties.getProperty(JobProperties.PATH_NAME_PROPERTY));
        } catch (FileNotFoundException | XMLStreamException e) {
            logger.error("Execution failed... Reason: " + e.getMessage());
            abortExecution("Can not open file/path");
        }
    }

    @Override
    public void close() throws Exception {
        parser.close();
        parser = null;
    }

    @Override
    public Object readItem() throws Exception {
        Object parsedObject = parser.parse();
        if (parsedObject != null) {
            checkpoint = (int)checkpoint + 1;
        }
        return parsedObject;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }

    private void abortExecution(String exitStatus) {
        jobContext.setExitStatus(exitStatus);
        BatchRuntime.getJobOperator().stop(jobContext.getExecutionId());
    }
}
