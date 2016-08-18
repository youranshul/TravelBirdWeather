package travelbird.com.travelbirdweather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    private final Channel channel;

    public Result(@JsonProperty("channel") Channel channel) {

        this.channel = channel;
    }


    public Channel getChannel() {
        return channel;
    }
}
