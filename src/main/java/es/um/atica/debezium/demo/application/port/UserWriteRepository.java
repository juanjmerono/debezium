package es.um.atica.debezium.demo.application.port;

import es.um.atica.debezium.demo.domain.User;

public interface UserWriteRepository {
    
    User save(User u);

}
