package com.example.chat_firebase;

import java.util.Map;

public class MessageSend extends Message{
    private Map time;

    public MessageSend() {
    }

    public MessageSend(Map time) {
        this.time = time;
    }

    public MessageSend(String message, String url_photo, String name, String photo_profile, String type_message, Map time) {
        super(message, url_photo, name, photo_profile, type_message);
        this.time = time;
    }

    public MessageSend(String message, String name, String photo_profile, String type_message, Map time) {
        super(message, name, photo_profile, type_message);
        this.time = time;
    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}
