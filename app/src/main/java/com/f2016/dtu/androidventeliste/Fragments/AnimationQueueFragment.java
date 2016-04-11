package com.f2016.dtu.androidventeliste.Fragments;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;

public class AnimationQueueFragment extends Fragment{

    private View view;
    private Handler customHandler = new Handler();
    private int queueLenght;
    private int queueNumber;
    LinearLayout layout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_queue_animation, container, false);
        setDynamicData();
        customHandler.postDelayed(updateTextThread, 0);
        return view;
    }

    private Runnable updateTextThread = new Runnable() {

        public void run() {
            if(queueLenght != UserSession.getQueueLenght() ||  queueNumber != UserSession.getQueueNumber()){
                setDynamicData();
            }
            customHandler.postDelayed(this, 500);
        }
    };

    private void setDynamicData(){
        queueLenght = UserSession.getQueueLenght();
        queueNumber = UserSession.getQueueNumber();

        layout = (LinearLayout)view.findViewById(R.id.queueLayout);
        layout.removeAllViews();

        for(int i = 0; i < queueLenght; i++){
            placePictureInQueue();
        }
    }

    private void placePictureInQueue() {
      /*  ImageView imageView = new ImageView(layout.getContext());
        imageView.setImageResource(R.drawable.blaamand);
        //setting image position
        imageView.getLayoutParams().height = 50;
        imageView.getLayoutParams().height = 30;
        layout.addView(imageView);*/
    }
}
