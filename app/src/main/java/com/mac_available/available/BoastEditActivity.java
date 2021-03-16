package com.mac_available.available;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoastEditActivity extends AppCompatActivity {

    EditText etName, etMsg;
    ImageView iv;
    Uri imageUri;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boast_edit);

        etName = findViewById(R.id.name);
        etMsg = findViewById(R.id.msg);
        iv = findViewById(R.id.iv);

    }

    public void clickBoast(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 20 && resultCode == RESULT_OK){
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(iv);
        }
    }

    String uploadUri;
    String title;
    String msg;
    FirebaseDatabase firebaseDatabase;


    public void clickBoastUpload(View view) {
        title = etName.getText().toString();
        msg = etMsg.getText().toString();
        if (imageUri == null || title == null || title.equals("") || msg == null || msg.equals("")){
            new AlertDialog.Builder(this).setMessage("상세히 적어주세요!!").setPositiveButton("확인", null).create().show();
            return;
        }
        firebaseStorage = FirebaseStorage.getInstance();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
        final StorageReference imgRef = firebaseStorage.getReference("boast" + fileName);
        imgRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imgRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uploadUri = uri.toString();

                        firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference itemRef = firebaseDatabase.getReference("boast");
                        itemRef.push().setValue(new BoastVO(uploadUri,title,msg)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(BoastEditActivity.this, "uploaded", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                });
            }
        });

        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("잠시만 기다려주세요~~~");
        progressDialog.setCancelable(false);
        progressDialog.show();

    }
}