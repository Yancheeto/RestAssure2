package com.cybertek.tests.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AllSpartans extends Spartan {
    @JsonProperty("")
    private List<Spartan> spartans;


}
