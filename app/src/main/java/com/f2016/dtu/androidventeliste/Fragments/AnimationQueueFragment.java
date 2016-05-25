package com.f2016.dtu.androidventeliste.Fragments;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;

public class AnimationQueueFragment extends Fragment {
    private View view;
    private Handler customHandler = new Handler();
    private int queueLenght;
    private int queueNumber;
    private LinearLayout layout;
    private LinearLayout curLine;
    private int lines;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_queue_animation, container, false);
        setHasOptionsMenu(true);
        customHandler.postDelayed(updateTextThread, 0);
        return view;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu){
        menu.clear();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        customHandler.removeCallbacks(updateTextThread);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        customHandler = null;
    }

    private Runnable updateTextThread = new Runnable() {

        public void run() {
            if (queueLenght != UserSession.getQueueLenght() || queueNumber != UserSession.getQueueNumber()) {
                setDynamicData();
            }
            customHandler.postDelayed(this, 500);
        }
    };

    private void setDynamicData() {
        queueLenght = UserSession.getQueueLenght();
        queueNumber = UserSession.getQueueNumber();

        layout = (LinearLayout) view.findViewById(R.id.queueLayout);
        layout.removeAllViews();

        if (queueLenght <= 10) {
            lines = 1;
        } else {
            lines = ((queueLenght - (queueLenght % 10)) / 10 + 1);
        }
        layout.setWeightSum(lines);

        for (int i = 0; i < queueLenght + 1; i++) {

            if (i % 10 == 0) {
                newViewLine();
            }
            if (i == 0) {
                placePictureInQueue("start");
            } else if (UserSession.getQueueNumber() == i) {
                placePictureInQueue(UserSession.getTriageName());
            } else {
                placePictureInQueue("sort");
            }

        }
    }

    private void newViewLine() {
        curLine = new LinearLayout(layout.getContext());
        curLine.setOrientation(LinearLayout.HORIZONTAL);
        curLine.setGravity(Gravity.CENTER_VERTICAL);
        LinearLayout.LayoutParams HorizontalLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        HorizontalLayoutParams.weight = 1;

        curLine.setLayoutParams(HorizontalLayoutParams);
        layout.addView(curLine);
    }

    private void placePictureInQueue(String color) {
        try {
            WindowManager wm = (WindowManager) layout.getContext().getSystemService(layout.getContext().WINDOW_SERVICE);

            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);

            double width = metrics.widthPixels;
            double height = metrics.heightPixels;
            double scale = height / width;
            ImageView imageView = new ImageView(curLine.getContext());

            imageView.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));
            imageView.getLayoutParams().width = (int) width / 10;
            imageView.getLayoutParams().height = (int) (imageView.getLayoutParams().width * scale);

            if (color.equals("sort")) {
                imageView.setImageResource(R.drawable.sortmand);
            } else if (color.equals("rød")) {
                imageView.setImageResource(R.drawable.rodmand);
            } else if (color.equals("orange")) {
                imageView.setImageResource(R.drawable.orangemand);
            } else if (color.equals("gul")) {
                imageView.setImageResource(R.drawable.gulmand);
            } else if (color.equals("grøn")) {
                imageView.setImageResource(R.drawable.gronmand);
            } else if (color.equals("blå")) {
                imageView.setImageResource(R.drawable.blaamand);
            } else if (color.equals("start")) {
                imageView.setImageResource(R.drawable.waitingman);
            }
            curLine.addView(imageView);

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
        }
    }
}
