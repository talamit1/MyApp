package com.example.talam.myapplication;

import android.widget.ArrayAdapter;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * Created by talam on 02/06/2017.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] names;
    private final Integer[] imageId;


    public CustomList(Activity context,
                      String[] names, Integer[] imageId) {

        super(context, R.layout.list_cell, names);
        this.context = context;
        this.names = names;
        this.imageId = imageId;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_cell, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(names[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
