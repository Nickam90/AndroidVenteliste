package com.f2016.dtu.androidventeliste.Utils;

import android.app.Activity;
import android.os.Handler;
import android.widget.Toast;

import com.f2016.dtu.androidventeliste.Activities.MainActivity;

import java.util.Timer;

/**
 * Created by nicka on 07-04-2016.
 */
public class DemoSession {

    private Runnable demoSession;
    private Handler demoHandler;
    private int eventIndex;
    private int numberOfEvents;
    private int eventTime = 10000;
    private Activity demoCaller;

    public DemoSession(int events, Activity caller){
        numberOfEvents = events;
        demoHandler = new Handler();
        eventIndex = 0;
        demoCaller = caller;

        demoSession = new Runnable() {
            @Override
            public void run() {
                eventHandler();
                eventIndex++;
                if(eventIndex < numberOfEvents) {
                    demoHandler.postDelayed(this, eventTime);
                }
            }
        };
        demoHandler.postDelayed(demoSession, 500);
    }

    private void eventHandler() {
        switch (eventIndex) {
            case 0:
                UserSession.setQueueNumber(UserSession.getQueueNumber()-1);
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 4:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 5:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 6:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 7:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 8:
                Toast.makeText(demoCaller,
                        "Tom event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }

    public void stopDemo(){
        demoHandler.removeCallbacks(demoSession);
    }

}
