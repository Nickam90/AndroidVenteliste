package com.f2016.dtu.androidventeliste.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomArrayAdapter extends ArrayAdapter<String> {
    public CustomArrayAdapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (position == UserSession.getQueueNumber() - 1) {
            view.setBackgroundColor(Color.parseColor(UserSession.getTriageColorCode()));
        } else {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        return view;
    }
}