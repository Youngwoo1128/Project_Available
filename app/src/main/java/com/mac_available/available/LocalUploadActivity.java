package com.mac_available.available;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

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
        if (name == null || name.equals("") || msg ==null || msg.equals("")){
            new AlertDialog.Builder(this).setMessage("상세히 적어주시기 바랍니다.").setPositiveButton("확인",null).create().show();
            return;
        }

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



        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()){
                    Toast.makeText(LocalUploadActivity.this, "앱 등록실패", Toast.LENGTH_SHORT).show();
                    return;
                }

                String token = task.getResult();

                //Toast.makeText(LocalUploadActivity.this, ""+token, Toast.LENGTH_SHORT).show();
                Log.i("TOKEN", token);
            }
        });

        finish();

    }

}