package com.example.tclarke.lostdogprogram;

import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.location.Location;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseImageView;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


    public class LostDogsActivity extends Activity implements OnItemClickListener {
        private EditText mTaskInput;
        private ListView mListView;
        private LostDogAdapter mAdapter;
        private LocationManager mLocationManager;
        private final LocationListener mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateData();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lost_dogs);

            mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5 * 60 * 1000,
                    0, mLocationListener);
            mAdapter = new LostDogAdapter(this, new ArrayList<LostDog>());

          // mTaskInput = (EditText) findViewById(R.id.distance);
            mListView = (ListView) findViewById(R.id.posts_listview);

            mListView.setAdapter(mAdapter);

            mListView.setOnItemClickListener(this);
            updateData();
        }

        public void updateData(){
            ParseQuery<LostDog> query = ParseQuery.getQuery(LostDog.class);
            //query.whereEqualTo("user", ParseUser.getCurrentUser());
            query.setCachePolicy(ParseQuery.CachePolicy.CACHE_THEN_NETWORK);
            query.findInBackground(new FindCallback<LostDog>() {
                @Override
                public void done(List<LostDog> tasks, ParseException error) {
                    if(tasks != null){
                        mAdapter.clear();
                        for (int i = 0; i < tasks.size(); i++) {
                            mAdapter.add(tasks.get(i));
                        }
                    }
                }
            });
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            LostDog dog = mAdapter.getItem(position);
            TextView dogDescription = (TextView) view.findViewById(R.id.desctxt);
            ParseImageView imgV = (ParseImageView) view.findViewById(R.id.dogView);

        }

        @Override
        protected void onDestroy() {
            super.onDestroy();

        }

        @Override
        protected void onResume() {
            super.onResume();

        }

    }
