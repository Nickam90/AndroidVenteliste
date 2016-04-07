package com.f2016.dtu.androidventeliste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;


public class DrawQueueFragment extends Fragment{


    View minGrafik;

    int k;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        minGrafik = new View(getActivity()) {


            @Override
            protected void onDraw(Canvas c) {
                //int quelength=10;
                //int queNumber = 3;
                    int quelength = UserSession.getQueueLenght();
                    int queNumber = UserSession.getQueueNumber();
                    int x = getWidth();
                    int y = getHeight();
                    int placementx = 35;
                    int placementy = 50;
                    int radius;
                    radius = 15;
                    Paint paint = new Paint();
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.WHITE);
                    c.drawPaint(paint);
                    // Use Color.parseColor to define HTML colors

                    for (k=1;k<=quelength;k++) {

                        if (k == queNumber){
                            // Declare an object of type Bitmap
                            //Bitmap bitmapBob;
                            // Initialize it using the bob.png file
                           // bitmapBob = BitmapFactory.decodeResource(this.getResources(), R.drawable.bob);
                            // Now draw bob to our canvas
                            //c.drawBitmap(bitmapBob, placementx, placementy, paint);
                            paint.setColor(Color.parseColor("#009ce8"));
                        }

                        if (k!=queNumber) {

                            paint.setColor(Color.parseColor("#CD5C5C"));
                        }

                        if (placementx>getWidth()-25){
                            placementy = placementy+35;
                            placementx = 35;
                        }
                        c.drawCircle(placementx, placementy, radius, paint);

                        RectF rect1 = new RectF(placementx-7, placementy, placementx+7, placementy+10);
                        paint.setColor(Color.parseColor("#FFF9F7F7"));
                        c.drawArc(rect1, 0, 180, true, paint);
                        c.drawOval(placementx-7,placementy-8,placementx-3,placementy-3,paint);
                        c.drawOval(placementx+4,placementy-8,placementx+8,placementy-3,paint);
                        placementx =placementx+35;
                }
            }
        };
        TableLayout tableLayout = new TableLayout(getActivity());
        tableLayout.addView(minGrafik);
        return tableLayout;
    }

}
