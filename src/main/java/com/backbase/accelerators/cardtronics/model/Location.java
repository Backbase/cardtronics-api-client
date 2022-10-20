
package com.backbase.accelerators.cardtronics.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Location {

    private AdditionalServices additionalServices;
    private Address address;
    private Attributes attributes;
    private String distance;
    private String distanceUnit;
    private Hours hours;
    private String id;
    private Services services;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
