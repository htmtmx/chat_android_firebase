package com.example.chat_firebase;

public class Message {
    private String message;
    private String url_photo;
    private String name;
    private String photo_profile;
    private String type_message;

    public Message() {
    }

    public Message(String message, String name, String photo_profile, String type_message) {
        this.message = message;
        this.name = name;
        this.photo_profile = photo_profile;
        this.type_message = type_message;
    }

    public Message(String message, String url_photo, String name, String photo_profile, String type_message) {
        this.message = message;
        this.url_photo = url_photo;
        this.name = name;
        this.photo_profile = photo_profile;
        this.type_message = type_message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        this.photo_profile = photo_profile;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }
}
