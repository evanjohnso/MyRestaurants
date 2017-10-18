package com.evanrjohnso.myrestaurants;

import android.os.Build;
import android.widget.ListView;

import com.evanrjohnso.myrestaurants.ui.RestaurantsActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricGradleTestRunner.class)

public class RestaurantsActivityTest {
    private RestaurantsActivity activity;
    private ListView mRestaurantListView;


    @Test
    public void restaurantListViewPopulates() {
        assertNotNull(mRestaurantListView.getAdapter());
        assertEquals(mRestaurantListView.getAdapter().getCount(), 15);
    }
}