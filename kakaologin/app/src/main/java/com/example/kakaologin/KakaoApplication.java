package com.example.kakaologin;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "5c4fc2e644db927b5409d24364c75dc7");
    }
}
