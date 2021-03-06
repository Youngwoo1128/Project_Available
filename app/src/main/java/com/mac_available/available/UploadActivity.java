package com.mac_available.available;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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
    TextView tvLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imageView = findViewById(R.id.image);
        etTitle = findViewById(R.id.title);
        etPrice = findViewById(R.id.price);
        etContent = findViewById(R.id.content);
        tvLocation = findViewById(R.id.locationID);

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
        if (requestCode == 100 && resultCode == RESULT_OK){
            String dataStringExtra = data.getStringExtra("location");
            tvLocation.setText(dataStringExtra);
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
        title = etTitle.getText().toString();
        price = etPrice.getText().toString();
        content = etContent.getText().toString();
        firebaseStorage = FirebaseStorage.getInstance();
        location = tvLocation.getText().toString();
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".png";
        final StorageReference imageRef = firebaseStorage.getReference("images" + fileName);
        if (imageUri == null || title == null || title.equals("") || price == null || price.equals("") || content ==  null || content.equals("") || location == null || location.equals("")){
            Toast.makeText(UploadActivity.this, "올리실 제품을 상세히 적어주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        imageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        uploadUri = uri.toString();
                        time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
                        String visualTime = new SimpleDateFormat("MM_dd-HH:mm").format(new Date());

                        firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference itemRef = firebaseDatabase.getReference("items");
                        itemRef.push().setValue(new ProductVO(uploadUri, title, price, content,location,visualTime,G.myId)).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        ProgressDialog progressDialog;
        progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("잠시만 기다려주세요~~");
        progressDialog.setCancelable(false);
        progressDialog.show();


    }

    public void clickLocation(View view) {
        Intent intent = new Intent(this, LocationSearchActivity.class);
        startActivityForResult(intent, 100);
    }
}