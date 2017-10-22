package com.evanrjohnso.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.evanrjohnso.myrestaurants.Constants;
import com.evanrjohnso.myrestaurants.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.findRestaurantsButton) Button mFindRestaurantsButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.appNameTextView) TextView mAppNameTextView;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                String userLocation = mLocationEditText.getText().toString();
                if (!(userLocation).equals("")) {
                    addToSharedPreferences(userLocation);
                }
                Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addToSharedPreferences(String userLocation) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, userLocation).apply();
    }
}
