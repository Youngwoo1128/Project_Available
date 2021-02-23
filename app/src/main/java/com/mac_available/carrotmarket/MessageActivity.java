package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MessageActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        tv = findViewById(R.id.msg_tv);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String message = intent.getStringExtra("msg");

        getSupportActionBar().setTitle(name);
        tv.setText(message);
    }
}