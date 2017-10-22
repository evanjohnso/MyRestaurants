package com.evanrjohnso.myrestaurants;

import android.support.test.rule.ActivityTestRule;

import com.evanrjohnso.myrestaurants.ui.MainActivity;

import org.junit.Rule;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class MainActivityInstrumentationTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

}
