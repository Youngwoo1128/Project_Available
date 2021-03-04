package com.mac_available.available;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class AdvertisingProduction extends AppCompatActivity {

    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertising_production);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        int imgId = intent.getIntExtra("imgId", R.drawable.ch_chopa);

        //name은 제목줄에 표시
        getSupportActionBar().setTitle(name+"");

        //이미지 설정 - Glide 라이브러리 사용
        iv = findViewById(R.id.iv);
        Glide.with(this).load(imgId).into(iv);

        //iv에게 Transition의 Pair를 위한 이름 부여
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            iv.setTransitionName("img");
        }

        //제목줄에 뒤로가기 버튼 화살표아이콘 보이기
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    //업버튼 클릭 반응하기 - 사실 업버튼은 일종의 옵션메뉴아이템과 같은것임


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==android.R.id.home){
            super.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}