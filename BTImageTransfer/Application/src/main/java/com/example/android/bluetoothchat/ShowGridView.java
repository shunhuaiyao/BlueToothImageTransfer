package com.example.android.bluetoothchat;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.GridView;

public class ShowGridView extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grid_view);

        FragmentManager ftm = getSupportFragmentManager();

        //get grid view reference
        GridView grid = (GridView)findViewById(R.id.image_grid);
        grid.setAdapter(new GridViewAdapter(getApplicationContext(),ftm));
    }

}
