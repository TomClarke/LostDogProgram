package com.example.tclarke.lostdogprogram;

/**
 * Created by t.clarke on 25/02/2015.
 */
public class ItemView {

}
/*
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;

import java.io.File;
import java.text.ParseException;

public class ItemView extends RelativeLayout {
    private TextView mTitleTextView;
    private TextView mDescriptionTextView;
    private ImageView mImageView;
    private ProgressDialog progressDialog;

    public static ItemView inflate(ViewGroup parent) {
        ItemView itemView = (ItemView)LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);
        return itemView;
    }

    public ItemView(Context c) {
        this(c, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.item_view_children, this, true);
        setupChildren();
    }

    private void setupChildren() {
        mTitleTextView = (TextView) findViewById(R.id.petnamelist);
        mDescriptionTextView = (TextView) findViewById(R.id.distance);
        mImageView = (ImageView) findViewById(R.id.image);
    }

     public void setItem(LostDog lostdog) {

         ParseQueryAdapter.QueryFactory<LostDog> factory =
                 new ParseQueryAdapter.QueryFactory<LostDog>() {
                     public ParseQuery<LostDog> create() {
                         Location myLoc = (currentLocation == null) ? lastLocation : currentLocation;
                         ParseQuery<LostDog> query = LostDog.getQuery();
                         query.include("user");
                         query.orderByDescending("createdAt");
                         query.whereWithinKilometers("location", geoPointFromLocation(myLoc), radius
                                 * METERS_PER_FEET / METERS_PER_KILOMETER);
                         query.setLimit(MAX_POST_SEARCH_RESULTS);
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
                 contentView.setText(post.getText());
                 usernameView.setText(post.getUser().getUsername());
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

         // Set up the handler for an item's selection
         postsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 final LostDog item = postsQueryAdapter.getItem(position);
                 selectedPostObjectId = item.getObjectId();
             }
         });
    }

            public ImageView getImageView() {
                return mImageView;
            }

            public TextView getTitleTextView() {
                return mTitleTextView;
            }

            public TextView getDescriptionTextView() {
                return mDescriptionTextView;
            }
        }*/
