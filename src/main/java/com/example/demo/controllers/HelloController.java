package com.example.demo.controllers;

import com.example.demo.beans.HelloWorldBean;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    //@RequestMapping(method = RequestMethod.GET, path = "/hello")
    @GetMapping(path = "/hello") //wypisz string na ekran
    public String helloUser() {
        return "Hello World";
    }

    //wypisz JSON na ekran
    @GetMapping(path = "/hello-bean")
    public HelloWorldBean helloUserBean() {
        return new HelloWorldBean("Hello Bean");
    }

    @GetMapping(path = "/hello-path-variable/{name}")
    public String helloPathVariable(@PathVariable String name) {
        return "Hello " + name;
    }
}

