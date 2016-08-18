package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Unit {

    private final String temperature;

    public Unit(@JsonProperty("temperature") String temperature) {

        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }
}
