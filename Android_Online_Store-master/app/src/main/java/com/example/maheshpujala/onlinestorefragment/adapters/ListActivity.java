package com.example.maheshpujala.onlinestorefragment.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maheshpujala.onlinestorefragment.R;

/**
 * Created by maheshpujala on 27/5/16.
 */
public class ListActivity extends ArrayAdapter<String> {
    private final String[] web;
    private final Activity context;
    private  final String check;

    public ListActivity(Activity context, String[] web,String check) {
        super(context, R.layout.listview_layout,web);
        this.context = context;
        this.web = web;
        this.check=check;
       }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listview_layout, null, true);
        TextView primary = (TextView) rowView.findViewById(R.id.main);
       // TextView secondary = (TextView) rowView.findViewById(R.id.sub);
        if (check.contains("show_count")){
            primary.setText(web[position]);
        //    secondary.setText("Only 13 left");
        }else{

            primary.setText(web[position]);
         //   secondary.setVisibility(View.GONE);

        }
        return rowView;
    }
}