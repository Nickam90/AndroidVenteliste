package com.f2016.dtu.androidventeliste.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;


import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;


public class DrawQueueFragment extends Fragment implements View.OnClickListener{


    View minGrafik;
    int navigation;
    ScrollView test;
    boolean findflag;
    int k;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);

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
                        double onePercentWidth = getWidth() * 0.005;
                        int onePercentWidthInt = (int) onePercentWidth;
                        int placementx = (int) getWidth()/2;

                        //Height attributes
                        int placementy = bitmapsize+onePercentWidthInt*20;

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
                            k=1;
                            while (k<=UserSession.getQueueLenght()){
                           // for (k=1;k<=UserSession.getQueueLenght();k++) {

                            if (k == UserSession.getQueueNumber()){
                                paint.setStyle(Paint.Style.FILL);
                                paint.setColor(Color.parseColor("#009ce8"));

                                if (findflag) {
                                    setMyPlace(placementy);
                                    findflag=false;
                                }


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

                                c.drawArc(new RectF(placementx - (onePercentWidthInt * 7), placementy - (onePercentWidthInt * 9), placementx + (onePercentWidthInt * 7), placementy - (onePercentWidthInt * 5)), 0, -180, false, paint);
                                //Draw eyes
                                c.drawOval(placementx - (onePercentWidthInt*2), placementy - (onePercentWidthInt*5), placementx - (onePercentWidthInt*1), placementy - (onePercentWidthInt*3), paint);
                                c.drawOval(placementx + (onePercentWidthInt * 1), placementy - (onePercentWidthInt * 5), placementx + (onePercentWidthInt * 2), placementy - (onePercentWidthInt * 3), paint);

                                //place que number text
                                paint.setTextSize(onePercentWidthInt*10);
                                paint.setColor(Color.BLACK);
                                c.drawText(""+k, onePercentWidthInt*5, placementy+onePercentWidthInt*3, paint);

                                placementy = placementy+onePercentWidthInt*30;
                                k++;

                    }
                //Log.d("hov", "HVAD ER tester " + tester+" "+k);
                minGrafik.setLayoutParams(new LinearLayout.LayoutParams(getWidth(),placementy));
               invalidate();
            }

        };

        TableLayout tableLayout = new TableLayout(getActivity());


        //Button findMe = new Button(getContext());
        //findMe.setOnClickListener((View.OnClickListener) this);
        //findMe.setText("Find mig");
        LinearLayout holder = new LinearLayout(getActivity());

        //tableLayout.addView(findMe);
        tableLayout.addView(minGrafik);
        holder.addView(tableLayout);

        test = new ScrollView(getActivity());
        test.setFillViewport(true);
        test.setSmoothScrollingEnabled(true);

        test.addView(holder);



        return test;
    }

    public void setMyPlace(int a) {

        this.navigation = a;
        double me = (int) navigation * 0.8;
        int meme = (int) me;
        test.smoothScrollTo(0, meme);
    }


    @Override
    public void onClick(View v) {
        findflag=true;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    // Inflate the menu; this adds items to the action bar if it is present.
        inflater.inflate(R.menu.menu_main, menu);
       //return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.findme){
            Log.d("test","HVAD SKER DER HER I KNAPPEN");
            findflag=true;
        }

        return true;
    }


}
