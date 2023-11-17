package es.um.atica.debezium.demo.adapters.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.debezium.demo.application.port.UserService;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/usersource")
public class UserSourceRestController extends UserRestController {

    public UserSourceRestController(@Qualifier("userSourceService") UserService userSourceService) {
        this.userService = userSourceService;
    }

}
