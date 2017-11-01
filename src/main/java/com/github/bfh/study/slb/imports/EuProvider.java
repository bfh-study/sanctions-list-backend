package com.github.bfh.study.slb.imports;

/**
 * @Author: Dario Carosella
 */
public class EuProvider implements Import {

    @Override
    public int doSomeStuff(int number1, int number2) {
        return number1 - number2;
    }
}
