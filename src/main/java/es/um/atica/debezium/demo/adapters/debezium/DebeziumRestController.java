package es.um.atica.debezium.demo.adapters.debezium;

import java.util.Collections;

import org.apache.kafka.common.serialization.Serde;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.um.atica.debezium.demo.application.port.UserService;
import io.debezium.serde.DebeziumSerdes;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dbz")
@Slf4j
public class DebeziumRestController {
    
    private final UserService userService;
    private final Serde<UserDebezium> serdeUserAfter;
    //private final Serde<UserDebezium> serdeUserBefore;
    private final Serde<String> serdeOp;

    public DebeziumRestController(@Qualifier("userTargetService") UserService userService) {
        this.userService = userService;
        serdeUserAfter = DebeziumSerdes.payloadJson(UserDebezium.class);
        serdeUserAfter.configure(Collections.singletonMap("from.field", "after"), false);
        /*serdeUserBefore = DebeziumSerdes.payloadJson(UserDebezium.class);
        serdeUserBefore.configure(Collections.singletonMap("from.field", "before"), false);*/
        serdeOp = DebeziumSerdes.payloadJson(String.class);
        serdeOp.configure(Collections.singletonMap("from.field", "op"), false);
    }

    @PostMapping
    public void change(@RequestBody String payload) {
        //log.info(payload);
        UserDebezium user = serdeUserAfter.deserializer().deserialize("payload",payload.getBytes());
        String op = serdeOp.deserializer().deserialize("payload",payload.getBytes());
        switch (op) {
            case "c":
                log.info(String.format("Creating new user: [%s] %s",user.getUserid(),user.getUsername()));
                userService.createUserWithId(user.getUserid(),user.getUsername());
                break;
            case "u":
                log.info(String.format("Updating user: [%s] %s",user.getUserid(),user.getUsername()));
                userService.changeUserName(user.getUserid(),user.getUsername());
                break;
            default:
                break;
        }

    }

}
