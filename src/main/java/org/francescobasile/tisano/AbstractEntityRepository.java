package org.francescobasile.tisano;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;

import javax.naming.InitialContext;

@PersistenceContext(name = "entityManager", unitName = "tisano-pu-jta", type = PersistenceContextType.TRANSACTION)
public abstract class AbstractEntityRepository {
    protected EntityManager entityManager;

    public AbstractEntityRepository() {
        try {
            this.getEntityManager();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getEntityManager() throws Exception {
        InitialContext context = new InitialContext();
        entityManager = (EntityManager) context.lookup("java:comp/env/entityManager");
        if (entityManager == null) throw new NullPointerException("entityManager not injected");
    }
}
