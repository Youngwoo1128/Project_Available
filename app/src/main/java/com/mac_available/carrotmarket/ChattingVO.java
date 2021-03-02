package com.mac_available.carrotmarket;

public class ChattingVO {

    public String id;
    public String time;
    public String message;


    public ChattingVO() {
    }

    public ChattingVO(String id, String message, String time) {
        this.id = id;
        this.message = message;
        this.time = time;
    }
}
