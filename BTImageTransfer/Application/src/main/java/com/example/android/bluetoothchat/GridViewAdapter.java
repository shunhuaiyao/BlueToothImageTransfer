package com.example.android.bluetoothchat;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by Shun-Huai.Yao on 6/2/2016.
 */
public class GridViewAdapter extends BaseAdapter {

    private List<ReceivedImage> values = MainActivity.datasource.getAllComments();
    private Context context;
    private FragmentManager ftm;

    public GridViewAdapter(Context context, FragmentManager ftm){
        this.context = context;
        this.ftm = ftm;
    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public Object getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image;
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        if(convertView==null){
            image = new ImageView(context);
            image.setLayoutParams(new GridView.LayoutParams(width/2, width/2));
            image.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }else{
            image = (ImageView)convertView;
        }
        image.setImageURI(Uri.parse(values.get(position).getUri()));
        final String imageID = String.valueOf(values.get(position).getId());
        final String imageSize = values.get(position).getImageSize();
        final String imageUri = values.get(position).getUri();
        final String imageLastTime = values.get(position).getLastUpdatedTime();
        image.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ID: " + imageID + "Size: " + imageSize + "URI: " + imageUri + "Time: " + imageLastTime);

                showInfo(imageSize, imageUri, imageLastTime);


            }
        });
        return image;
    }
    private void showInfo(String size,String uri,String time){
        FragmentTransaction ft = ftm.beginTransaction();
        Fragment prev = ftm.findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        ImageInfoDialogFragment f = new ImageInfoDialogFragment();
        Bundle args = new Bundle();
        args.putString("size", size);
        args.putString("uri", uri);
        args.putString("time", time);
        f.setArguments(args);
        f.show(ftm,"dialog");
    }

}
