package com.example.chat_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdapterMessages extends RecyclerView.Adapter<HolderMessage> {
    private List<MessageReceive> list_message = new ArrayList<>();
    private Context c;

    public AdapterMessages(Context c) {
        this.c = c;
    }

    public void addMessage(MessageReceive m){
        list_message.add(m);
        notifyItemInserted(list_message.size());
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.card_view_messages,parent,false);
        return new HolderMessage(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        holder.getName().setText(list_message.get(position).getName());
        holder.getMessage().setText(list_message.get(position).getMessage());
        if (list_message.get(position).getType_message().equals("2")){
            holder.getPhoto().setVisibility(View.VISIBLE);
            holder.getMessage().setVisibility(View.VISIBLE);
            Glide.with(c).load(list_message.get(position).getUrl_photo()).into(holder.getPhoto());
        }else if (list_message.get(position).getType_message().equals("1")){
            holder.getPhoto().setVisibility(View.GONE);
            holder.getMessage().setVisibility(View.VISIBLE);
        }
        if (list_message.get(position).getPhoto_profile().isEmpty()){
            holder.getPhoto().setImageResource(R.mipmap.ic_launcher);
        }else{
            Glide.with(c).load(list_message.get(position).getPhoto_profile()).into(holder.getPhoto_profile_message());
        }
        Long code_time = list_message.get(position).getTime();
        Date d = new Date(code_time);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        holder.getTime().setText(sdf.format(d));
    }

    @Override
    public int getItemCount() {
        return list_message.size();
    }
}
