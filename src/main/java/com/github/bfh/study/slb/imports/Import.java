package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.imports.parser.ProcessingElement;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.util.List;

/**
 * Represents an import provider.
 *
 * @author Dario Carosella
 */
public interface Import {

    BaseEntity convertEntity(Object object);

    List<ProcessingElement> getProcessingElements();
}
