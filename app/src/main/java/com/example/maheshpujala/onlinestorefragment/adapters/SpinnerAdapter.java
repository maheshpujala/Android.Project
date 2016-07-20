package com.example.maheshpujala.onlinestorefragment.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;


/**
 * Created by maheshpujala on 15/7/16.
 */
public class SpinnerAdapter extends ArrayAdapter<String> {


    public SpinnerAdapter(Context context, int resource, int textViewResourceId, String[] objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @Override
    public int getCount() {
        // don't display last item. It is used as hint.
        int count = super.getCount();
        return count > 0 ? count - 1 : count;
    }
}