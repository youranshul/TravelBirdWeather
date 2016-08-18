package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private final Result result;
    public WeatherResponse(@JsonProperty("results") Result result){

        this.result = result;
    }

    public Result getResult() {
        return result;
    }
}
