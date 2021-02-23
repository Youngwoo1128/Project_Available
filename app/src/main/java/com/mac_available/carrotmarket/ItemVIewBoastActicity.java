package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemVIewBoastActicity extends AppCompatActivity {

    ImageView iv;
    TextView tv_title, tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_v_iew_boast_acticity);

        iv = findViewById(R.id.itemViewBoastImageView);
        tv_title = findViewById(R.id.itemViewBoastTitle);
        tv_message = findViewById(R.id.itemViewBoastMessage);

        ///////////////////////////////////////////////////////

        String title = getIntent().getStringExtra("title");
        String msg = getIntent().getStringExtra("msg");
        String img = getIntent().getStringExtra("img");

        tv_title.setText(title);
        tv_message.setText(msg);
        Glide.with(this).load(img).into(iv);
    }
}