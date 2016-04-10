package com.f2016.dtu.androidventeliste.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;


import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;


public class DrawQueueFragment extends Fragment{


    View minGrafik;
    int k;
    int placementy;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        minGrafik = new View(getActivity()) {


            @Override
            protected void onDraw(Canvas c) {


                        int x = getWidth();
                        int y = c.getHeight();
                        Bitmap bitmapNurse;
                        bitmapNurse = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon);
                        int bitmapplacement = (getWidth() - bitmapNurse.getWidth()) /2;

                        int bitmapsize = bitmapNurse.getHeight();
                        //Width attributes
                        double onePercentWidth = getWidth() * 0.01;
                        int onePercentWidthInt = (int) onePercentWidth;
                        int placementx = (int) getWidth()/2;

                        //Height attributes
                        double onePercentHeight = getHeight() * 0.01;
                        int onePercentHeightInt = (int) onePercentHeight;
                        placementy = bitmapsize+onePercentWidthInt*20;

                        //Radius of smileys
                        int radius = (int) onePercentWidthInt*12;

                        Paint paint = new Paint();
                        paint.setStrokeCap(Paint.Cap.ROUND);
                        paint.setStrokeJoin(Paint.Join.ROUND);
                        //paint.setPathEffect(new CornerPathEffect(10) );
                        paint.setAntiAlias(true);
                        paint.setStrokeWidth(radius / 14.0f);
                        paint.setColor(Color.WHITE);
                        c.drawPaint(paint);



                            c.drawBitmap(bitmapNurse, bitmapplacement, 0, paint);

                            for (k=1;k<=UserSession.getQueueLenght();k++) {

                            if (k == UserSession.getQueueNumber()){
                                paint.setStyle(Paint.Style.FILL);
                                paint.setColor(Color.parseColor("#009ce8"));
                            }

                            if (k!=UserSession.getQueueNumber()) {
                                paint.setStyle(Paint.Style.FILL);
                                paint.setColor(Color.parseColor("#000000"));
                            }


                                c.drawOval(placementx - (onePercentWidthInt * 11), placementy - (onePercentWidthInt * 15), placementx - (onePercentWidthInt * 2), placementy - (onePercentWidthInt * 5), paint);
                                c.drawOval(placementx + (onePercentWidthInt * 2), placementy - (onePercentWidthInt * 15), placementx + (onePercentWidthInt * 11), placementy - (onePercentWidthInt * 5), paint);

                                c.drawCircle(placementx, placementy, radius, paint);

                               // RectF rect1 = new RectF(placementx-(onePercentWidthInt*7), placementy-(onePercentWidthInt*10), placementx+(onePercentWidthInt*7), placementy-(onePercentWidthInt*4));
                                paint.setColor(Color.parseColor("#FFF9F7F7"));
                                //Draw mouth
                                paint.setStyle(Paint.Style.STROKE);
                                c.drawArc(new RectF(placementx - (onePercentWidthInt * 5), placementy - (onePercentWidthInt * 8), placementx + (onePercentWidthInt * 5), placementy - (onePercentWidthInt * 7)), 0, -180, true, paint);
                                //Draw eyes
                                c.drawOval(placementx - (onePercentWidthInt*2), placementy - (onePercentWidthInt*5), placementx - (onePercentWidthInt*1), placementy - (onePercentWidthInt*3), paint);
                                c.drawOval(placementx + (onePercentWidthInt*1), placementy - (onePercentWidthInt*5), placementx + (onePercentWidthInt*2), placementy - (onePercentWidthInt*3), paint);

                                placementy = placementy+onePercentWidthInt*30;

                    }

                minGrafik.setLayoutParams(new LinearLayout.LayoutParams(getWidth(),placementy));
                invalidate();
            }

        };



        Log.d("hej","HVAD ER PLACEMENT "+placementy);
        LinearLayout holder = new LinearLayout(getActivity());
        holder.addView(minGrafik);
        ScrollView test = new ScrollView(getActivity());
        test.setFillViewport(true);
        test.setSmoothScrollingEnabled(true);

        test.addView(holder);

        return test;
    }


}
