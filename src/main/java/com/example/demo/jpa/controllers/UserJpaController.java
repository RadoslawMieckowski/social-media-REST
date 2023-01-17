package com.example.demo.jpa.controllers;

import com.example.demo.beans.Post;
import com.example.demo.beans.User;
import com.example.demo.exceptions.UserNotExsitsException;
import com.example.demo.jpa.repositories.PostRepository;
import com.example.demo.jpa.repositories.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@AllArgsConstructor
@RestController
public class UserJpaController {
    private UserRepository userRepository;
    private PostRepository postRepository;

    @GetMapping(path = "/jpa/users")
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/jpa/users/{id}")
    public EntityModel<User> getOneUser(@PathVariable int id) {
        Optional<User> oneUser = userRepository.findById(id);
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
        userRepository.deleteById(id);
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User newUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")                              // appending variable
                .buildAndExpand(newUser.getId())            // initializing the variable
                .toUri();                                   //building the URI for a new resource
        return ResponseEntity.created(location).build();        //manipulates the return code
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getAllPostsOfUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExsitsException("The user with an id = " + id + " was not found");
        }
        return user.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/posts")
    public ResponseEntity<Post> createPost(@PathVariable int id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotExsitsException("The user with an id = " + id + " was not found");
        }
        post.setUser(user.get());
        Post newPost = postRepository.save(post);//dlaczego nie zapiszemy też usera

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
