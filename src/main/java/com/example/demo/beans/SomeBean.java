package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties({"field3","password"})     //u can specify multiple properties here
public class SomeBean {
    private String field1;
    @JsonIgnore     //static filtering      one property
    private String password;    //don't want to pass it to user
    private String field3;
}
