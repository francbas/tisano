package org.francescobasile.tisano;

import java.util.List;

public class UserService implements EntityService<User> {

    //    @Inject
    private UserRepository userRepository;

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
    public <P> Boolean update(User entity) {
        return false;
    }

    @Override
    public Boolean delete(User entity) {
        return false;
    }
}
