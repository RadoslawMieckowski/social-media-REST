package com.example.demo.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private Integer age;
    private String name;
    private LocalDate birthDate;
}
