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
public class ListActivity extends ArrayAdapter<String> {
    private  String[] web;
    private final Activity context;
    private  String check;
    private int count;

    public ListActivity(Activity context, String[] web,String check) {
        super(context, R.layout.listview_layout,web);
        this.context = context;
        this.web = web;
        this.check=check;
        Log.e("sort","=======clicked======");

    }
    public ListActivity(Activity context,int count,String check) {
        super(context, R.layout.adapter_product);
        this.context=context;
        this.check=check;
        this.count=count;


    }

    @Override
    public int getCount() {
        return count;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView;

        if (check.contains("mycart") ){
            LayoutInflater inflater = context.getLayoutInflater();
            rowView=inflater.inflate(R.layout.adapter_product,null,true);
            TextView primary = (TextView) rowView.findViewById(R.id.tvProductName);
            primary.setText("Hello World");

        }else{
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.listview_layout, null, true);
            TextView primary = (TextView) rowView.findViewById(R.id.main);
            primary.setText(web[position]);

        }
        return rowView;
    }
}