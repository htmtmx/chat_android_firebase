package com.example.chat_firebase;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HolderMessage extends RecyclerView.ViewHolder {
    private TextView name, message, time;
    private ImageView photo, photo_profile, photo_profile_message;
    public HolderMessage(@NonNull View itemView) {
        super(itemView);
        name = (TextView) itemView.findViewById(R.id.name_message);
        message = (TextView) itemView.findViewById(R.id.message_message);
        time = (TextView) itemView.findViewById(R.id.time_message);
        photo = (ImageView) itemView.findViewById(R.id.photo_message);
        photo_profile = (ImageView) itemView.findViewById(R.id.photo_profile);
        photo_profile_message = (ImageView) itemView.findViewById(R.id.photo_user_message);
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

    public ImageView getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(ImageView photo_profile) {
        this.photo_profile = photo_profile;
    }

    public ImageView getPhoto_profile_message() {
        return photo_profile_message;
    }

    public void setPhoto_profile_message(ImageView photo_profile_message) {
        this.photo_profile_message = photo_profile_message;
    }
}
