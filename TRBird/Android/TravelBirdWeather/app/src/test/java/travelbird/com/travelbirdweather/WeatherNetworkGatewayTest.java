package travelbird.com.travelbirdweather;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import travelbird.com.travelbirdweather.httpConnection.WeatherNetworkGateway;
import travelbird.com.travelbirdweather.model.WeatherDataContainer;
import travelbird.com.travelbirdweather.presentation.WeatherPresenter;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;

public class WeatherNetworkGatewayTest {

    public static WeatherNetworkGateway mockNetwork() {
        WeatherNetworkGateway gateway = Mockito.mock(WeatherNetworkGateway.class);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                WeatherPresenter.Listener listener = (WeatherPresenter.Listener) invocation.getArguments()[1];
                WeatherDataContainer container = Mockito.mock(WeatherDataContainer.class);
                listener.onSuccess(container);
                return null;
            }
        }).when(gateway).getWeatherForecast(anyString(), anyListener());
        return gateway;
    }

    public static WeatherPresenter.Listener anyListener() {
        return any();
    }

}
