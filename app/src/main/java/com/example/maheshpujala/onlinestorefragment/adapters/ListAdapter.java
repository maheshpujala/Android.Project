package com.example.maheshpujala.onlinestorefragment.adapters;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maheshpujala.onlinestorefragment.R;
import com.facebook.internal.BoltsMeasurementEventListener;

/**
 * Created by maheshpujala on 27/5/16.
 */
public class ListAdapter extends ArrayAdapter<String> {
    private  String[] web;
    private final Activity context;

    public ListAdapter(Activity context, String[] web) {
        super(context, R.layout.listview_layout,web);
        this.context = context;
        this.web = web;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView ;

        LayoutInflater inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.listview_layout, null, true);
        TextView primary = (TextView) rowView.findViewById(R.id.main);
        primary.setText(web[position]);


        return rowView;
    }
}