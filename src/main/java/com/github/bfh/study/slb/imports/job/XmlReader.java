package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.ImportContext;
import com.github.bfh.study.slb.imports.parser.PartialXmlParser;
import java.io.Serializable;
import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

/**
 * Parse a defined xml and get the elements back.
 *
 * @author Samuel Ackermann
 */
public class XmlReader extends ReaderWriterBase implements ItemReader {

    private PartialXmlParser parser;

    @Inject
    private JobContext jobContext;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        this.checkpoint = checkpoint;
        ImportContext context = null;
        try {
            context = (ImportContext) jobContext.getTransientUserData();
        } catch (ClassCastException e) {
            jobContext.setExitStatus("No import context found.");
            return;
        }
        //parser = new PartialXmlParser(context.getProcessingElements());
        //parser.open("");
    }

    @Override
    public void close() throws Exception {
        //parser.close();
    }

    @Override
    public Object readItem() throws Exception {
        //Object o = parser.parse();
        //checkpoint += (Serializable) 1;
        return null ;
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
