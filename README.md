# cardtronics-api-client

This project contains a client library that can be used to call Cardtronics branch and ATM locator API.

This project can be utilized in your Backbase integration services as a Maven dependency. Simply include the following
maven coordinates in the `dependency` section of your service's `pom.xml`

```aidl
    <groupId>com.backbase.accelerators</groupId>
    <artifactId>cardtronics-api-client</artifactId>
    <version>1.0.0</version>
```

## Build this project

From the root directory of this project, run:

```mvn clean install```

This will compile the project.

### Example usage - Defining `application.yml` configuration:

```yaml
cardtronics:
  client:
    baseUrl: https://clsws.locatorsearch.net/Rest/LocationApi.svc/FindLocations
    username: ${CARDTRONICS_USERNAME}
    password: ${CARDTRONICS_PASSWORD}

```

```java
@Data
@Configuration
@ConfigurationProperties("cardtronics.client")
public class CardtronicsProperties {

    private String baseUrl;
    private String username;
    private String password;
    
    public CardtronicsRequestSettings toCardtronicsRequestSettings() {
      CardtronicsRequestSettings cardtronicsRequestSettings = new CardtronicsRequestSettings();
      cardtronicsRequestSettings.setBaseUrl(baseUrl);
      cardtronicsRequestSettings.setUsername(username);
      cardtronicsRequestSettings.setPassword(password);
    }
}
```

### Example usage - Defining a Spring Bean in Your Integration Service:

```java
@Configuration
public class CardTronicsApiClientConfiguration {

    @Bean
    public CardtronicsClient cardtronicsClient(CardtronicsProperties cardtronicsProperties) {
        return new CardtronicsClient(
                cardtronicsProperties.toCardtronicsRequestSettings(),
                HttpClient.newHttpClient());
    }
}
```
