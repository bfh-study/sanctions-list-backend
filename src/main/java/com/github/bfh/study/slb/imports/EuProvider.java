package com.github.bfh.study.slb.imports;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Dario Carosella
 */
public class EuProvider implements Import {

    @Override
    public int doSomeStuff(int number1, int number2) {
        return number1 - number2;
    }

    @Override
    public Map<String, Class<?>> getProcessingElements() {
        return new HashMap<>();
    }
}
