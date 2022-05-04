package com.example.chat_firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdapterMessages extends RecyclerView.Adapter<HolderMessage> {
    private List<Message> list_message = new ArrayList<>();
    private Context c;

    public AdapterMessages(Context c) {
        this.c = c;
    }

    public void addMessage(Message m){
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
        holder.getTime().setText(list_message.get(position).getTime());
        if (list_message.get(position).getType_message().equals("2")){
            holder.getPhoto().setVisibility(View.VISIBLE);
            holder.getMessage().setVisibility(View.VISIBLE);
            Glide.with(c).load(list_message.get(position).getUrl_photo()).into(holder.getPhoto());
        }else if (list_message.get(position).getType_message().equals("1")){
            holder.getPhoto().setVisibility(View.GONE);
            holder.getMessage().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list_message.size();
    }
}
