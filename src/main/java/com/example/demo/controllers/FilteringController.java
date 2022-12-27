package com.example.demo.controllers;

import com.example.demo.beans.SomeBean;
import com.example.demo.beans.SomeBean2;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean filtering() {
        return new SomeBean("val1", "val2", "val3");
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue filteringDynamic() {
        SomeBean2 someBean = new SomeBean2("val1", "val2", "val3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("FilteredBean", filter); //name in the @JsonFilter annotation
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
