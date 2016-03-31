package com.f2016.dtu.androidventeliste;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;


public class DrawQueueFragment extends Fragment{


    View minGrafik;
    String teksten = "TEST TEST";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        minGrafik = new View(getActivity()) {

            @Override
            protected void onDraw(Canvas c) {
                Paint tekstStregtype = new Paint();
                tekstStregtype.setColor(Color.GREEN);
                tekstStregtype.setTextSize(24);
                c.rotate(23, 0, 0); // getWidth()/2, getHeight()/2// rotér 23 grader om midten
                c.drawText(teksten, 0, 20, tekstStregtype);
            }
        };



        return minGrafik;
    }

}
