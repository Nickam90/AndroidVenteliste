package com.f2016.dtu.androidventeliste.Fragments;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.CustomArrayAdapter;
import com.f2016.dtu.androidventeliste.Utils.UserSession;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Marie on 07/04/16.
 */
public class ListQueueFragment extends Fragment {
    private View view;
    private Handler customHandler = new Handler();
    private int queueLenght;
    private int queueNumber;
    private ListView queueList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.list_queue, container, false);
        setDynamicData();
        customHandler.postDelayed(updateTextThread, 0);
        return view;
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
        queueList = (ListView) view.findViewById(R.id.queue_list);

        String[] queueArray = new String[queueLenght];
        for (int i = 0; i < queueArray.length; i++) {
            if (i + 1 == queueNumber) {
                queueArray[i] = i + 1 + ": Du er her";
            } else {
                queueArray[i] = i + 1 + ": Patient " + (i + 1);
            }
        }
        Arrays.sort(queueArray, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                String first = lhs.split(":")[0].trim();
                String second = rhs.split(":")[0].trim();
                int firstNumber = Integer.parseInt(first);
                int secondNumber = Integer.parseInt(second);

                if (firstNumber > secondNumber) {
                    return 1;
                } else if (firstNumber == secondNumber) {
                    return 0;
                }
                return -1;
            }
        });
        ArrayAdapter<String> adapter2 = new CustomArrayAdapter(queueList.getContext(), android.R.layout.simple_list_item_1, queueArray);
        queueList.setAdapter(adapter2);
        view.invalidate();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.findme) {
            queueList.smoothScrollToPosition(queueNumber);
        }
        return true;
    }
}
