package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemViewMainActivity extends AppCompatActivity {

    TextView tv_title,tv_content,tv_price;
    ImageView iv;
    Button btnBuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view_main);

        tv_title = findViewById(R.id.itemViewTitle);
        tv_content = findViewById(R.id.itemViewContent);
        tv_price = findViewById(R.id.itemViewPrice);
        iv = findViewById(R.id.itemViewImage);
        btnBuy = findViewById(R.id.btn_buy);

        //////////////////////////////////////////////////////////////////

        String title = G.currentItem.title;
        String price = G.currentItem.price;
        String content = G.currentItem.content;
        String img = G.currentItem.imageUri;

        if (G.myId.equals(G.currentItem.masterId)){
            btnBuy.setEnabled(false);
        }

//        String title = getIntent().getStringExtra("name");
//        String price = getIntent().getStringExtra("price");
//        String content = getIntent().getStringExtra("content");
//        String img = getIntent().getStringExtra("img");
        tv_title.setText(title);
        tv_price.setText(price);
        tv_content.setText(content);
        Glide.with(this).load(img).into(iv);

    }

    public void clickChatting(View view) {
        Intent intent = new Intent(this, ChattingActivity.class);
        startActivity(intent);
    }
}