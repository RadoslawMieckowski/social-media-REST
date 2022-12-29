package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "posts_details")
public class Post {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "post_description")
    private String description;

//    @Column(name = "publisher")       //can't be used with ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)   //dont't want to retrieve user right away
    @JsonIgnore                         //dont't want to display user field in the response
    private User user;
}
