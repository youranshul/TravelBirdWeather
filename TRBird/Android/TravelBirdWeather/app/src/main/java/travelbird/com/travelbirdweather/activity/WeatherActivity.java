package travelbird.com.travelbirdweather.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import travelbird.com.travelbirdweather.R;
import travelbird.com.travelbirdweather.httpConnection.WeatherNetworkGateway;
import travelbird.com.travelbirdweather.presentation.WeatherPresenter;

//add UI testing

public class WeatherActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, WeatherView {

    private static final String TAG = "weather";
    private static final String KEY_PLACE = "key_plcae";
    private WeatherPresenter presenter;
    private String cityName;

    @Bind(R.id.content_view)
    View ContentView;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.date)
    TextView date;

    @Bind(R.id.temp)
    TextView temp;

    @Bind(R.id.hi_temp)
    TextView highTemp;

    @Bind(R.id.lo_temp)
    TextView lowTemp;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.getting_weather));

        presenter = new WeatherPresenter(new WeatherNetworkGateway());
        presenter.onCreated(this);

        if (savedInstanceState != null && savedInstanceState.containsKey(KEY_PLACE)) {
            cityName = savedInstanceState.getString(KEY_PLACE);
            presenter.onItemClick(cityName);
        }

        addGoogleClient();
        addPlaceAutoCompleteFragment();

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    @Override
    public void showLocation(String location) {
        title.setText(location);
    }

    @Override
    public void showDate(String dateAndDay) {
        date.setText(dateAndDay);
    }

    @Override
    public void showTemperature(String temperature) {
        temp.setText(temperature);
    }

    @Override
    public void showHighestTemperature(String hTemperature) {
        highTemp.setText(hTemperature);
    }

    @Override
    public void showLowestTemperature(String lTemperature) {
        lowTemp.setText(lTemperature);
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(ContentView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showContentView() {
        ContentView.setVisibility(View.VISIBLE);
    }

    private void addGoogleClient() {
        new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        String errorMsg = connectionResult.getErrorMessage();
        errorMsg = errorMsg == null ? getString(R.string.generic_error) : errorMsg;
        Snackbar.make(ContentView, errorMsg, Snackbar.LENGTH_SHORT).show();
    }

    private void addPlaceAutoCompleteFragment() {
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_CITIES)
                .build();


        autocompleteFragment.setFilter(typeFilter);
        autocompleteFragment.setHint(getString(R.string.search_hint));
        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName());
                cityName = place.getName().toString();
                presenter.onItemClick(cityName);
            }

            @Override
            public void onError(Status status) {
                String errorMsg = status.getStatusMessage() == null ? getString(R.string.generic_error) :
                        status.getStatusMessage();
                Snackbar.make(ContentView, errorMsg, Snackbar.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_PLACE, cityName);
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
