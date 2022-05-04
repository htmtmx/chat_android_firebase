package com.example.chat_firebase;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderMessage extends RecyclerView.ViewHolder {
    private TextView name, message, time;
    private ImageView photo;
    public HolderMessage(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name_message);
        message = (TextView) itemView.findViewById(R.id.message_message);
        time = (TextView) itemView.findViewById(R.id.time_message);
        photo = (ImageView) itemView.findViewById(R.id.photo_message);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getMessage() {
        return message;
    }

    public void setMessage(TextView message) {
        this.message = message;
    }

    public TextView getTime() {
        return time;
    }

    public void setTime(TextView time) {
        this.time = time;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

}
