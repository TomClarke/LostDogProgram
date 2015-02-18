package com.example.tclarke.lostdogprogram;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

/**
 * Created by t.clarke on 06/02/2015.
 */
@ParseClassName("LostDogs")
public class LostDog extends ParseObject {

    int id;
    String name;
    String description;
    String contactNumber;
    Boolean found;
    Date createdAt;
    ParseFile photo;
    ParseGeoPoint geoLocation;
    Date dateTime;

    public int getID() {
        return getInt("objectid");
    }

    public void setID(int ident) {
        put("objectid",ident);
    }

    public String getName() {
        return getString("PetName");
    }

    public void setName(String petName) {
       put("PetName",petName);
    }


    public String getDescription() {
        return getString("Description");
    }

    public void setDescription(String description) {
        put("Description",description);
    }


    public String getContactNumber() {
        return getString("contactNumber");
    }

    public void setContactNumber(String contactNumber) {
        put("ContactNumber",contactNumber);
    }


    public Date getDateTime() {
        return getDate("DateTime");
    }

    public void setDateTime(Date date) {
        put("DateTime",date);
    }


    public Boolean getfound() {
        return getBoolean("Found");
    }

    public void setFound(Boolean found) {
       put("Found",found);
    }


    public ParseFile getDogimg() {
        return getParseFile("Photo");
    }

    public void setDogimg(ParseFile dogimg) {
        put("Photo",dogimg);
    }


    public ParseGeoPoint getLocation() {
        return getParseGeoPoint("geoLocation");
    }

    public void setLocation(ParseGeoPoint location) {
        put("geoLocation",location);
    }


    public static ParseQuery<LostDog> getQuery() {
        return ParseQuery.getQuery(LostDog.class);
    }
}


