package travelbird.com.travelbirdweather;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import travelbird.com.travelbirdweather.model.Channel;
import travelbird.com.travelbirdweather.model.Condition;
import travelbird.com.travelbirdweather.model.Forecast;
import travelbird.com.travelbirdweather.model.Item;
import travelbird.com.travelbirdweather.model.Result;
import travelbird.com.travelbirdweather.model.Unit;
import travelbird.com.travelbirdweather.model.WeatherData;
import travelbird.com.travelbirdweather.model.WeatherReport;
import travelbird.com.travelbirdweather.model.WeatherResponse;

import static junit.framework.Assert.assertEquals;

public class WeatherDataShould {

    private static final String TEMPERATURE = "30";
    private static final int INDEX = 0;
    private static final String DATE = "30 Aug 2016";
    private static final String UNIT = "F";
    private static final String HI = "70";
    private static final String LOW = "76";
    private WeatherData weatherData;

    private WeatherReport weatherReport;

    @Before
    public void setUp() {

        Condition condition = new Condition(TEMPERATURE);
        Forecast forecast = new Forecast(DATE, HI, LOW);
        ArrayList<Forecast> list = new ArrayList<>();
        list.add(INDEX, forecast);
        Item item = new Item("title", condition, list);
        Channel channel = new Channel(new Unit(UNIT), item);
        Result result = new Result(channel);
        WeatherResponse response = new WeatherResponse(result);
        weatherReport = new WeatherReport(response);

        weatherData = new WeatherData(weatherReport);
    }


    @Test
    public void return_date() {
        assertEquals(weatherData.getDate(), DATE);
    }

    @Test
    public void return_temperature() {
        assertEquals(weatherData.getTemperature(), TEMPERATURE + UNIT);
    }

    @Test
    public void return_highest_temperature() {
        assertEquals(weatherData.getHigh(), HI + UNIT);
    }

    @Test
    public void return_lowest_temperature() {
        assertEquals(weatherData.getLow(), LOW + UNIT);
    }
}
