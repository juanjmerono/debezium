package es.um.atica.debezium.demo.application.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.um.atica.debezium.demo.application.port.UserReadRepository;
import es.um.atica.debezium.demo.application.port.UserWriteRepository;

@Service(value = "userTargetService")
public class UserTargetServiceImpl extends UserServiceImpl {

    public UserTargetServiceImpl(@Qualifier("userTargetWriteRepository") UserWriteRepository userWriteRepository, 
                           @Qualifier("userTargetReadRepository") UserReadRepository userReadRepository) {
        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
    }
    
}
