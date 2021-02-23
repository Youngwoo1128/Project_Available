package com.mac_available.carrotmarket;

public class ProductVO {

    public String imageUri;
    public String title;
    public String price;
    public String content;
    public String location;
    public String time;
    public String masterId;


    public ProductVO() {
    }

    public ProductVO(String imageUri, String title, String price, String content, String location, String time) {
        this.imageUri = imageUri;
        this.title = title;
        this.price = price;
        this.content = content;
        this.location = location;
        this.time = time;
    }

    public ProductVO(String imageUri, String title, String price, String content, String location, String time, String masterId) {
        this.imageUri = imageUri;
        this.title = title;
        this.price = price;
        this.content = content;
        this.location = location;
        this.time = time;
        this.masterId = masterId;
    }
}

