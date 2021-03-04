package com.mac_available.available;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText etId,etPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        toolbar = findViewById(R.id.toolbarAccount);
        etId = findViewById(R.id.et_accountId);
        etPw = findViewById(R.id.et_accountPw);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void clickSaveACCOUNT(View view) {

        String Id = etId.getText().toString();
        String pw = etPw.getText().toString();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("users");

        databaseReference.child(Id).setValue(pw).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AccountActivity.this, "be succeed", Toast.LENGTH_SHORT).show();
                G.myId = Id;
                Intent intent = new Intent(AccountActivity.this, MainActivity.class);
                startActivity(intent);

            }
        });


    }
}