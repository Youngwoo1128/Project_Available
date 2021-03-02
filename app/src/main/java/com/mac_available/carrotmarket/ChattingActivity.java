package com.mac_available.carrotmarket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChattingActivity extends AppCompatActivity {
    ListView listView;
    EditText editText;

    ArrayList<ChattingVO> items;
    ChattingAdapter adapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);
        listView = findViewById(R.id.chattingListView);
        editText = findViewById(R.id.et_chat);

        items = new ArrayList<>();

        adapter = new ChattingAdapter(this,items);
        listView.setAdapter(adapter);

       // Toast.makeText(this, "" + getIntent().getStringExtra("server"), Toast.LENGTH_SHORT).show();


        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference("chat").child(G.currentItem.masterId+"&&"+G.myId);
        if (getIntent().getStringExtra("server") != null){
            databaseReference = firebaseDatabase.getReference("chat").child(getIntent().getStringExtra("server"));
        }
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
               ChattingVO item = snapshot.getValue(ChattingVO.class);
               items.add(item);
               adapter.notifyDataSetChanged();
               listView.setSelection(items.size()-1);

//                // 새로 추가된 데이터 값 (DataSnapshot 이 촬영한 값)
//                MessageItem item = snapshot.getValue(MessageItem.class);
//
//                // 읽어들인 메세지를 리스트뷰가 보여주는 대량의 데이터에 추가
//                messageItems.add(item);
//
//                //리스트뷰 갱신 - 리스트뷰가 보여줄 뷰를 만들어내는 아답터에게 요청
//                chatAdapter.notifyDataSetChanged();
//                listView.setSelection(messageItems.size()-1); //리스트뷰의 마지막 위치로 스크롤 이동
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void clickSend(View view) {

        String data = editText.getText().toString();
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String visualTime = new SimpleDateFormat("HH : mm").format(new Date());
        if (data.equals("")) return;

        databaseReference.child(time).setValue(new ChattingVO(G.myId, data, visualTime)).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ChattingActivity.this, "전송완료", Toast.LENGTH_SHORT).show();
                editText.setText("");
            }
        });
    }
}