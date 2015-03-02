package com.example.tclarke.lostdogprogram;

/**
 * Created by t.clarke on 25/02/2015.
 */
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseObject;

import java.util.ArrayList;

public class ItemListFragment extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, container, savedInstanceState);

        ArrayList<LostDog> items = new ArrayList<LostDog>();
        for (int i = 0; i < 10; i++) {

           LostDog item = new LostDog();
            item.getContactNumber();
            items.add(item);
            System.out.println(item.contactNumber);
        }


        setListAdapter(new LostDogAdapter(getActivity(), items));

        return v;
    }
}