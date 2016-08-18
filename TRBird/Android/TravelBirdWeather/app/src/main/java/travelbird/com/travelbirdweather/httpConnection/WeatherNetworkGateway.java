package travelbird.com.travelbirdweather.httpConnection;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import travelbird.com.travelbirdweather.httpConnection.RetrofitServiceFactory;
import travelbird.com.travelbirdweather.model.WeatherData;
import travelbird.com.travelbirdweather.model.WeatherReport;
import travelbird.com.travelbirdweather.presentation.WeatherPresenter;

public class WeatherNetworkGateway {

    private static final String TAG = "weather";
    private static final String SELECT_QUERY = "select * from weather.forecast where woeid in(select woeid from geo.places(1)";
    private static final String WHERE_CLAUSE = " where text =";
    private static final String END_STRING = ")";
    private final String env = "store://datatables.org/alltableswithkeys";
    private final String format = "json";

    private final WeatherService service;
    private WeatherPresenter.Listener listener;

    public WeatherNetworkGateway() {
        service = RetrofitServiceFactory.getInstance().getService(WeatherService.class);
    }

    public void getWeatherForecast(String city, WeatherPresenter.Listener listener) {
        String query = SELECT_QUERY + WHERE_CLAUSE + "\'" + city + "\'" + END_STRING;
        this.setListener(listener);
        Call<WeatherReport> call = service.getWeather(query, format, env);
        call.enqueue(callback);
    }

    private Callback<WeatherReport> callback = new Callback<WeatherReport>() {
        @Override
        public void onResponse(Call<WeatherReport> call, Response<WeatherReport> response) {
            WeatherReport weatherReport = response.body();
            if (listener != null) {
                WeatherData data = new WeatherData(weatherReport);
                listener.onSuccess(data);
            }
            Log.d(TAG, "sucess");
        }

        @Override
        public void onFailure(Call<WeatherReport> call, Throwable t) {
            Log.d(TAG, "failure");
            if (listener != null) {
                listener.onFailure(t.getMessage());
            }
        }
    };

    private void setListener(WeatherPresenter.Listener listener) {
        this.listener = listener;
    }

    interface WeatherService {

        @GET("/v1/public/yql")
        Call<WeatherReport> getWeather(@Query("q") String path, @Query("format") String format,
                                       @Query("env") String env);

    }
}
