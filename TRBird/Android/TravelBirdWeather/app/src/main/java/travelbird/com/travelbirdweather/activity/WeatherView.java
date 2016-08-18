package travelbird.com.travelbirdweather.activity;

public interface WeatherView {
    void showProgressDialog();

    void hideProgressDialog();

    void showLocation(String location);

    void showDate(String date);

    void showTemperature(String temperature);

    void showHighestTemperature(String hTemperature);

    void showLowestTemperature(String lTemperature);

    void showErrorMessage(String message);

    void showContentView();
}
