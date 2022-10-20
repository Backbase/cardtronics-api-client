
package com.backbase.accelerators.cardtronics.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attributes {

    @JsonProperty("AcceptDeposit")
    private String acceptDeposit;

    @JsonProperty("AdditionalDetails1")
    private String additionalDetails1;

    @JsonProperty("AdditionalDetails2")
    private String additionalDetails2;

    @JsonProperty("AddressLine")
    private String addressLine;

    @JsonProperty("AtmStatus")
    private String atmStatus;

    @JsonProperty("BusinessType")
    private String businessType;

    @JsonProperty("CashWithdrawLimit")
    private Integer cashWithdrawLimit;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("CountryCode")
    private String countryCode;

    @JsonProperty("Distance")
    private String distance;

    @JsonProperty("DistanceMeters")
    private String distanceMeters;

    @JsonProperty("DistanceUnit")
    private String distanceUnit;

    @JsonProperty("Fax")
    private String fax;

    @JsonProperty("ImageText")
    private String imageText;

    @JsonProperty("ImageURL")
    private String imageURL;

    @JsonProperty("Latitude")
    private String latitude;

    @JsonProperty("LimitedHours")
    private String imitedHours;

    @JsonProperty("LobbyDays1")
    private String lobbyDays1;

    @JsonProperty("LobbyDays2")
    private String lobbyDays2;

    @JsonProperty("LobbyDays3")
    private String lobbyDays3;

    @JsonProperty("LobbyDays4")
    private String lobbyDays4;

    @JsonProperty("LobbyDays5")
    private String lobbyDays5;

    @JsonProperty("LobbyDays6")
    private String lobbyDays6;

    @JsonProperty("LobbyDays7")
    private String lobbyDays7;

    @JsonProperty("LobbyHours1")
    private String lobbyHours1;

    @JsonProperty("LobbyHours2")
    private String lobbyHours2;

    @JsonProperty("LobbyHours3")
    private String lobbyHours3;

    @JsonProperty("LobbyHours4")
    private String lobbyHours4;

    @JsonProperty("LobbyHours5")
    private String lobbyHours5;

    @JsonProperty("LobbyHours6")
    private String lobbyHours6;

    @JsonProperty("LobbyHours7")
    private String lobbyHours7;

    @JsonProperty("LocationID")
    private String locationID;

    @JsonProperty("LocationName")
    private String locationName;

    @JsonProperty("LocationType")
    private String locationType;

    @JsonProperty("LocationTypeLabel")
    private String locationTypeLabel;

    @JsonProperty("Longitude")
    private String longitude;

    @JsonProperty("MapUrl")
    private String mapUrl;

    @JsonProperty("MobileCashIn")
    private Object mobileCashIn;

    @JsonProperty("MobileCashOut")
    private Object mobileCashOut;

    @JsonProperty("MobileValue")
    private String mobileValue;

    @JsonProperty("MultipleDenomination")
    private Object multipleDenomination;

    @JsonProperty("NetworkID")
    private String networkID;

    @JsonProperty("Open24Hours")
    private Object open24Hours;

    @JsonProperty("Phone")
    private String phone;

    @JsonProperty("PostalCode")
    private String postalCode;

    @JsonProperty("RestrictedAccess")
    private String restrictedAccess;

    @JsonProperty("RetailOutlet")
    private String retailOutlet;

    @JsonProperty("StateCode")
    private String stateCode;

    @JsonProperty("SurchargeFree")
    private String surchargeFree;

    @JsonProperty("TerminalID")
    private String terminalID;

    @JsonProperty("WebsiteUrl")
    private String websiteUrl;

    private Map<String, Object> additionalProperties = new HashMap<>();
}
