package com.mac_available.carrotmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemVIewBoastActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv_title, tv_message;
    Toolbar toolbar;

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

        toolbar = findViewById(R.id.itemView_Boast_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}