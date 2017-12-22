package com.github.bfh.study.slb.app;

import com.github.bfh.study.slb.EntityManagerUtil;

import org.bitbucket.samsamann.rest.base.SimpleTransactionRunner;
import org.bitbucket.samsamann.rest.core.RestFeature;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * Activate the rest feature and other parts.
 *
 * @author Samuel Ackermann
 */
@Provider
@ApplicationScoped
public class JsonApiFeature implements Feature {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonApiFeature.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean configure(FeatureContext featureContext) {
        EntityManagerUtil.instance(entityManager);

        // Reindexing at startup
        FullTextEntityManager fullTextEntityManager =
            Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            LOGGER.warn("reindexing thread was interrupted.");
        }


        RestFeature restFeature =
            new RestFeature(entityManager, new SimpleTransactionRunner(entityManager));

        restFeature.addModule(new SlbModule());
        featureContext.register(restFeature);

        return true;
    }
}
