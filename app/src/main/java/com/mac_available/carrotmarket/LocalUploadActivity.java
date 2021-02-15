package com.mac_available.carrotmarket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

public class LocalUploadActivity extends AppCompatActivity {

    EditText et_local, et_local2;
    Button localUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_upload);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = firebaseDatabase.getReference();

        et_local = findViewById(R.id.et_local);
        et_local2 = findViewById(R.id.et_local2);
        localUpload = findViewById(R.id.localUpload);

        localUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nick = et_local.getText().toString();
                String msg = et_local2.getText().toString();

                LocalVO local = new LocalVO(nick, msg);

                DatabaseReference localRef = rootRef.child("local");
                localRef.push().setValue(local).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(LocalUploadActivity.this, "!!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });

//                localRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        StringBuffer buffer = new StringBuffer();
//                        for (DataSnapshot snap : snapshot.getChildren() ){
//                            LocalVO local = snap.getValue(LocalVO.class);
//                            String nick = local.nick;
//                            String msg = local.msg;
//
//                            buffer.append(nick + "\n" + msg + "\n\n\n");
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });

            }
        });
    }
}