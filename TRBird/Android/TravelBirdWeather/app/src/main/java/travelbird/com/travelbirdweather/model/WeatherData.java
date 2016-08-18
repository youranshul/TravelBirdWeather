package travelbird.com.travelbirdweather.model;

public class WeatherData implements WeatherDataContainer {

    private final WeatherReport weatherReport;

    public WeatherData(WeatherReport weatherReport) {
        this.weatherReport = weatherReport;
    }

    @Override
    public String getLocation() {
        return getItem().getTitle();
    }

    private Item getItem() {
        return weatherReport.getResponse().getResult().getChannel().getItem();
    }

    @Override
    public String getDate() {
        return getItem().getForecasts().get(0).getDate();
    }

    private String getTemperatureUnit() {
        return weatherReport.getResponse().getResult().getChannel().getUnits().getTemperature();
    }

    @Override
    public String getTemperature() {
        return getItem().getCondition().getTemp() + getTemperatureUnit();
    }

    @Override
    public String getHigh() {
        return getItem().getForecasts().get(0).getHigh() + getTemperatureUnit();
    }

    @Override
    public String getLow() {
        return getItem().getForecasts().get(0).getLow() + getTemperatureUnit();
    }
}
