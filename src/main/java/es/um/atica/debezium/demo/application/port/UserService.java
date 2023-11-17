package es.um.atica.debezium.demo.application.port;

import java.util.List;
import java.util.Optional;

import es.um.atica.debezium.demo.domain.User;

public interface UserService {
    
    List<User> findAll();
    Optional<User> findById(String id);
    Optional<User> changeUserName(String id, String userName);
    Optional<User> createUser(String userName);
    Optional<User> createUserWithId(String userId,String userName);

}
