package travelbird.com.travelbirdweather.activity;

import android.app.Application;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;

import travelbird.com.travelbirdweather.httpConnection.ConfigurableRetrofitServiceFactory;
import travelbird.com.travelbirdweather.httpConnection.RetrofitServiceFactory;

public class WeatherApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ConfigurableRetrofitServiceFactory factory = new ConfigurableRetrofitServiceFactory();

        RetrofitServiceFactory.setInstance(factory);
    }
}
