package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ItemViewLocalActivity extends AppCompatActivity {

    TextView tv_name, tv_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view_local);

        tv_name = findViewById(R.id.itemViewName);
        tv_message = findViewById(R.id.itemViewMessage);

        String name = getIntent().getStringExtra("name");
        String message = getIntent().getStringExtra("msg");

        tv_name.setText(name);
        tv_message.setText(message);
    }
}