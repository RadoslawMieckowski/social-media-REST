package com.example.demo.controllers;

import com.example.demo.beans.User;
import com.example.demo.service.UserDAOService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
}

