package com.evanrjohnso.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.evanrjohnso.myrestaurants.Constants;
import com.evanrjohnso.myrestaurants.R;
import com.evanrjohnso.myrestaurants.adapters.RestaurantListAdapter;
import com.evanrjohnso.myrestaurants.models.Restaurant;
import com.evanrjohnso.myrestaurants.services.YelpService;

import okhttp3.Call;
import okhttp3.Callback;
import java.io.IOException;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class RestaurantListActivity extends AppCompatActivity {
    public static final String TAG = RestaurantListActivity.class.getSimpleName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
    }

}
