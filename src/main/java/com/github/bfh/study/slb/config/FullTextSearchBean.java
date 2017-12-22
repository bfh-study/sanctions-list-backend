package com.github.bfh.study.slb.config;

import org.hibernate.search.jpa.FullTextEntityManager;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class FullTextSearchBean {

    @PersistenceContext(unitName = "defaultUnit")
    private EntityManager entityManager;

    @Produces
    public FullTextEntityManager getFullTextEntityManager() {
        return org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);
    }
}
