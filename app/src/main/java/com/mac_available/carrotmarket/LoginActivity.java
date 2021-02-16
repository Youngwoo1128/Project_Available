package com.mac_available.carrotmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.kakao.sdk.auth.LoginClient;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.common.util.Utility;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String keyHash = Utility.INSTANCE.getKeyHash(this);
        Log.i("KeyHash", keyHash);

    }

    public void clickBtn(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void clickKakao(View view) {
        LoginClient.getInstance().loginWithKakaoAccount(this, new Function2<OAuthToken, Throwable, Unit>() {
            @Override
            public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                if (oAuthToken != null){
                    Toast.makeText(LoginActivity.this, "카카오계정으로 로그인이 완료 되었습니다.", Toast.LENGTH_SHORT).show();
                    UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                        @Override
                        public Unit invoke(User user, Throwable throwable) {
                            if(user !=null){
                                long id = user.getId();
                                String userId = String.valueOf(id);
                                SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences("userInfo", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("userKey", userId).commit();
                            }
                            return null;
                        }
                    });
                }else{
                    Toast.makeText(LoginActivity.this, "카카오계정으로 로그인이 실패 하였습니다.", Toast.LENGTH_SHORT).show();
                }
                return null;
            }
        });
    }
}