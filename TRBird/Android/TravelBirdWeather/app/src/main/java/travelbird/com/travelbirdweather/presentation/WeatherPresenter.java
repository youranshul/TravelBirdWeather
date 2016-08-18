package travelbird.com.travelbirdweather.presentation;

import travelbird.com.travelbirdweather.activity.EmptyWeatherView;
import travelbird.com.travelbirdweather.activity.WeatherView;
import travelbird.com.travelbirdweather.httpConnection.WeatherNetworkGateway;
import travelbird.com.travelbirdweather.model.WeatherDataContainer;

public class WeatherPresenter {

    private final WeatherNetworkGateway weatherNetworkGateway;
    private WeatherView view;

    public WeatherPresenter(WeatherNetworkGateway weatherNetworkGateway) {
        this.weatherNetworkGateway = weatherNetworkGateway;
        view = new EmptyWeatherView();
    }

    public void onItemClick(String cityName) {
        showProgressDialog();
        weatherNetworkGateway.getWeatherForecast(cityName, listener);
    }

    private void showProgressDialog() {
        view.showProgressDialog();
    }


    public void onCreated(WeatherView view) {
        this.view = view;
    }

    private Listener listener = new Listener() {
        @Override
        public void onSuccess(WeatherDataContainer container) {
            view.hideProgressDialog();
            view.showContentView();

            view.showLocation(container.getLocation());
            view.showDate(container.getDate());

            view.showTemperature(container.getTemperature());
            view.showHighestTemperature(container.getHigh());
            view.showLowestTemperature(container.getLow());
        }

        @Override
        public void onFailure(String error) {
            view.hideProgressDialog();
            view.showErrorMessage(error);
        }
    };

    public void onDestroy() {
        view = new EmptyWeatherView();
    }


    public interface Listener {

        void onSuccess(WeatherDataContainer container);

        void onFailure(String error);
    }
}
