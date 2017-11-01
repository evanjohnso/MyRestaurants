package com.evanrjohnso.myrestaurants.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.evanrjohnso.myrestaurants.Constants;
import com.evanrjohnso.myrestaurants.R;
import com.evanrjohnso.myrestaurants.adapters.FirebaseRestaurantListAdapter;
import com.evanrjohnso.myrestaurants.adapters.FirebaseRestaurantViewHolder;
import com.evanrjohnso.myrestaurants.models.Restaurant;
import com.evanrjohnso.myrestaurants.util.OnStartDragListener;
import com.evanrjohnso.myrestaurants.util.SimpleItemTouchHelperCallback;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedRestaurantListActivity extends AppCompatActivity implements OnStartDragListener {
    private DatabaseReference mRestaurantReference;
    private FirebaseRestaurantListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_restaurants);
        ButterKnife.bind(this);

        setUpFireBaseAdapter();
    }

    private void setUpFireBaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

//        mRestaurantReference = FirebaseDatabase
//                .getInstance()
//                .getReference(Constants.FIREBASE_CHILD_USERS)
//                .child(uid);

        Query query = FirebaseDatabase
                .getInstance()
                .getReference(Constants.FIREBASE_CHILD_USERS)
                .child(uid)
                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        mFirebaseAdapter = new FirebaseRestaurantListAdapter(Restaurant.class,
                R.layout.restaurant_list_item_drag,
                FirebaseRestaurantViewHolder.class,
                query,
                this,
                this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

}
