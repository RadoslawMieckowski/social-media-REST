package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor//required for Builder
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "user_details")
public class User {
    @JsonProperty("user_id") // doesn't relate to databases!
    @Id                     // relates to the databases!
    @GeneratedValue
    private Integer id;

    @Size(min = 2, message = "user's name must be at least 2 letters long")
    @JsonProperty("user_name")
    private String name;

    @Past(message = "user's birthDate must be in the past")
    @JsonProperty("user_birth_date")
    @Column(name = "birth_date")    //necessary to fix an unknown bug (could not find column birthDate by the name)
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Post> posts;
}
