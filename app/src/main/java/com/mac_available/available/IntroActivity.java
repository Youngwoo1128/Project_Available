package com.mac_available.available;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class IntroActivity extends AppCompatActivity {
    //TextView tv;
    Animation animation;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        relativeLayout = findViewById(R.id.relative);
        animation = AnimationUtils.loadAnimation(this, R.anim.introanimation);
        relativeLayout.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = IntroActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
               String userID = sharedPreferences.getString("userKey",null);

                Intent intent;
                if (userID != null){
                    G.myId = userID;
                    intent = new Intent(IntroActivity.this, MainActivity.class);
                }else{
                    intent = new Intent(IntroActivity.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();

            }

        },2000);
    }

}