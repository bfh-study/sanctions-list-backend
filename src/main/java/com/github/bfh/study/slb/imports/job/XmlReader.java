package com.github.bfh.study.slb.imports.job;

import com.github.bfh.study.slb.ImportContext;
import com.github.bfh.study.slb.imports.parser.PartialXmlParser;
import com.github.bfh.study.slb.imports.SecoProvider;

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
        ImportContext context = new ImportContext(new SecoProvider());
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
