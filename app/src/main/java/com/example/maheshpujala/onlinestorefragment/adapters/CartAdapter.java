package com.example.maheshpujala.onlinestorefragment.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.maheshpujala.onlinestorefragment.R;

/**
 * Created by maheshpujala on 14/7/16.
 */
public class CartAdapter  extends BaseAdapter {
    private final Activity context;
    private int cartItems;

    public CartAdapter(Activity context, int cartItems) {
        this.context = context;
        this.cartItems = cartItems;
    }

    @Override
    public int getCount() {

        return cartItems;  }

    @Override
    public Object getItem(int position) {

        return null;
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView;
        LayoutInflater inflater = context.getLayoutInflater();
        rowView = inflater.inflate(R.layout.adapter_product, null, true);
        TextView productname = (TextView) rowView.findViewById(R.id.tvProductName);
        productname.setText("HELLO WORLD");

        return rowView;
    }
}