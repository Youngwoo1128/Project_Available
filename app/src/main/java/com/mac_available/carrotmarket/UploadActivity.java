package com.mac_available.carrotmarket;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class UploadActivity extends AppCompatActivity {

    ImageView imageView;
    EditText etTitle, etPrice, etContent;
    Uri imageUri;
    FirebaseStorage firebaseStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imageView = findViewById(R.id.image);
        etTitle = findViewById(R.id.title);
        etPrice = findViewById(R.id.price);
        etContent = findViewById(R.id.content);

    }

    public void clickImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK) {
            imageUri = data.getData();
            Glide.with(this).load(imageUri).into(imageView);

        }
    }

    String uploadUri;
    String title;
    String price;
    String content;
    String time;
    String location;
    FirebaseDatabase firebaseDatabase;

    public void clickUpload(View view) {
        firebaseStorage = FirebaseStorage.getInstance();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
        final StorageReference imageRef = firebaseStorage.getReference("images" + fileName);
        imageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uploadUri = uri.toString();
                        title = etTitle.getText().toString();
                        price = etPrice.getText().toString();
                        content = etContent.getText().toString();
                        time = new SimpleDateFormat("yyyy.MM.dd").format(new Date());
                        firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference itemRef = firebaseDatabase.getReference("items");
                        itemRef.push().setValue(new ProductVO(uploadUri, title, price, content,time, location)).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(UploadActivity.this, "uploaded", Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });


                    }
                });
            }
        });
    }

    public void clickLocation(View view) {
        Intent intent = new Intent(this, )
    }
}