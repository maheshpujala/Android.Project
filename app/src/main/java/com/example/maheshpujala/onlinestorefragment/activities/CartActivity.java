package com.example.maheshpujala.onlinestorefragment.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.maheshpujala.onlinestorefragment.R;

/**
 * Created by maheshpujala on 4/7/16.
 */
public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        TextView count =(TextView)findViewById(R.id.count);

        String value =getIntent().getStringExtra("cart_value");
        count.setText(value);
    }
}
