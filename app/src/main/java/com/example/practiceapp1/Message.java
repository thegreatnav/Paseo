package com.example.practiceapp1;

public class Message {

    private String text;
    private String time;
    private String senderId,receiverId;

    public Message() {
        // Default constructor required for Firebase
    }

    public Message(String text,String receiverId,String senderId, String time) {
        this.text = text;
        this.senderId = senderId;
        this.receiverId=receiverId;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverId() {
        return receiverId;
    }
}

