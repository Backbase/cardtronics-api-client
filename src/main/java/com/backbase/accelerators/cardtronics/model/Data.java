
package com.backbase.accelerators.cardtronics.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@lombok.Data
public class Data {

    @JsonProperty("StartAddress")
    private String startAddress;

    @JsonProperty("StartLatitude")
    private String startLatitude;

    @JsonProperty("StartLongitude")
    private String startLongitude;

    private String locationCount;

    private List<Location> locations = null;

    private Map<String, Object> additionalProperties = new HashMap<>();

}
