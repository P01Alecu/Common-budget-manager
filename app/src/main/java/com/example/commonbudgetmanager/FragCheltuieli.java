package com.example.commonbudgetmanager;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragCheltuieli extends Fragment {

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String s1[] = {"cheltuiala 1", "cheltuiala 2", "cheltuiala 3"},
                s2[] = {"100", "200", "300"};

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_cheltuieli, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewCheltuieli);

        //getActivity() -> return context
        recycleAdapter myAdapter = new recycleAdapter(getActivity(), s1, s2);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        
        return view;
        //return inflater.inflate(R.layout.fragment_frag_cheltuieli, container, false);
    }
}