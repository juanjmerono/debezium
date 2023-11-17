package es.um.atica.debezium.demo.adapters.jpatarget;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.um.atica.debezium.demo.application.port.UserReadRepository;
import es.um.atica.debezium.demo.domain.User;

@Repository(value = "userTargetReadRepository")
public class UserTargetReadRepositoryImpl implements UserReadRepository {
    
    private UserTargetJPARepository userTargetJpaRepository;

    public UserTargetReadRepositoryImpl(UserTargetJPARepository userTargetJpaRepository) {
        this.userTargetJpaRepository = userTargetJpaRepository;
    }

    @Override
    public List<User> findAll() {
        return userTargetJpaRepository.findAll().stream().map(UserTargetEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return userTargetJpaRepository.findById(id).map(UserTargetEntity::toModel);
    }

}
