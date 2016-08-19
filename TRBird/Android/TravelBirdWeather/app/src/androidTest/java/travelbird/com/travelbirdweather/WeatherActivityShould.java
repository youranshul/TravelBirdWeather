package travelbird.com.travelbirdweather;


import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import travelbird.com.travelbirdweather.activity.WeatherActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class WeatherActivityShould {
    private static final String BASIC_SAMPLE_PACKAGE = "travelbird.com.travelbirdweather";
    private static final int LAUNCH_TIMEOUT = 5000;
    private UiDevice mDevice;

    @Rule
    public ActivityTestRule<WeatherActivity> mActivityRule = new ActivityTestRule(WeatherActivity.class);

    @Before
    public void setUp() {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void see_enter_city_text_area() {
        onView(withId(R.id.place_autocomplete_search_input)).check(matches(isDisplayed()));

    }

    @Test
    public void enter_city_name_display_search_results() throws UiObjectNotFoundException {
        onView(withId(com.google.android.gms.R.id.place_autocomplete_search_input)).perform(click());

        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);

        UiObject searchField = mDevice.findObject(new UiSelector()
                .text("Search").className("android.widget.EditText"));
        if (searchField.exists()) {
            Log.e("UI :", "exists");
            searchField.setText("bang");

            mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                    LAUNCH_TIMEOUT);

            UiSelector listSelector = new UiSelector().className("android.support.v7.widget.RecyclerView");
            mDevice.findObject(listSelector);

            mDevice.pressBack();
            mDevice.pressBack();
        }
    }

    @Test
    public void get_weather_condition_for_city() throws UiObjectNotFoundException {
        onView(withId(com.google.android.gms.R.id.place_autocomplete_search_input)).perform(click());

        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);

        UiObject searchField = mDevice.findObject(new UiSelector()
                .text("Search").className("android.widget.EditText"));
        if (searchField.exists()) {
            Log.e("UI :", "exists");
            searchField.setText("bang");

            mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                    LAUNCH_TIMEOUT);

            UiSelector listSelector = new UiSelector().className("android.support.v7.widget.RecyclerView");


            UiObject recycler = mDevice.findObject(listSelector);

            if (recycler.exists()) {
                UiSelector child = new UiSelector().className("android.widget.TextView").text("Bangalore");
                recycler.getChild(child).click();
            }

            mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                    LAUNCH_TIMEOUT);


            mDevice.findObject(new UiSelector().resourceId("travelbird.com.travelbirdweather:id/title"));

            mDevice.pressBack();
            mDevice.pressBack();
        }
    }

    @Test
    public void show_error_for_invalid_city_text() throws UiObjectNotFoundException {
        onView(withId(com.google.android.gms.R.id.place_autocomplete_search_input)).perform(click());

        mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                LAUNCH_TIMEOUT);

        UiObject searchField = mDevice.findObject(new UiSelector()
                .text("Search").className("android.widget.EditText"));
        if (searchField.exists()) {
            searchField.setText("tyyuuuuuuuui");

            mDevice.wait(Until.hasObject(By.pkg(BASIC_SAMPLE_PACKAGE).depth(0)),
                    LAUNCH_TIMEOUT);

            UiSelector error = new UiSelector().resourceId("com.google.android.gms:id/error");
            mDevice.findObject(error);

            mDevice.pressBack();
            mDevice.pressBack();
        }
    }

}
