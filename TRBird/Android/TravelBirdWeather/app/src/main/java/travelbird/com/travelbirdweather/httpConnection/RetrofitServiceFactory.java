package travelbird.com.travelbirdweather.httpConnection;

import android.support.annotation.NonNull;


public abstract class RetrofitServiceFactory {

    private static RetrofitServiceFactory instance;

    public static void setInstance(RetrofitServiceFactory retrofitServiceFactory) {
        instance = retrofitServiceFactory;
    }

    public static RetrofitServiceFactory getInstance() {
        return instance;
    }

    public abstract
    @NonNull
    <T> T getService(@NonNull Class<T> service);
}
