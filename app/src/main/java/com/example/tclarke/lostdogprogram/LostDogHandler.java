package com.example.tclarke.lostdogprogram;

import java.util.Date;
import java.util.LinkedList;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;

/**
 * Created by t.clarke on 06/02/2015.
 */

public class LostDogHandler extends ParseObject{

   public LinkedList<LostDog> dogList= new LinkedList<LostDog>();

    public void createNewDog(String name, String des, String contact, Date date, boolean found, ParseFile img, ParseGeoPoint location){

        LostDog createDog = new LostDog();

        createDog.setName(name);
        createDog.setDescription(des);
        createDog.setContactNumber(contact);
        createDog.setDateTime(date);
        createDog.setFound(found);
        createDog.setDogimg(img);
        createDog.setLocation(location);

        dogList.add(createDog);

    }

public void removeDog(int id){

    for(int i = 0 ; dogList.size() > i; i++){

        if(dogList.get(i).getID()==id){
            dogList.remove(i);
        }

    }
}

//link the doglist to the server
    public LinkedList<LostDog> updateDogList(){

        return dogList;
    }


}
