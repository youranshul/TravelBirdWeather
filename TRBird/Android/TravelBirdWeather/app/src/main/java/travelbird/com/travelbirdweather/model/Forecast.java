package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

    private final String date;
    private final String high;
    private final String low;

    public Forecast(@JsonProperty("date") String date,
                    @JsonProperty("high") String high,
                    @JsonProperty("low") String low) {

        this.date = date;
        this.high = high;
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public String getHigh() {
        return high;
    }

    public String getLow() {
        return low;
    }
}
