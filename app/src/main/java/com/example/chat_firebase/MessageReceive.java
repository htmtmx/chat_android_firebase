package com.example.chat_firebase;

public class MessageReceive extends Message{
    private Long time;

    public MessageReceive() {
    }

    public MessageReceive(Long time) {
        this.time = time;
    }

    public MessageReceive(String message, String url_photo, String name, String photo_profile, String type_message, Long time) {
        super(message, url_photo, name, photo_profile, type_message);
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
