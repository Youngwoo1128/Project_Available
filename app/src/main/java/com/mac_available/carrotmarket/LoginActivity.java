package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    EditText etId, etPw;

    String id;
    String pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String keyHash = Utility.INSTANCE.getKeyHash(this);
        Log.i("KeyHash", keyHash);

        etId = findViewById(R.id.et_login_inputid);
        etPw = findViewById(R.id.et_login_inputpw);

    }

    public void clickBtn(View view) {

        id = etId.getText().toString();
        pw = etPw.getText().toString();

        if (id == null || id.equals("") || pw ==null || pw.equals("")) {
            Toast.makeText(this, "아이디와 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child(id);
        databaseReference.get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getValue() == null) {
                    Toast.makeText(LoginActivity.this, "아이디가 없습니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                String str = dataSnapshot.getValue(String.class);
                if (pw.equals(str)) {
                    Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    G.myId = id;
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    byte[] account_id = pw.getBytes();
//                    String userId = String.valueOf(account_id);
                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("userKey", id).commit();
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(LoginActivity.this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(LoginActivity.this, ""+str, Toast.LENGTH_SHORT).show();
            }
        });



//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);

    }

    public void clickKakao(View view) {
        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null) {
                    Toast.makeText(LoginActivity.this, "카카오계정으로 로그인이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if (user != null) {
                                long id = user.getId();
                                String userId = String.valueOf(id);
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                G.myId = "kakao"+userId;
                                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userKey", userId).commit();
                                startActivity(intent);
                                finish();

//                                Intent intent = new Intent(LoginActivity.this, AccountActivity.class);
//                                startActivity(intent);
//                                finish();
                            }
                            return null;
                        }
                    });
                } else {
                    Toast.makeText(LoginActivity.this, "카카오계정으로 로그인이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });
    }

    public void clickAccount(View view) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }

    public void clickTour(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(this, "로그인을 하지 않으면 원활한 이용을 할 수 없습니다.", Toast.LENGTH_SHORT).show();
    }
}