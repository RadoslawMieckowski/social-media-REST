package com.example.demo.controllers;

import com.example.demo.beans.User;
import com.example.demo.exceptions.UserNotExsitsException;
import com.example.demo.service.UserDAOService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
    public EntityModel<User> getOneUser(@PathVariable int id) {
        User oneUser = service.getOneUser(id);
        if (oneUser == null) {
            throw new UserNotExsitsException("The user with an id = " + id + " was not found");
        }
        EntityModel<User> entityModel = EntityModel.of(oneUser);
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getUsers());
        entityModel.add(link.withRel("all-users"));     //adding link to the entity model
        return entityModel;
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable int id) {
        service.deleteUserById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = service.createUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")                              // appending variable
                .buildAndExpand(newUser.getId())            // initializing the variable
                .toUri();                                   //building the URI for a new resource
        return ResponseEntity.created(location).build();        //manipulates the return code
    }
}

