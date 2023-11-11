package org.francescobasile.tisano;

import org.francescobasile.tisano.entity.User;
import org.francescobasile.tisano.entity.repository.UserRepository;

import java.util.List;

public class UserService implements EntityService<User> {

    //    @Inject
    private final UserRepository userRepository;

    public UserService() {
        super();
        this.userRepository = new UserRepository();
    }

    @Override
    public User findById(int id) {
        return userRepository.findId(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean create(User entity) {
        return false;
    }

    @Override
    public Boolean update(User entity) {
        return false;
    }

    @Override
    public Boolean delete(User entity) {
        return false;
    }
}
