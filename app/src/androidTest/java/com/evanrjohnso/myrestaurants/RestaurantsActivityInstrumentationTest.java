package com.evanrjohnso.myrestaurants;

import android.support.test.rule.ActivityTestRule;

import com.evanrjohnso.myrestaurants.ui.RestaurantsActivity;

import org.junit.Rule;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.core.IsNot.not;

public class RestaurantsActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<RestaurantsActivity> activityTestRule =
            new ActivityTestRule<>(RestaurantsActivity.class);


}