package com.example.demo.jpa.controllers;

import com.example.demo.beans.User;
import com.example.demo.exceptions.UserNotExsitsException;
import com.example.demo.jpa.repositories.UserRepository;
import com.example.demo.service.UserDAOService;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaController {
    private UserRepository repository;

    public UserJpaController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/jpa/users")
    public List<User> getUsers() {
        return repository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> getOneUser(@PathVariable int id) {
        Optional<User> oneUser = repository.findById(id);
        if (oneUser.isEmpty()) {
            throw new UserNotExsitsException("The user with an id = " + id + " was not found");
        }
        EntityModel<User> entityModel = EntityModel.of(oneUser.get());
        WebMvcLinkBuilder link = WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getUsers());
        entityModel.add(link.withRel("all-users"));     //adding link to the entity model
        return entityModel;
    }

    @DeleteMapping(path = "/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")                              // appending variable
                .buildAndExpand(newUser.getId())            // initializing the variable
                .toUri();                                   //building the URI for a new resource
        return ResponseEntity.created(location).build();        //manipulates the return code
    }
}
