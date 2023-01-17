package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity(name = "posts_details")
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @JsonProperty("post_description")
    @Size(min = 10, message = "post's description must consist of at least 10 characters")
    @Column(name = "post_description")
    private String description;

//    @Column(name = "publisher")       //can't be used with ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)   //dont't want to retrieve user right away
    @JsonIgnore                         //dont't want to display user field in the response
    private User user;
}
