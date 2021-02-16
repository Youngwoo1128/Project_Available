package com.mac_available.carrotmarket;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class KakaoLogin extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        KakaoSdk.init(this, "fc216ad897603be180f518dac5355d5e");
    }
}
