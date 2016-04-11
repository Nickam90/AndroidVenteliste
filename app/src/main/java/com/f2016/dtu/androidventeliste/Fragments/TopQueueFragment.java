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
public class TopQueueFragment extends Fragment {

    private Handler customHandler = new Handler();
    private TextView noInQueueFieldView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue_top, container, false);
        noInQueueFieldView = (TextView) view.findViewById(R.id.NoInQueueField);
        customHandler.postDelayed(updateListThread, 0);
        return view;
    }

    private Runnable updateListThread = new Runnable() {

        public void run() {
            int noInQ = UserSession.getQueueNumber();
            String noText = String.valueOf(noInQ);
            noInQueueFieldView.setText(noText);
            customHandler.postDelayed(this, 500);
        }
    };

}
