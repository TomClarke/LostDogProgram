package com.example.tclarke.lostdogprogram;

import android.content.ClipData;
import android.content.Context;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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
import com.squareup.picasso.Picasso;

/**
 * Created by t.clarke on 06/02/2015.
 */

    public class LostDogAdapter extends ArrayAdapter<LostDog> {

    private Context mContext;
    private List<LostDog> mTasks;
    private static Context applicationContext;

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


        ImageView imageView = (ImageView) convertView.findViewById(R.id.dogView);
        ParseFile postImage = dog.getDogimg();
        Uri imageUri = Uri.parse(postImage.getUrl());
        Picasso.with(mContext).load(imageUri.toString()).into(imageView);
        //imageView.se        tParseFile(dog.getDogimg());

        return convertView;
    }







        /*imageView.loadInBackground(new GetDataCallback() {
            @Override
            public void done(byte[] data, com.parse.ParseException e) {
                System.out.println("Check");
                if (e != null)
                    e.printStackTrace();
            }
        });
        return convertView;

    }*/




}



