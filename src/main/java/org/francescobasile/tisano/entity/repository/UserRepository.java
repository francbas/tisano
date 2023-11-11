package org.francescobasile.tisano.entity.repository;

import jakarta.ejb.Stateless;
import jakarta.persistence.Query;
import org.francescobasile.tisano.AbstractEntityRepository;
import org.francescobasile.tisano.entity.User;

import java.util.List;

@Stateless
public class UserRepository extends AbstractEntityRepository {

    public UserRepository() {
        super();
    }

    public User findId(int id) {
        return entityManager.find(User.class, id);
    }

    public List<User> findAll() {
        Query query = entityManager.createQuery("select u from User u");
        return (List<User>) query.getResultList();
    }


}
