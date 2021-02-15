package com.mac_available.carrotmarket;

public class ProductVO {

    public String imageUri;
    public String title;
    public String price;
    public String location;
    public String time;


    public ProductVO(String uploadUri, String title, String price, String content, String time, String location) {
    }

    public ProductVO(String imageUri, String title, String price, String location, String time) {
        this.imageUri = imageUri;
        this.title = title;
        this.price = price;
        this.location = location;
        this.time = time;
    }
}

