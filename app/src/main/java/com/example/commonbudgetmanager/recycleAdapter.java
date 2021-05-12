package com.example.commonbudgetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>{

    String data1[], data2[];
    Context context;

    public recycleAdapter(Context ct, String s1[], String s2[])
    {
        context = ct;
        data1 = s1;
        data2 = s2;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text.setText(data1[position]);
        holder.suma.setText(data2[position]);
        //holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return data1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text, suma;
        //ImageView myImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            suma = itemView.findViewById(R.id.suma);
        }
    }
}
