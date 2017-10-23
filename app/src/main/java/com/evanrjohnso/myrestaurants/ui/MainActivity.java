package com.evanrjohnso.myrestaurants.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.evanrjohnso.myrestaurants.Constants;
import com.evanrjohnso.myrestaurants.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.findRestaurantsButton)
    Button mFindRestaurantsButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.appNameTextView)
    TextView mAppNameTextView;
    @Bind(R.id.savedRestaurantsButton)
    Button mSavedRestaurantsButton;
    private DatabaseReference mSearchedLocationReference;
    private ValueEventListener mSearchedLocationReferenceListener;
//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);
        //pinpoint the location node
        mSearchedLocationReferenceListener = mSearchedLocationReference.addValueEventListener(new ValueEventListener() { //add listener of location data changessavedRestaurantsButton
            @Override
            public void onDataChange(DataSnapshot recentSnapshot) {
                for (DataSnapshot locationSnap : recentSnapshot.getChildren()) {
                    String location = locationSnap.getValue().toString();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSavedRestaurantsButton.setOnClickListener(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);
        mFindRestaurantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userLocation = mLocationEditText.getText().toString();


//                if (!(userLocation).equals("")) {
//                    addToSharedPreferences(userLocation);
//                }
                saveLocationToFirebase(userLocation);
                Intent intent = new Intent(MainActivity.this, RestaurantListActivity.class);
                intent.putExtra("location", userLocation);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }

    private void saveLocationToFirebase(String userLocation) {
        mSearchedLocationReference.push().setValue(userLocation);
    }

    @Override
    public void onClick(View v) {
        if (v == mSavedRestaurantsButton) {
            Intent intent = new Intent(MainActivity.this, SavedRestaurantListActivity.class);
            startActivity(intent);
        }
    }


//    private void addToSharedPreferences(String userLocation) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, userLocation).apply();
//    }
}
