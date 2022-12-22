package com.example.demo.controllers;

import com.example.demo.beans.User;
import com.example.demo.service.UserDAOService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private UserDAOService service;
    public UserController(UserDAOService service) {
        this.service = service;
    }
    @GetMapping(path = "/users")
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @GetMapping(path = "/users/{id}")
    public User getUserById(@PathVariable int id) {
        return service.getUserById(id - 1);
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        User newUser = service.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")      // appending variable
                .buildAndExpand(newUser.getId())    // initializing the variable
                .toUri();       //building the URI for a new resource
        return ResponseEntity.created(location).build();        //manipulates the return code
    }
}

