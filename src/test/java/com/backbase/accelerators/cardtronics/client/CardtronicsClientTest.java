package com.backbase.accelerators.cardtronics.client;

import com.backbase.accelerators.cardtronics.CardtronicsRequestSettings;
import com.backbase.accelerators.cardtronics.model.LocationType;
import com.backbase.accelerators.cardtronics.model.Places;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CardtronicsClientTest {

    @Mock
    private CardtronicsRequestSettings cardtronicsRequestSettings;

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private CardtronicsClient cardtronicsClient;

    @Test
    public void should_return_atm_and_branch_locations() throws Exception {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(cardtronicsRequestSettings.getBaseUrl()).thenReturn("http://fake-url.com");
        when(cardtronicsRequestSettings.getUsername()).thenReturn("fakeUsername");
        when(cardtronicsRequestSettings.getPassword()).thenReturn("fakePassword");
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(loanJsonFromFile());

        Places response = cardtronicsClient.findPlaces(
                new BigDecimal("39.148790985922034"),
                new BigDecimal("-77.06576623062483"), new BigDecimal("15.00"), LocationType.ALL);

        verify(cardtronicsRequestSettings).getBaseUrl();
        verify(cardtronicsRequestSettings).getPassword();
        verify(cardtronicsRequestSettings).getBaseUrl();
        verify(httpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        verify(httpResponse, times(2)).statusCode();
        verify(httpResponse).body();

        assertEquals(80, response.getData().getLocations().size());

    }

    @Test
    public void should_return_only_atm_locations() throws Exception {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(cardtronicsRequestSettings.getBaseUrl()).thenReturn("http://fake-url.com");
        when(cardtronicsRequestSettings.getUsername()).thenReturn("fakeUsername");
        when(cardtronicsRequestSettings.getPassword()).thenReturn("fakePassword");
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(loanJsonFromFile());

        Places response = cardtronicsClient.findPlaces(
                new BigDecimal("39.148790985922034"),
                new BigDecimal("-77.06576623062483"), new BigDecimal("15.00"), LocationType.ATM);

        verify(cardtronicsRequestSettings).getBaseUrl();
        verify(cardtronicsRequestSettings).getPassword();
        verify(cardtronicsRequestSettings).getBaseUrl();
        verify(httpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        verify(httpResponse, times(2)).statusCode();
        verify(httpResponse).body();

        assertEquals(62, response.getData().getLocations().size());
        verifyLocationType(response, LocationType.ATM);

    }

    public void should_return_only_branch_locations() throws Exception {
        HttpResponse<String> httpResponse = mock(HttpResponse.class);

        when(cardtronicsRequestSettings.getBaseUrl()).thenReturn("http://fake-url.com");
        when(cardtronicsRequestSettings.getUsername()).thenReturn("fakeUsername");
        when(cardtronicsRequestSettings.getPassword()).thenReturn("fakePassword");
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);
        when(httpResponse.statusCode()).thenReturn(200);
        when(httpResponse.body()).thenReturn(loanJsonFromFile());

        Places response = cardtronicsClient.findPlaces(
                new BigDecimal("39.148790985922034"),
                new BigDecimal("-77.06576623062483"), new BigDecimal("15.00"), LocationType.BRANCH);

        verify(cardtronicsRequestSettings).getBaseUrl();
        verify(cardtronicsRequestSettings).getPassword();
        verify(cardtronicsRequestSettings).getBaseUrl();
        verify(httpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
        verify(httpResponse, times(2)).statusCode();
        verify(httpResponse).body();

        assertEquals(18, response.getData().getLocations().size());
        verifyLocationType(response, LocationType.BRANCH);
    }

    @SneakyThrows
    private String loanJsonFromFile() {
        String fileName = "src/test/resources/json-samples/cardtronics-get-places-response.json";
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private void verifyLocationType(Places places, LocationType locationType) {
        places.getData()
                .getLocations()
                .forEach(location -> assertEquals(locationType.getValue(), location.getAttributes().getLocationType()));
    }
}