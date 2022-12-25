package com.example.demo.controllers;

import com.example.demo.beans.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloController {

    private MessageSource messageSource;

    public HelloController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

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

    @GetMapping(path = "/hello-internationalized")
    public String helloInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(
                "good.mornig.message", null, "default message", locale);
    }
}

