package com.example.tclarke.lostdogprogram;

import android.content.ClipData;
import android.content.Context;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseImageView;
import com.parse.ParseObject;

/**
 * Created by t.clarke on 06/02/2015.
 */

    public class LostDogAdapter extends ArrayAdapter<LostDog> {

    private Context mContext;
    private List<LostDog> mTasks;

    public LostDogAdapter(Context context, List<LostDog> objects) {
        super(context, R.layout.item_view_children, objects);
        this.mContext = context;
        this.mTasks = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(mContext);
            convertView = mLayoutInflater.inflate(R.layout.item_view_children, null);
        }

        LostDog dog = mTasks.get(position);

        TextView descriptionView = (TextView) convertView.findViewById(R.id.desctxt);
        descriptionView.setText(dog.getDescription());


        ParseImageView imageView = (ParseImageView) convertView.findViewById(R.id.dogView);
        imageView.setParseFile(dog.getDogimg());
System.out.println("Done Image");
        imageView.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, com.parse.ParseException e) {
                System.out.println("Check");
                if (e != null)
                    e.printStackTrace();
            }
        });


        return convertView;
    }

}



