package com.inti.brandon.travelme;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.inti.brandon.travelme.RSSFeed.ReadRss;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFragment extends Fragment {

    RecyclerView recyclerView;
    private View view;

    public DiscoverFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_dicover, container, false);


        recyclerView= (RecyclerView) view.findViewById(R.id.recyclerview);
        ReadRss readRss = new ReadRss(getActivity(),recyclerView);
        readRss.execute();

        return view;

    }

}
