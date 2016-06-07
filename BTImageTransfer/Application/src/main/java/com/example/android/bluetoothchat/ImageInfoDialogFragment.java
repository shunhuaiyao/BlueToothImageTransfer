package com.example.android.bluetoothchat;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.StringDef;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shun-Huai.Yao on 6/2/2016.
 */
public class ImageInfoDialogFragment extends DialogFragment {

    private ImageView imageView;
    private TextView imageSize;
    private TextView imageUri;
    private TextView imageLastTime;

    private String size;
    private String uri;
    private String time;

//    public static ImageInfoDialogFragment newInstace(String size, String uri, String time) {
//
//        ImageInfoDialogFragment f = new ImageInfoDialogFragment();
//
//        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putString("size", size);
//        args.putString("uri", uri);
//        args.putString("time", time);
//        f.setArguments(args);
//
//        return f;
//
//    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // this line is missing
        size = getArguments().getString("size");
        uri = getArguments().getString("uri");
        time = getArguments().getString("time");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //System.out.println("tag = " + getTag()); // tag which is from acitivity which started this fragment

        View view = inflater.inflate(R.layout.imageinfo_fragment, container, false);
        getDialog().setTitle("Info");

        imageSize = (TextView)view.findViewById(R.id.imageSize);
        imageUri = (TextView)view.findViewById(R.id.imageUri);
        imageLastTime = (TextView)view.findViewById(R.id.imageTime);
        imageView = (ImageView)view.findViewById(R.id.selectedimage);

        imageView.setImageURI(Uri.parse(uri));
        imageSize.setText(size);
        imageUri.setText(uri);
        imageLastTime.setText(time);

        return view;
    }
}