package com.example.maheshpujala.onlinestorefragment.adapters;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maheshpujala.onlinestorefragment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maheshpujala on 6/6/16.
 */
public class GridAdapter extends BaseAdapter
        {
private List<Item> items = new ArrayList<Item>();
private LayoutInflater inflater;

public GridAdapter(Context context)
        {
        inflater = LayoutInflater.from(context);

        items.add(new Item("Image 1", R.drawable.q1));
        items.add(new Item("Image 2", R.drawable.q2));
        items.add(new Item("Image 3", R.drawable.q3));
        items.add(new Item("Image 4", R.drawable.q4));
        items.add(new Item("Image 5", R.drawable.q5));
            items.add(new Item("Image 5", R.drawable.q6));

            items.add(new Item("Image 5", R.drawable.q7));

            items.add(new Item("Image 5", R.drawable.q8));

            items.add(new Item("Image 5", R.drawable.q9));

            items.add(new Item("Image 5", R.drawable.q10));

            items.add(new Item("Image 5", R.drawable.q11));

        }

@Override
public int getCount() {
        return items.size();
        }

@Override
public Object getItem(int i)
        {
        return items.get(i);
        }

@Override
public long getItemId(int i)
        {
        return items.get(i).drawableId;
        }

@Override
public View getView(int i, View view, ViewGroup viewGroup)
        {
        View v = view;
        ImageView picture;
        TextView name;

        if(v == null)
        {
        v = inflater.inflate(R.layout.gridview_item, viewGroup, false);
        v.setTag(R.id.picture, v.findViewById(R.id.picture));
        v.setTag(R.id.text, v.findViewById(R.id.text));
        }

        picture = (ImageView)v.getTag(R.id.picture);
        name = (TextView)v.getTag(R.id.text);

        Item item = (Item)getItem(i);

        picture.setImageResource(item.drawableId);
        name.setText(item.name);

        return v;
        }

private class Item
{
    final String name;
    final int drawableId;

    Item(String name, int drawableId)
    {
        this.name = name;
        this.drawableId = drawableId;
    }
}
}