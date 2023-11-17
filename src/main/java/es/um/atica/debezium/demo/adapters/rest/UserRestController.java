package es.um.atica.debezium.demo.adapters.rest;

import es.um.atica.debezium.demo.application.port.UserService;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserRestController {

    protected UserService userService;

    @GetMapping
    public List<UserDTO> listUsers() {
        return userService.findAll().stream().map(u->UserDTO.of(u)).collect(Collectors.toList());
    }
    
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO user) {
        UserDTO savedUser = UserDTO.of(userService.createUser(user.getUsername()).get());
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO user) {
        try {
            UserDTO savedUser = UserDTO.of(userService.changeUserName(id,user.getUsername()).orElseThrow(()->new NoSuchElementException()));
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (NoSuchElementException n) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
}
