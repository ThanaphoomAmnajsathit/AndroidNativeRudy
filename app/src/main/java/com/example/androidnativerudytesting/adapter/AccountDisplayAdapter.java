package com.example.androidnativerudytesting.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidnativerudytesting.R;

public class AccountDisplayAdapter extends RecyclerView.Adapter<AccountDisplayAdapter.Holder> {

    private int[] topic;
    private String[] information;

    public AccountDisplayAdapter(int[] topic,String[] information){
        this.topic = topic;
        this.information = information;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.layout_account_display,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.iv_topic.setImageResource(topic[position]);
        holder.tv_information.setText(information[position]);
    }

    @Override
    public int getItemCount() {
        return topic.length;
    }

    class Holder extends RecyclerView.ViewHolder{

        ImageView iv_topic;
        TextView tv_information;

        public Holder(@NonNull View itemView) {
            super(itemView);
            iv_topic = itemView.findViewById(R.id.imageView_topic);
            tv_information = itemView.findViewById(R.id.textView_information);
        }
    }
}
