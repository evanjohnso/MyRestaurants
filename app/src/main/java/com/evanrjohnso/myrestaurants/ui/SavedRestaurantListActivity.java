package com.evanrjohnso.myrestaurants.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.evanrjohnso.myrestaurants.Constants;
import com.evanrjohnso.myrestaurants.R;
import com.evanrjohnso.myrestaurants.adapters.FirebaseRestaurantViewHolder;
import com.evanrjohnso.myrestaurants.models.Restaurant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRestaurantListActivity extends AppCompatActivity {
    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        String userId = FirebaseAuth.getInstance()
                .getCurrentUser().getUid();

        mRestaurantReference = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_USERS)
                .child(userId);
        setUpFireBaseAdapter();
    }

    private void setUpFireBaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Restaurant, FirebaseRestaurantViewHolder>(Restaurant.class, R.layout.restaurant_list_item_drag, FirebaseRestaurantViewHolder.class, mRestaurantReference) {
            @Override
            protected void populateViewHolder(FirebaseRestaurantViewHolder viewHolder, Restaurant model, int position) {
                viewHolder.bindRestaurant(model);
            }

        };
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
