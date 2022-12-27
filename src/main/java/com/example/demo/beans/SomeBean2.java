package com.example.demo.beans;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonFilter("FilteredBean")     //dynamic filtering annotation, check also the controller
public class SomeBean2 {
    private String field1;
    private String password;    //don't want to pass it to user
    private String field3;
}
