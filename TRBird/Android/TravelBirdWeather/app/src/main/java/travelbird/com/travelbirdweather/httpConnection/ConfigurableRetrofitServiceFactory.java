package travelbird.com.travelbirdweather.httpConnection;

import android.support.annotation.NonNull;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class ConfigurableRetrofitServiceFactory extends RetrofitServiceFactory {

    private static final String BASE_URL = "https://query.yahooapis.com/";
    // private OkHttpClient client;
    private okhttp3.OkHttpClient.Builder client;

    public ConfigurableRetrofitServiceFactory() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new okhttp3.OkHttpClient.Builder();
        client.addInterceptor(logging);

        /*client.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                // Customize the request
                Request.Builder builder = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .header("Accept-Charset", "UTF-8");

                Request request = builder.method(original.method(), original.body())
                        .build();

                Response response = chain.proceed(request);

                // Customize or return the response
                return response;
            }
        });
*/
    }

    @NonNull
    @Override
    public <T> T getService(@NonNull Class<T> service) {

        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(BASE_URL);
        builder.addConverterFactory(JacksonConverterFactory.create(Mapper.get()));
        builder.client(client.build());
        builder.build();

        return builder.build().create(service);
    }
}
