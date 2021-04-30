package com.example.jmjapplication;

import android.app.Application;
import com.kakao.sdk.common.KakaoSdk;

public class KakaoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        KakaoSdk.init(this, "0b47713e4f8fdd3a9feafca8fef6516d");
    }
}
