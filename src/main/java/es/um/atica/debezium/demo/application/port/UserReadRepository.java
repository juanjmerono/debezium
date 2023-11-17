package es.um.atica.debezium.demo.application.port;

import java.util.List;
import java.util.Optional;

import es.um.atica.debezium.demo.domain.User;

public interface UserReadRepository {
    
    List<User> findAll();
    Optional<User> findById(String id);

}
