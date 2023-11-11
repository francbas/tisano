package org.francescobasile.tisano.prove;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class LocalEntityRepository {

    static private EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("tisano-pu");
    static private EntityManager entityManager = managerFactory.createEntityManager();

    private static EntityManager openEm() {
        if (entityManager.isOpen()) return entityManager;
        managerFactory = Persistence.createEntityManagerFactory("tisano-pu");
        entityManager = managerFactory.createEntityManager();
        return entityManager;
    }

    public static <T> void save(T entity) {
        entityManager = openEm();
        EntityTransaction tx = entityManager.getTransaction();
        try {
            tx.begin();
            entityManager.persist(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
                throw e;
            }
        }
    }

    public static <T> void merge(T entity) {
        entityManager = openEm();
        entityManager.merge(entity);
    }

    public static void close() {
        if (!entityManager.isOpen()) return;
        entityManager.close();
        managerFactory.close();
    }

    public static <T> T find(Class<T> classe, Object o) {
        entityManager = openEm();
        return entityManager.find(classe, o);
    }

}
