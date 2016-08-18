package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Condition {
    private final String temp;


    public Condition(@JsonProperty("temp") String temp) {
        this.temp = temp;
    }

    public String getTemp() {
        return temp;
    }
}
