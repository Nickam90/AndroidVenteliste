package com.f2016.dtu.androidventeliste.Utils;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.f2016.dtu.androidventeliste.Activities.MainActivity;
import com.f2016.dtu.androidventeliste.R;

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
        UserSession.setQueueLenght(36);
        UserSession.setQueueNumber(25);

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
        AlertDialog.Builder builder;
        switch (eventIndex) {
            case 0:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 1);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 1);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 1:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 3);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 3);

                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 2:
                UserSession.setQueueLenght(UserSession.getQueueLenght() -5);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 5);

                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 3:
                builder = new AlertDialog.Builder(demoCaller);
                builder.setMessage("Færdsels uheld på O3 motorvejen. \nForventes længere ventetid.");
                builder.show();

                UserSession.setQueueLenght(UserSession.getQueueLenght() + 20);
                UserSession.setQueueNumber(UserSession.getQueueNumber() + 20);

                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 4:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 6);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 6);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 5:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 10);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 10);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 6:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 2);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 2);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 7:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 5);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 3);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 8:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 6);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 6);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 9:
                UserSession.setQueueLenght(UserSession.getQueueLenght() - 5);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 5);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 10:
                builder = new AlertDialog.Builder(demoCaller);
                builder.setMessage("Sammenstød i lyskryds. \nForventes længere ventetid.");
                builder.setTitle("DEMO BESKED");
                builder.show();


                UserSession.setQueueLenght(UserSession.getQueueLenght() + 4);
                UserSession.setQueueNumber(UserSession.getQueueNumber() + 4);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;
            case 11:
                UserSession.setQueueLenght(UserSession.getQueueLenght() + 6);
                UserSession.setQueueNumber(UserSession.getQueueNumber() - 5);
                Toast.makeText(demoCaller,
                        "Event " + eventIndex, Toast.LENGTH_LONG).show();
                break;

            default:
                break;
        }
    }

    public void stopDemo(){
        demoHandler.removeCallbacks(demoSession);
    }

}
