package com.example.commonbudgetmanager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FragVenituri extends Fragment {

    RecyclerView recyclerView;
    private List<User> userList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_frag_venituri, container, false);
        loadTransactionList();
        recyclerView = view.findViewById(R.id.recyclerViewCheltuieli);
        //getActivity() -> return context
        recycleAdapter myAdapter = new recycleAdapter(getActivity());

        recyclerView.setAdapter(myAdapter);
        myAdapter.setUserList(userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void loadTransactionList(){
        AppDatabase db = AppDatabase.getDbInstance(getActivity().getApplicationContext());
        userList = db.userDao().getAllTransactions();
    }
}