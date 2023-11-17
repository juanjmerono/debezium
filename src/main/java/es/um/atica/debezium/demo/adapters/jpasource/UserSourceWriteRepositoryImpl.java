package es.um.atica.debezium.demo.adapters.jpasource;

import org.springframework.stereotype.Repository;

import es.um.atica.debezium.demo.application.port.UserWriteRepository;
import es.um.atica.debezium.demo.domain.User;

@Repository(value = "userSourceWriteRepository")
public class UserSourceWriteRepositoryImpl implements UserWriteRepository {
    
    private UserSourceJPARepository userSourceJpaRepository;

    public UserSourceWriteRepositoryImpl(UserSourceJPARepository userSourceJpaRepository) {
        this.userSourceJpaRepository = userSourceJpaRepository;
    }

    @Override
    public User save(User u) {
        userSourceJpaRepository.save(UserSourceEntity.of(u));
        return u;
    }

}
