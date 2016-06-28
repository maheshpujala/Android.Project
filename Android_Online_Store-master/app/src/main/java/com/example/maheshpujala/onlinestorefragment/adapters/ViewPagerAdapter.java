package com.example.maheshpujala.onlinestorefragment.adapters;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by maheshpujala on 23/6/16.
 */
public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    String[] product_image_url;
    ImageView imageView;

    public ViewPagerAdapter(Context context, String[] url) {
        this.context = context;
        this.product_image_url = url;

    }

    @Override
    public int getCount() {
        return product_image_url.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        imageView = new ImageView(context);
//            int padding = context.getResources().getDimensionPixelSize(R.dimen.padding_medium);
//            imageView.setPadding(padding, padding, padding, padding);
//            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.with(context).load("http://192.168.3.48:2490"+product_image_url[position]).into(imageView);
        //      imageView.setImageResource(mImages[position]);
        ((ViewPager) container).addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }

}
