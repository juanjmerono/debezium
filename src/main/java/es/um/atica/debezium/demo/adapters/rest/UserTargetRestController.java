package es.um.atica.debezium.demo.adapters.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.debezium.demo.application.port.UserService;
import org.springframework.beans.factory.annotation.Qualifier;

@RestController
@RequestMapping("/usertarget")
public class UserTargetRestController  extends UserRestController {

    public UserTargetRestController(@Qualifier("userTargetService") UserService userTargetService) {
        this.userService = userTargetService;
    }

}
