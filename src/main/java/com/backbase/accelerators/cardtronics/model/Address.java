
package com.backbase.accelerators.cardtronics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class Address {

    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("Long")
    private String longitude;

    private String city;
    private String country;
    private String state;
    private String street;
    private String zip;

    private Map<String, Object> additionalProperties = new HashMap<>();

}
