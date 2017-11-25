package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.imports.parser.ProcessingElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Processes the EU sanction list.
 *
 * @author Dario Carosella
 */
public class EuProvider implements Import {

    @Override
    public int doSomeStuff(int number1, int number2) {
        return number1 - number2;
    }

    @Override
    public List<ProcessingElement> getProcessingElements() {
        return new ArrayList<>();
    }
}
