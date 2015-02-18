package com.example.tclarke.lostdogprogram;

import android.location.LocationListener;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.location.Location;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;


public class LostDogsActivity extends FragmentActivity implements LocationListener {
    private Location savedLocation;
    private Location currentLocation;
    // Adapter for the Parse query
    private ParseQueryAdapter<LostDog> postsQueryAdapter;
    private String selectedPostObjectId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_dogs);

        // Set up a customized query
        ParseQueryAdapter.QueryFactory<LostDog> factory =
                new ParseQueryAdapter.QueryFactory<LostDog>() {
                    public ParseQuery<LostDog> create() {
                        Location myLoc = (currentLocation == null) ? savedLocation : currentLocation;
                        ParseQuery<LostDog> query = LostDog.getQuery();
                        query.include("user");
                        query.include("petName");
                        query.include("Geolocation");
                        query.include("Photo");

                        query.orderByDescending("createdAt");

                        return query;
                    }
                };
        // Set up the query adapter
        postsQueryAdapter = new ParseQueryAdapter<LostDog>(this, factory) {
            @Override
            public View getItemView(LostDog post, View view, ViewGroup parent) {
                if (view == null) {
                    view = View.inflate(getContext(), R.layout.lostdog_post_item, null);
                }
                TextView contentView = (TextView) view.findViewById(R.id.content_view);
                TextView usernameView = (TextView) view.findViewById(R.id.username_view);
                contentView.setText(post.getName());
                usernameView.setText(post.getDescription());
                return view;
            }
        };
// Disable automatic loading when the adapter is attached to a view.
        postsQueryAdapter.setAutoload(false);

        // Disable pagination, we'll manage the query limit ourselves
        postsQueryAdapter.setPaginationEnabled(false);

        // Attach the query adapter to the view
        ListView postsListView = (ListView) findViewById(R.id.posts_listview);
        postsListView.setAdapter(postsQueryAdapter);
        doListQuery();
         /* // Set up the handler for an item's selection
        postsListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final LostDog item = postsQueryAdapter.getItem(position);
                selectedPostObjectId = item.getObjectId();
            }
            });
    }
             mapFragment.getMap().animateCamera(
                        CameraUpdateFactory.newLatLng(new LatLng(item.getLocation().getLatitude(), item
                                .getLocation().getLongitude())), new CancelableCallback() {
                            public void onFinish() {
                                Marker marker = mapMarkers.get(item.getObjectId());
                                if (marker != null) {
                                    marker.showInfoWindow();
                                }
                            }

                            public void onCancel() {
                            }
                        });
                Marker marker = mapMarkers.get(item.getObjectId());
                if (marker != null) {
                    marker.showInfoWindow();
                }
            }
        });
*/

    }

    private void doListQuery() {
        Location myLoc = (currentLocation == null) ? savedLocation : currentLocation;
        if (myLoc != null) {
            postsQueryAdapter.loadObjects();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lost_dogs, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {

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

}
