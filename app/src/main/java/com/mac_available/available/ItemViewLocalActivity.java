package com.mac_available.available;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class ItemViewLocalActivity extends AppCompatActivity {

    TextView tv_name, tv_message;
    Toolbar toolbar;

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

        toolbar = findViewById(R.id.itemView_local_toolbar);
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