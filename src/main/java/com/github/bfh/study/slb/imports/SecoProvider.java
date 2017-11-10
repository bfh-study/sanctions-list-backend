package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.provider.seco.entities.SanctionProgram;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Dario Carosella
 */
public class SecoProvider implements Import {

    @Override
    public int doSomeStuff(int number1, int number2) {
        return number1 + number2;
    }

    @Override
    public Map<String, Class<?>> getProcessingElements() {
        Map<String, Class<?>> classes = new HashMap<>();
        classes.put("sanctions-program", SanctionProgram.class);

        return classes;
    }
}
