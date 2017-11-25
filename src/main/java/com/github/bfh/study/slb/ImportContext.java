package com.github.bfh.study.slb;

import com.github.bfh.study.slb.imports.Import;
import com.github.bfh.study.slb.imports.parser.ProcessingElement;

import java.util.List;

/**
 * Context of the import process.
 *
 * @author Dario Carosella
 */
public class ImportContext {

    private Import importer;

    public ImportContext(Import importer) {
        this.importer = importer;
    }

    public int executeImport(int number1, int number2) {
        return importer.doSomeStuff(number1, number2);
    }

    public List<ProcessingElement> getProcessingElements() {
        return importer.getProcessingElements();
    }
}
