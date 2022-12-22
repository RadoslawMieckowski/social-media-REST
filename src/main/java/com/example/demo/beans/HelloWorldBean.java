package com.example.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class HelloWorldBean {
    @Getter
    @Setter
    private String message;
}
