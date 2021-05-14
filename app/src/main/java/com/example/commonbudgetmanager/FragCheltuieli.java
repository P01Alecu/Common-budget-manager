package com.example.commonbudgetmanager;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FragCheltuieli extends Fragment {

    RecyclerView recyclerView;
    private List<User> userList;

    String name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SharedPreferences sp = getActivity().getSharedPreferences("UserPref", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_cheltuieli, container, false);
        loadTransactionList(name);
        recyclerView = view.findViewById(R.id.recyclerViewCheltuieli);
        //getActivity() -> return context
        recycleAdapter myAdapter = new recycleAdapter(getActivity());

        if(userList != null) {
            recyclerView.setAdapter(myAdapter);
            myAdapter.setUserList(userList);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        return view;
    }

    private void loadTransactionList(String name){
        AppDatabase db = AppDatabase.getDbInstance(getActivity().getApplicationContext());
        try {
            //userList = db.userDao().getAllTransactions();
            userList = db.userDao().getTransaction(name, "Cheltuiala");
        }catch(Exception e){

        }

        }
    }