package com.f2016.dtu.androidventeliste.Fragments;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
            if(UserSession.getQueueNumber()-1 == i){
                placePictureInQueue(UserSession.getTriageName());
            }
            else {
                placePictureInQueue("sort");
            }
        }
    }

    private void placePictureInQueue(String color) {
        try {
            ImageView imageView = new ImageView(layout.getContext());
            if(color.equals("sort")){
                imageView.setImageResource(R.drawable.sortmand);
            }
            else if(color.equals("rød")){
                imageView.setImageResource(R.drawable.rodmand);
            }
            else if(color.equals("orange")){
                imageView.setImageResource(R.drawable.orangemand);
            }
            else if(color.equals("gul")){
                imageView.setImageResource(R.drawable.gulmand);
            }
            else if(color.equals("grøn")){
                imageView.setImageResource(R.drawable.gronmand);
            }
            else if(color.equals("blå")){
                imageView.setImageResource(R.drawable.blaamand);
            }

            imageView.setLayoutParams(new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT));
            imageView.getLayoutParams().width = 60;
            imageView.getLayoutParams().height = 100;

            //setting image position
            //imageView.setLayoutParams();setMaxHeight(50);
            //imageView.setMaxWidth(30);
            layout.addView(imageView);
            //layout.invalidate();
            //getActivity().setContentView(layout);
        }
        catch (Exception e){
            Log.d("Error", e.getMessage());
        }
    }
}
