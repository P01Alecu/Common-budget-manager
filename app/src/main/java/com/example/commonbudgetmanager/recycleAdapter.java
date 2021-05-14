package com.example.commonbudgetmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.MyViewHolder>{

    private List<User> userList;
    Context context;

    public void setUserList(List<User> userList){
        this.userList = userList;
        notifyDataSetChanged();
    }

    public recycleAdapter(Context ct)
    {
        context = ct;
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
        holder.text.setText(this.userList.get(position).descriere);
        holder.suma.setText(this.userList.get(position).suma.toString());
        //holder.myImage.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return this.userList.size();
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
