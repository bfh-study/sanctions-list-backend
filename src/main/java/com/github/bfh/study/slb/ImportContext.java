package com.github.bfh.study.slb;

import com.github.bfh.study.slb.imports.Import;

/**
 * @Author: Dario Carosella
 */
public class ImportContext {

    private Import importer;

    public ImportContext(Import importer){
        this.importer = importer;
    }

    public int executeImport(int number1, int number2){
        return importer.doSomeStuff(number1, number2);
    }
}
