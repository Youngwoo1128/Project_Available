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
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

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
//        fragments[1] = new Tab2Fragment();
//        fragments[2] = new Tab3Fragment();
//        fragments[3] = new Tab4Fragment();
//        fragments[4] = new Tab5Fragment();


        fragmentManager = getSupportFragmentManager();



        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.container, fragments[0]);

        tran.commit();

        bnv = findViewById(R.id.bnv);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentTransaction tran = fragmentManager.beginTransaction();
                for (int i=0 ; i<fragments.length; i++){
                    if (fragments[i] != null)
                    tran.hide(fragments[i]);
                }
                Fragment fragment = null;
                switch (item.getItemId()){
                    case R.id.home:
                        if (fragments[0] == null) {
                            fragments[0] = new Tab1Fragment();
                            tran.add(R.id.container, fragments[0]);
                        }
                        tran.show(fragments[0]);
//                        fragment = fragments[0];
                        break;

                    case R.id.local:
                        if (fragments[1] == null) {
                            fragments[1] = new Tab2Fragment();
                            tran.add(R.id.container, fragments[1]);
                        }
                        tran.show(fragments[1]);
                       // fragment = fragments[1];
                        break;

                    case R.id.location:
                        if (fragments[2] == null) {
                            fragments[2] = new Tab3Fragment();
                            tran.add(R.id.container, fragments[2]);
                        }
                        tran.show(fragments[2]);
                        //fragment = fragments[2];
                        break;

                    case R.id.chat:
                        if (fragments[3] == null) {
                            fragments[3] = new Tab4Fragment();
                            tran.add(R.id.container, fragments[3]);
                        }
                        tran.show(fragments[3]);
                        //fragment = fragments[3];
                        break;

                    case R.id.account:
                        if (fragments[4] == null) {
                            fragments[4] = new Tab5Fragment();
                            tran.add(R.id.container, fragments[4]);
                        }
                        tran.show(fragments[4]);
                        //fragment = fragments[4];
                        break;
                }

                //기존 프래그먼트를 없애고 새로운 프래그먼트로 재배치
//                tran.replace(R.id.container, fragment);
                //tran.addToBackStack(null);

                tran.commit();
                return false;
            }
        });
    }
}