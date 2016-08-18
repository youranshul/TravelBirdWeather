package travelbird.com.travelbirdweather;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class WeatherActivityShould {
    private static final String BASIC_SAMPLE_PACKAGE = "travelbird.com.travelbirdweather";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

    @Before
    public void startMainActivityFromHomeScreen() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertThat(launcherPackage, notNullValue());
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        // Launch the app
        Context context = InstrumentationRegistry.getContext();
        final Intent intent = context.getPackageManager()
                .getLaunchIntentForPackage(BASIC_SAMPLE_PACKAGE);
        // Clear out any previous instances
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);

        // Wait for the app to appear
        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);
    }

    @Test
    public void see_enter_city_text_area() {
        /*
        onView(withId(R.id.place_autocomplete_search_input)).check(matches(isDisplayed()));*/

        UiObject area = mDevice.findObject(new UiSelector()
                .text("Enter city"));
        if (area.exists()) {
            try {
                area.click();
                mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                        LAUNCH_TIMEOUT);

                UiSelector selector = new UiSelector()
                        .text("Search").className("android.widget.EditText");
                UiObject searchField = mDevice.findObject(selector);
                if (searchField.exists()) {
                    Log.e("UI :", "exists");
                    searchField.setText("bang");

                    mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                            LAUNCH_TIMEOUT);
                }

                UiObject listItem = mDevice.findObject(new UiSelector().className("android.widget.TextView").text("Bangalore"));
                if (listItem.exists()) {
                    listItem.click();

                    mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                            LAUNCH_TIMEOUT);
                }
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }


    }


   /* private static final String BANGALORE = "bang";
    @Rule
    public ActivityTestRule<WeatherActivity> mActivityRule = new ActivityTestRule(WeatherActivity.class);


   *//* @Test
    public void see_enter_city_text_area() {
        onView(withId(R.id.place_autocomplete_search_input)).check(matches(isDisplayed()));
    }*//*

    @Test
    public void enter_city_name_text_area() {
        onView(withId(com.google.android.gms.R.id.place_autocomplete_search_input)).perform(ViewActions.click());


        ArrayList<View> views = new ArrayList<>();
         getAutoCompleteFragment().getView().findViewsWithText(views,"Enter city",View.FIND_VIEWS_WITH_TEXT);

        Log.e("Espresso :", views.get(0).getId() + "");
       // onView(withId(id)).check(matches(isDisplayed()));
    }

    private PlaceAutocompleteFragment getAutoCompleteFragment() {

        return (PlaceAutocompleteFragment) mActivityRule.getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
    }*/
}
