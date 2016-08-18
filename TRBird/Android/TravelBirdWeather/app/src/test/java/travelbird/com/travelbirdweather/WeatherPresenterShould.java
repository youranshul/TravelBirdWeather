package travelbird.com.travelbirdweather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import travelbird.com.travelbirdweather.activity.WeatherView;
import travelbird.com.travelbirdweather.httpConnection.WeatherNetworkGateway;
import travelbird.com.travelbirdweather.presentation.WeatherPresenter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeatherPresenterShould {

    private static final String CITY = "Bangalore";

    private WeatherNetworkGateway weatherNetworkGateway = WeatherNetworkGatewayTest.mockNetwork();

    private WeatherPresenter presenter;

    @Mock
    private WeatherView view;


    @Before
    public void setUp() {
        presenter = new WeatherPresenter(weatherNetworkGateway);
        presenter.onCreated(view);

        presenter.onItemClick(CITY);
    }

    @Test
    public void send_weather_request() {

        verify(weatherNetworkGateway).getWeatherForecast(eq(CITY), WeatherNetworkGatewayTest.anyListener());
    }

    @Test
    public void show_location() {

        verify(view).showLocation(anyString());
    }


    @Test
    public void show_date() {

        verify(view).showDate(anyString());
    }

    @Test
    public void show_progress() {

        verify(view).showProgressDialog();
    }

    @Test
    public void show_temperature() {
        verify(view).showTemperature(anyString());
    }

    @Test
    public void show_highest_temperature() {
        verify(view).showHighestTemperature(anyString());
    }

    @Test
    public void show_lowest_temperature() {
        verify(view).showLowestTemperature(anyString());
    }
}
