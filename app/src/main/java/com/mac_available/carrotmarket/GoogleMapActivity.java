package com.mac_available.carrotmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class GoogleMapActivity extends AppCompatActivity {

    GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);

        FragmentManager fragmentManager = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) fragmentManager.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                googleMap = googleMap;

                LatLng seoul = new LatLng(37.562087, 127.035192);
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 15)); //줌:1~25

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.title("왕십리");
                markerOptions.snippet("왕십리역에 있는 미래능력개발교육원");
                markerOptions.position(seoul);
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.mapicon32));
                markerOptions.anchor(0.5f, 1.0f);

                googleMap.addMarker(markerOptions);

                UiSettings settings= googleMap.getUiSettings();
                settings.setZoomControlsEnabled(true);

                settings.setMyLocationButtonEnabled(true);

                if (ActivityCompat.checkSelfPermission(GoogleMapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GoogleMapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
                    return;
                }
                googleMap.setMyLocationEnabled(true);
            }
        });

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED){
                String[] permission = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permission, 0);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_DENIED || grantResults[1] == PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this, "위치 서비스 사용불가", Toast.LENGTH_SHORT).show();
                }
                for (int i=0; i<grantResults.length; i++){
                    if (grantResults[i] == PackageManager.PERMISSION_DENIED){
                        Toast.makeText(this, "내 위치 사용불가", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                break;
        }
    }
}