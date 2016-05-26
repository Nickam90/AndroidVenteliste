package com.f2016.dtu.androidventeliste.Fragments;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;


import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;


public class DrawQueueFragment extends Fragment {
    private View minGrafik;
    private int navigation;
    private ScrollView scrollViewForView;
    private boolean findflag;
    private int k;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        minGrafik = new View(getActivity()) {

            @Override
            protected void onDraw(Canvas c) {
                Bitmap bitmapNurse;
                bitmapNurse = BitmapFactory.decodeResource(this.getResources(), R.drawable.icon);
                int bitmapplacement = (getWidth() - bitmapNurse.getWidth()) / 2;
                int bitmapsize = bitmapNurse.getHeight();
                //Width attributes
                double onePercentWidth = getWidth() * 0.005;
                int onePercentWidthInt = (int) onePercentWidth;
                int placementx = (int) getWidth() / 2;
                //Height attributes
                int placementy = bitmapsize + onePercentWidthInt * 20;
                //Radius of smileys
                int radius = (int) onePercentWidthInt * 12;
                Paint paint = new Paint();
                paint.setStrokeCap(Paint.Cap.ROUND);
                paint.setStrokeJoin(Paint.Join.ROUND);
                paint.setAntiAlias(true);
                paint.setStrokeWidth(radius / 14.0f);
                paint.setColor(Color.WHITE);
                paint.setTextSize(onePercentWidthInt * 10);

                c.drawPaint(paint);

                c.drawBitmap(bitmapNurse, bitmapplacement, 0, paint);
                k = 1;
                while (k <= UserSession.getQueueLenght()) {

                    if (k == UserSession.getQueueNumber()) {
                        paint.setStyle(Paint.Style.FILL);
                        paint.setColor(Color.parseColor(UserSession.getTriageColorCode()));
                        c.drawText("" + k, onePercentWidthInt * 7, placementy + onePercentWidthInt * 3, paint);

                        if (findflag) {
                            setMyPlace(placementy);
                            findflag = false;
                        }
                    }

                    if (k != UserSession.getQueueNumber()) {
                        paint.setStyle(Paint.Style.FILL);
                        paint.setColor(Color.parseColor("#000000"));
                        c.drawText("" + k, onePercentWidthInt * 7, placementy + onePercentWidthInt * 3, paint);
                    }
                    //Draw feet
                    c.drawOval(placementx - (onePercentWidthInt * 11), placementy - (onePercentWidthInt * 15), placementx - (onePercentWidthInt * 2), placementy - (onePercentWidthInt * 5), paint);
                    c.drawOval(placementx + (onePercentWidthInt * 2), placementy - (onePercentWidthInt * 15), placementx + (onePercentWidthInt * 11), placementy - (onePercentWidthInt * 5), paint);

                    //Draw body
                    c.drawCircle(placementx, placementy, radius, paint);

                    //Set color for mouth and eyes
                    paint.setColor(Color.parseColor("#FFF9F7F7"));
                    //Draw mouth
                    paint.setStyle(Paint.Style.STROKE);
                    c.drawArc(new RectF(placementx - (onePercentWidthInt * 7), placementy - (onePercentWidthInt * 9), placementx + (onePercentWidthInt * 7), placementy - (onePercentWidthInt * 5)), 0, -180, false, paint);

                    //Draw eyes
                    c.drawOval(placementx - (onePercentWidthInt * 2), placementy - (onePercentWidthInt * 5), placementx - (onePercentWidthInt * 1), placementy - (onePercentWidthInt * 3), paint);
                    c.drawOval(placementx + (onePercentWidthInt * 1), placementy - (onePercentWidthInt * 5), placementx + (onePercentWidthInt * 2), placementy - (onePercentWidthInt * 3), paint);

                    //Set placementy to next line
                    placementy = placementy + onePercentWidthInt * 30;
                    k++;

                }
                minGrafik.setLayoutParams(new LinearLayout.LayoutParams(getWidth(), placementy));
                invalidate();
            }

        };

        LinearLayout holder = new LinearLayout(getActivity());
        holder.addView(minGrafik);
        scrollViewForView = new ScrollView(getActivity());
        scrollViewForView.setFillViewport(true);
        scrollViewForView.setSmoothScrollingEnabled(true);
        scrollViewForView.addView(holder);
        return scrollViewForView;
    }

    public void setMyPlace(int a) {
        this.navigation = a;
        double me = (int) navigation * 0.8;
        int meme = (int) me;
        scrollViewForView.smoothScrollTo(0, meme);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_main, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.findme) {
            findflag = true;
        }
        return true;
    }
}
