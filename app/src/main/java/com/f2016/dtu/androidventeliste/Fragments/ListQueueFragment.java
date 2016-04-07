package com.f2016.dtu.androidventeliste.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.f2016.dtu.androidventeliste.R;
import com.f2016.dtu.androidventeliste.Utils.UserSession;

import java.util.Arrays;

/**
 * Created by Marie on 07/04/16.
 */
public class ListQueueFragment extends Fragment {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galge_spil_highscore);
    }
*/
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_galge_spil);
        View view = inflater.inflate(R.layout.list_queue, container, false);
        setDynamicData(view);
        return view;
    }
    /*
    @Override
    protected void onResume() {
        super.onResume();
    }*/
    private void setDynamicData(View view){
        ListView queueList = (ListView) view.findViewById(R.id.queue_list);

        String[] queueArray = new String[UserSession.getQueueLenght()];
        for(int i = 0; i < queueArray.length; i++){
            if(i+1 == UserSession.getQueueNumber()){
                queueArray[i] = i+1 + ": Du er her" ;


            }else{
                queueArray[i] = i+1 + ": Patient " + (i+1);
            }

        }

        Arrays.sort(queueArray);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(queueList.getContext(), android.R.layout.simple_list_item_1, queueArray);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(queueList.getContext(), android.R.layout.simple_list_item_1, queueArray);
        queueList.setAdapter(adapter2);
    }
}
