package com.example.studydemo.bean;

public class MessageWrap {

    public final String message;
    public int num;
    public static MessageWrap getInstance(String message,int num) {
        return new MessageWrap(message,num);
    }

    private MessageWrap(String message,int num) {
        this.message = message;
        this.num = num;
    }
}