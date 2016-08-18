package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private final String title;
    private final Condition condition;
    private final List<Forecast> forecasts;

    public Item(@JsonProperty("title") String title, @JsonProperty("condition") Condition condition,
                @JsonProperty("forecast") List<Forecast> forecasts) {
        this.title = title;
        this.condition = condition;
        this.forecasts = forecasts;
    }

    public String getTitle() {
        return title;
    }

    public Condition getCondition() {
        return condition;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }
}

