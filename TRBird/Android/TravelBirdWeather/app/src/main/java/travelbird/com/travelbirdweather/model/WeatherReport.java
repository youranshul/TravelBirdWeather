package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherReport {

    private final WeatherResponse response;

    public WeatherReport(@JsonProperty("query") WeatherResponse response) {

        this.response = response;

    }

    public WeatherResponse getResponse() {
        return response;
    }
}
