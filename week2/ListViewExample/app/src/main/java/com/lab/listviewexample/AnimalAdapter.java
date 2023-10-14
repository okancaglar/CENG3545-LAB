package com.lab.listviewexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AnimalAdapter extends BaseAdapter {

    private LayoutInflater minFlater;
    private List<Animal> animals;

    public AnimalAdapter(Activity activity, List<Animal> animals) {
        this.minFlater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.animals = animals;
    }

    @Override
    public int getCount() {
        return animals.size();
    }

    @Override
    public Object getItem(int i) {
        return animals.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowview;

        rowview = minFlater.inflate(R.layout.listview_row, null);
        TextView textView = (TextView) rowview.findViewById(R.id.label);

        ImageView imageview = (ImageView) rowview.findViewById(R.id.pic);

        Animal animal = animals.get(i);

        textView.setText(animal.getType());
        imageview.setImageResource(animal.getPicID());

        return rowview;
    }
}
