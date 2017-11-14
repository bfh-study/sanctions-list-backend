package com.github.bfh.study.slb.app;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
import org.bitbucket.samsamann.rest.base.SimpleTransactionRunner;
import org.bitbucket.samsamann.rest.core.RestFeature;

/**
 * Activate the rest feature and other parts
 */
@Provider
@ApplicationScoped
public class JsonApiFeature implements Feature {
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public boolean configure(FeatureContext featureContext) {
    RestFeature restFeature =
        new RestFeature(entityManager, new SimpleTransactionRunner(entityManager));

    restFeature.addModule(new SlbModule());
    featureContext.register(restFeature);

    return true;
  }
}
