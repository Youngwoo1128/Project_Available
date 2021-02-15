package com.mac_available.carrotmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bnv;

    FragmentManager fragmentManager;

    Fragment[] fragments = new Fragment[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //동적 퍼미션 작업
        String[] permission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (ActivityCompat.checkSelfPermission(this, permission[0]) == PackageManager.PERMISSION_DENIED){
            ActivityCompat.requestPermissions(this, permission, 100);
        }
        ///////////////////////////

        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        fragments[3] = new Tab4Fragment();
        fragments[4] = new Tab5Fragment();


        fragmentManager = getSupportFragmentManager();



        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.container, fragments[0]);

        tran.commit();

        bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran = fragmentManager.beginTransaction();
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        fragment = fragments[0];
                        break;

                    case R.id.local:
                        fragment = fragments[1];
                        break;

                    case R.id.location:
                        fragment = fragments[2];
                        break;

                    case R.id.chat:
                        fragment = fragments[3];
                        break;

                    case R.id.account:
                        fragment = fragments[4];
                        break;
                }

                //기존 프래그먼트를 없애고 새로운 프래그먼트로 재배치
                tran.replace(R.id.container, fragment);
                //tran.addToBackStack(null);

                tran.commit();
                return false;
            }
        });
    }
}