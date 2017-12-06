package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.imports.parser.ProcessingElement;

import java.util.List;

/**
 * Context of the import process.
 *
 * @author Dario Carosella
 */
public class ImportContext {

    private Import importer;

    private ImportContext(Import importer) {
        this.importer = importer;
    }

    public int executeImport(int number1, int number2) {
        return importer.doSomeStuff(number1, number2);
    }

    public List<ProcessingElement> getProcessingElements() {
        return importer.getProcessingElements();
    }

    /**
     * Import factory - create an import context decided by source name.
     *
     * @param sourceName Name of source
     * @return ImportContext import context
     * @throws SourceNotFoundException throw if no source was found
     */
    public static ImportContext importContextFactory(String sourceName)
        throws SourceNotFoundException {

        Import importer = null;
        switch (sourceName) {
            case "SECO":
                importer = new SecoProvider();
                break;
            default:
                throw new SourceNotFoundException(sourceName);
        }

        return new ImportContext(importer);
    }
}
