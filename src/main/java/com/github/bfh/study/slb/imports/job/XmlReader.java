package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.imports.ImportContext;
import com.github.bfh.study.slb.imports.parser.PartialXmlParser;
import java.io.Serializable;
import javax.batch.api.chunk.ItemReader;

/**
 * parse a defined xml and get the elements back
 *
 * @author Samuel Ackermann
 */
public class XmlReader extends ReaderWriterBase implements ItemReader {

    private PartialXmlParser parser;

    @Override
    public void open(Serializable checkpoint) throws Exception {
        this.checkpoint = checkpoint;
        ImportContext context = ImportContext.importContextFactory("SECO");
        parser = new PartialXmlParser(context.getProcessingElements());
        parser.open("");
    }

    @Override
    public void close() throws Exception {
        parser.close();
    }

    @Override
    public Object readItem() throws Exception {
        return parser.parse();
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }
}
