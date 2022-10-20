package com.backbase.accelerators.cardtronics.client;

import com.backbase.accelerators.cardtronics.CardtronicsRequestSettings;
import com.backbase.accelerators.cardtronics.exception.CardtronicsApiClientException;
import com.backbase.accelerators.cardtronics.model.FindPlacesRequest;
import com.backbase.accelerators.cardtronics.model.Location;
import com.backbase.accelerators.cardtronics.model.LocationType;
import com.backbase.accelerators.cardtronics.model.Places;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;
import java.util.Set;

import static com.backbase.accelerators.cardtronics.model.LocationType.ALL;
import static java.net.http.HttpRequest.BodyPublishers.ofString;
import static java.util.Objects.isNull;

@Slf4j
public class CardtronicsClient {

    private final CardtronicsRequestSettings cardtronicsRequestSettings;
    private final HttpClient httpClient;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public CardtronicsClient(CardtronicsRequestSettings cardtronicsRequestSettings, HttpClient httpClient) {
        Objects.requireNonNull(
                cardtronicsRequestSettings,
                "CardtronicsClient cannot be initialized because CardtronicsRequestSettings is null.");

        Objects.requireNonNull(
                httpClient,
                "CardtronicsClient cannot be initialized because HttpClient is null.");

        this.cardtronicsRequestSettings = cardtronicsRequestSettings;
        this.httpClient = httpClient;
    }

    @SneakyThrows
    public Places findPlaces(
            BigDecimal latitude,
            BigDecimal longitude,
            BigDecimal radius,
            LocationType locationType) {

        log.debug("Entering findPlaces() with latitude={}; longitude={}; radius={}; locationType={};",
                latitude,
                longitude,
                radius,
                locationType);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(cardtronicsRequestSettings.getBaseUrl()))
                .header("Content-Type", "application/json")
                .POST(ofString(toJsonString(createFindPlacesRequest(latitude, longitude, radius))))
                .build();

        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String responseString = httpResponse.body();

        log.debug("MX API response - statusCode: {}; body: {}", httpResponse.statusCode(), responseString);

        checkForErrors(httpResponse);
        Places places = toPojo(responseString, Places.class);
        return filterByLocationType(places, locationType);
    }

    private Places filterByLocationType(
            Places places,
            LocationType locationType) {

        if (isNull(locationType) || locationType == ALL) {
            log.debug("No filtering applied. Returning all {} branch & ATM locations", getLocationCount(places));
            return places;
        }

        log.debug("Filtering locations by type: {}", locationType);
        places.getData()
                .getLocations()
                .removeIf(location -> !hasMatchingLocationType(location, locationType));

        log.debug("Location filtering complete. Returning {} {} locations", getLocationCount(places), locationType);

        // Ensuring the locationCount property aligns with the actual list of locations after filtering as been applied.
        places.getData().setLocationCount(String.valueOf(getLocationCount(places)));
        return places;
    }

    private boolean hasMatchingLocationType(Location location, LocationType locationType) {
        return location.getAttributes().getLocationType().equalsIgnoreCase(locationType.getValue());
    }

    private int getLocationCount(Places places) {
        return places.getData().getLocations().size();
    }

    private FindPlacesRequest createFindPlacesRequest(BigDecimal latitude, BigDecimal longitude, BigDecimal radius) {
        FindPlacesRequest findPlacesRequest = new FindPlacesRequest();
        findPlacesRequest.setUsername(cardtronicsRequestSettings.getUsername());
        findPlacesRequest.setPassword(cardtronicsRequestSettings.getPassword());
        findPlacesRequest.setLatitude(latitude.toString());
        findPlacesRequest.setLongitude(longitude.toString());
        findPlacesRequest.setOffset(radius.toString());

        log.debug("Created FindPlacesRequest: {}", findPlacesRequest);
        return findPlacesRequest;
    }

    @SneakyThrows
    private String toJsonString(Object obj) {
        return objectMapper.writeValueAsString(obj);
    }

    public void checkForErrors(HttpResponse<String> httpResponse) {
        final Set<Integer> successCodes = Set.of(200, 201, 202, 204);

        if (!successCodes.contains(httpResponse.statusCode())) {
            var cardtronicsApiClientException = new CardtronicsApiClientException(httpResponse.body());
            cardtronicsApiClientException.setStatusCode(httpResponse.statusCode());

            throw cardtronicsApiClientException;
        }
    }

    @SneakyThrows
    public <T> T toPojo(String responseString, Class<T> clazz) {
        return objectMapper.readValue(responseString, clazz);
    }
}
