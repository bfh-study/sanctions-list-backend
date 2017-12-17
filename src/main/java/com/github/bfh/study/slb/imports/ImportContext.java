package com.github.bfh.study.slb.imports;

import com.github.bfh.study.slb.EntityManagerUtil;
import com.github.bfh.study.slb.imports.parser.ProcessingElement;

import org.bitbucket.samsamann.rest.base.entities.BaseEntity;

import java.util.List;
import javax.persistence.EntityManager;


/**
 * Context of the import process.
 *
 * @author Dario Carosella
 */
public class ImportContext {

    private Import importer;

    private final EntityManager entityManager;

    private ImportContext(Import importer) {
        this.importer = importer;
        entityManager = EntityManagerUtil.instance().getEntityManager();
    }

    public List<ProcessingElement> getProcessingElements() {
        return importer.getProcessingElements();
    }

    public BaseEntity convertEntity(Object object) {
        return importer.convertEntity(object);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Import factory - create an import context decided by source name.
     *
     * @param sourceName Name of source
     * @return ImportContext import context
     * @throws SourceNotFoundException throw if no source was found
     */
    public static ImportContext importContextFactory(String sourceName)
        throws SourceNotFoundException {

        Import importer = null;
        switch (sourceName) {
            case "SECO":
                importer = new SecoProvider();
                break;
            case "EU":
                importer = new EuProvider();
                break;
            default:
                throw new SourceNotFoundException(sourceName);
        }

        return new ImportContext(importer);
    }
}
