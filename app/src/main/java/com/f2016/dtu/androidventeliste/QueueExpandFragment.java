package com.f2016.dtu.androidventeliste;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

/**
 * Created by Marie on 27/03/16.
 */
public class QueueExpandFragment extends Fragment {
    private String[] groups;
    private String[][] children;
    private ExpandableListView lv;
    private View view;
    private LayoutInflater inflater;

    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        groups = new String[]{"Du er nr " + UserSession.getQueueNumber() + " i køen."};

        children = new String[][] {{"Den forventet ventetid er "}};

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_queue_expand, container, false);
        this.inflater = inflater;
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstaceState) {
        super.onViewCreated(view, savedInstaceState);

        lv = (ExpandableListView) view.findViewById(R.id.queueNr);
        lv.setAdapter(new CustomExpandableListAdapter(groups, children, inflater));
        lv.setGroupIndicator(null);

    }

}
