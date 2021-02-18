package com.mac_available.carrotmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LocalUploadActivity extends AppCompatActivity {

    EditText et_local, et_local2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_upload);

        et_local = findViewById(R.id.et_local);
        et_local2 = findViewById(R.id.et_local2);
    }



    public void localUpload(View view) {

        String name = et_local.getText().toString();
        String msg = et_local2.getText().toString();

        Retrofit retrofit = RetrofitHelper.getRetrofitInstanceScalars();
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);

        Map<String, String> dataPart = new HashMap<>();
        dataPart.put("name", name);
        dataPart.put("msg", msg);

        Call<String> call = retrofitService.postDataToServer(dataPart);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String s = response.body();
                Toast.makeText(LocalUploadActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(LocalUploadActivity.this, "error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        finish();
    }
}