package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    private final Unit units;
    private final Item item;

    public Channel(@JsonProperty("units") Unit units, @JsonProperty("item") Item item) {

        this.units = units;
        this.item = item;
    }

    public Unit getUnits() {
        return units;
    }

    public Item getItem() {
        return item;
    }
}
