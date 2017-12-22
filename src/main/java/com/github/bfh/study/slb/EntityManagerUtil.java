package com.github.bfh.study.slb;

import javax.persistence.EntityManager;

/**
 * singleton class for holding {@link EntityManager} over several treads.
 *
 * @author Samuel Ackermann
 */
public class EntityManagerUtil {

    private static EntityManagerUtil instance;

    private EntityManager entityManager;

    private EntityManagerUtil(EntityManager em) {
        entityManager = em;
    }

    public static EntityManagerUtil instance()  {
        if (instance == null) {
            return instance(null);
        }
        return instance;
    }

    /**
     * save entity manager into a new instance. if no instance exists.
     *
     * @param em {@link EntityManager}
     * @return instance of this class {@link EntityManagerUtil}
     */
    public static EntityManagerUtil instance(EntityManager em)  {
        if (instance == null) {
            instance = new EntityManagerUtil(em);
        }

        return instance;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
