package com.example.maheshpujala.onlinestorefragment.adapters;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by maheshpujala on 6/6/16.
 */
public class SquareImageAdapter extends ImageView
{
    public SquareImageAdapter(Context context)
    {
        super(context);
    }

    public SquareImageAdapter(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public SquareImageAdapter(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); //Snap to width
    }
}