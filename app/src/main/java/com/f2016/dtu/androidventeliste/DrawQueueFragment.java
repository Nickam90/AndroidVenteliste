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
    String teksten = "TEST";
    int x;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        minGrafik = new View(getActivity()) {


            @Override
            protected void onDraw(Canvas c) {

                int x = getWidth();
                int y = getHeight();
                int radius;
                radius = 100;
                Paint paint = new Paint();
                paint.setStyle(Paint.Style.FILL);
                paint.setColor(Color.WHITE);
                c.drawPaint(paint);
                // Use Color.parseColor to define HTML colors
                paint.setColor(Color.parseColor("#CD5C5C"));
                c.drawCircle(x / 2, y / 2, radius, paint);
            }
        };
        TableLayout tableLayout = new TableLayout(getActivity());

            tableLayout.addView(minGrafik);

        return tableLayout;
    }

}
