package com.example.demo.controllers;

import com.example.demo.beans.Name;
import com.example.demo.beans.PersonV1;
import com.example.demo.beans.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    //by different urls:
    @GetMapping(path = "/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("John Smith");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("John", "Smith"));
    }

    //by params in url:
    @GetMapping(path = "/person",params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("John Smith");
    }

    @GetMapping(path = "/person")
    public PersonV2 getSecondVersionOfPersonRequestParameter() {
        return new PersonV2(new Name("John", "Smith"));
    }

    //by headers:
    @GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonRequestHeader() {
        return new PersonV1("John Smith");
    }


    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonRequestHeader() {
        return new PersonV2(new Name("John", "Smith"));
    }

    //by media type versioning (similar but instead of headers is produces)
}

