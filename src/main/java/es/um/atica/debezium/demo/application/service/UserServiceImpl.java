package es.um.atica.debezium.demo.application.service;

import java.util.List;
import java.util.Optional;

import es.um.atica.debezium.demo.application.port.UserReadRepository;
import es.um.atica.debezium.demo.application.port.UserService;
import es.um.atica.debezium.demo.application.port.UserWriteRepository;
import es.um.atica.debezium.demo.domain.User;

public class UserServiceImpl implements UserService {
 
    protected UserWriteRepository userWriteRepository;
    protected UserReadRepository userReadRepository;

    @Override
    public List<User> findAll() {
        return userReadRepository.findAll();
    }

    @Override
    public Optional<User> findById(String id) {
        return userReadRepository.findById(id);
    }

    @Override
    public Optional<User> changeUserName(String id, String userName) {
        Optional<User> usr = userReadRepository.findById(id);
        userWriteRepository.save(userReadRepository.findById(id).get().changeUserName(userName));
        return usr;
    }

    @Override
    public Optional<User> createUser(String userName) {
        return Optional.ofNullable(userWriteRepository.save(User.of(userName)));
    }

    @Override
    public Optional<User> createUserWithId(String userId, String userName) {
        return Optional.ofNullable(userWriteRepository.save(User.of(userId,userName)));
    }
    
}
