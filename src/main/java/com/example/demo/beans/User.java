package com.example.demo.beans;

import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@AllArgsConstructor//required for Builder
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Integer id;
    private String name;
    private LocalDate birthDate;
}
