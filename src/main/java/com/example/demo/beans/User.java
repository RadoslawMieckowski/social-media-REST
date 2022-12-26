package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("user_id")
    private Integer id;
    @Size(min = 2, message = "user's name must be at least 2 letters long")
    @JsonProperty("user_name")
    private String name;
    @Past(message = "user's birthDate must be in the past")
    @JsonProperty("user_birth_date")
    private LocalDate birthDate;
}
