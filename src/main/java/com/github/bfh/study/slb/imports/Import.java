package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.imports.parser.ProcessingElement;

import java.util.List;

/**
 * Represents an import provider.
 *
 * @author Dario Carosella
 */
public interface Import {

    int doSomeStuff(int number1, int number2);

    List<ProcessingElement> getProcessingElements();
}
