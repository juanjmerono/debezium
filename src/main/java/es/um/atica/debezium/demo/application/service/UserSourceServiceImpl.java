package es.um.atica.debezium.demo.application.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import es.um.atica.debezium.demo.application.port.UserReadRepository;
import es.um.atica.debezium.demo.application.port.UserWriteRepository;

@Service(value = "userSourceService")
public class UserSourceServiceImpl extends UserServiceImpl {

    public UserSourceServiceImpl(@Qualifier("userSourceWriteRepository") UserWriteRepository userWriteRepository, 
                           @Qualifier("userSourceReadRepository") UserReadRepository userReadRepository) {
        this.userReadRepository = userReadRepository;
        this.userWriteRepository = userWriteRepository;
    }
    
}
