package com.f2016.dtu.androidventeliste.Fragments;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;

/**
 * Created by Marie on 27/03/16.
 */
public class BotQueueFragment extends Fragment {

    private TextView leftTextField;
    private TextView centerTextField;
    private TextView rightTextField;

    private Handler customHandler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View view = inflater.inflate(R.layout.fragment_queue_bot, container, false);
        leftTextField = (TextView) view.findViewById(R.id.leftText);
        centerTextField = (TextView) view.findViewById(R.id.centerText);
        rightTextField = (TextView) view.findViewById(R.id.rightText);
        customHandler.postDelayed(updateListThread, 0);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        customHandler.removeCallbacks(updateListThread);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        customHandler = null;
    }

    private Runnable updateListThread = new Runnable() {

        public void run() {
            int noInQ = UserSession.getQueueNumber();
            int timeEstimate = noInQ * 7;
            String noText = "..";

            if(timeEstimate < 60){
                noText = String.valueOf(timeEstimate);
                rightTextField.setText("min");
            }
            else {
                int hours = timeEstimate / 60; //since both are ints, you get an int
                int minutes = timeEstimate % 60;

                String h = String.valueOf(hours);
                String m = String.valueOf(minutes);

                noText = checkFormat(h) + ":" + checkFormat(m);
                rightTextField.setText("timer");
            }
            centerTextField.setText(noText);
            customHandler.postDelayed(this, 500);
        }
    };

    private String checkFormat(String t){
        if(t.length() == 1){
            return "0"+t;
        }
        return t;
    }

}
