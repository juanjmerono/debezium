package es.um.atica.debezium.demo.adapters.jpatarget;

import org.springframework.stereotype.Repository;

import es.um.atica.debezium.demo.application.port.UserWriteRepository;
import es.um.atica.debezium.demo.domain.User;

@Repository(value = "userTargetWriteRepository")
public class UserTargetWriteRepositoryImpl implements UserWriteRepository {
    
    private UserTargetJPARepository userTargetJpaRepository;

    public UserTargetWriteRepositoryImpl(UserTargetJPARepository userTargetJpaRepository) {
        this.userTargetJpaRepository = userTargetJpaRepository;
    }

    @Override
    public User save(User u) {
        userTargetJpaRepository.save(UserTargetEntity.of(u));
        return u;
    }

}
