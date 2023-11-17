package es.um.atica.debezium.demo.adapters.jpasource;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import es.um.atica.debezium.demo.application.port.UserReadRepository;
import es.um.atica.debezium.demo.domain.User;

@Repository(value = "userSourceReadRepository")
public class UserSourceReadRepositoryImpl implements UserReadRepository {
    
    private UserSourceJPARepository userSourceJpaRepository;

    public UserSourceReadRepositoryImpl(UserSourceJPARepository userSourceJpaRepository) {
        this.userSourceJpaRepository = userSourceJpaRepository;
    }

    @Override
    public List<User> findAll() {
        return userSourceJpaRepository.findAll().stream().map(UserSourceEntity::toModel).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        return userSourceJpaRepository.findById(id).map(UserSourceEntity::toModel);
    }

}
