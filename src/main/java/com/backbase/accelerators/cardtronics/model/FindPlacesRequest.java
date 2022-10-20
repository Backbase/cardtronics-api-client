package com.backbase.accelerators.cardtronics.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@Data
public class FindPlacesRequest {

    @JsonProperty("UserName")
    private String username;

    @JsonProperty("Password")
    private String password;

    @JsonProperty("Latitude")
    private String latitude;

    @JsonProperty("Longitude")
    private String longitude;

    @JsonProperty("Offset")
    private String offset;

    @JsonProperty("AddressLine")
    private String addressLine = EMPTY;

    @JsonProperty("City")
    private String city = EMPTY;

    @JsonProperty("State")
    private String state = EMPTY;

    @JsonProperty("PostalCode")
    private String postalCode = EMPTY;

    @JsonProperty("Country")
    private String country = EMPTY;

}
