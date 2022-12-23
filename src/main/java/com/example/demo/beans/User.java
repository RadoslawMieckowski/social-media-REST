package com.example.demo.beans;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor//required for Builder
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Integer id;
    @Size(min = 2, message = "user's name must be at least 2 letters long")
    private String name;
    @Past(message = "user's birthDate must be in the past")
    private LocalDate birthDate;
}
