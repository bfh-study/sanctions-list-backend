package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.imports.parser.ProcessingElement;
import com.github.bfh.study.slb.provider.seco.entities.SanctionProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * Processes the Swiss sanction list.
 *
 * @author Dario Carosella
 */
public class SecoProvider implements Import {

    @Override
    public int doSomeStuff(int number1, int number2) {
        return number1 + number2;
    }

    @Override
    public List<ProcessingElement> getProcessingElements() {
        List<ProcessingElement> elements = new ArrayList<>();
        elements.add(new ProcessingElement("sanctions-program", SanctionProgram.class));
        return elements;
    }
}
