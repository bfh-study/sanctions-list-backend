package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.imports.parser.ProcessingElement;

import java.util.List;
import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

/**
 * Represents an import provider.
 *
 * @author Dario Carosella
 */
public interface Import {

    int doSomeStuff(int number1, int number2);

    BaseEntity convertEntity(Object o);

    List<ProcessingElement> getProcessingElements();
}
