package com.cb.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Person {

    public String name;

    public String height;

    @JsonProperty("hair_color")
    public String hairColor;

}
