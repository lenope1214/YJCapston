package com.example.jmjapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jmjapp.owner.ListShopActivity;
import com.example.jmjapp.owner.OwnerLoginActivity;
import com.example.jmjapp.user.MainActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class IntroScreen extends AppCompatActivity {
    ImageView user_jmj_img, owner_jmj_img;
    TextView tv_userLogIn, tv_ownerLogIn;
    private AlertDialog dialog;
    String loginId, role, loginId2, role2;
    static public String newToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introscreen);

        makeDeviceToken();

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);

        loginId = pref.getString("user_id", null);
        role = pref.getString("role", null);

        SharedPreferences pref2 = getSharedPreferences("auth_o", MODE_PRIVATE);

        loginId2 = pref2.getString("owner_id", null);
        role2 = pref2.getString("role", null);

        if (loginId != null && role != null) {
            Intent intent = new Intent(IntroScreen.this, MainActivity.class);
            intent.putExtra("user_id", loginId);
            startActivity(intent);
            finish();
            //Log.d("결과@@#!@#", loginId + "님 일반회원 로그인 성공!");
        } else if (loginId2 != null && role2 != null) {
            Intent intent = new Intent(IntroScreen.this, ListShopActivity.class);
            intent.putExtra("owner_id", loginId2);
            startActivity(intent);
            finish();
        }

        tv_ownerLogIn = findViewById(R.id.tv_ownerLogIn);
        tv_userLogIn = findViewById(R.id.tv_userLogIn);

        tv_userLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(IntroScreen.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        tv_ownerLogIn.setOnClickListener(v -> {
            Intent intent = new Intent(IntroScreen.this, OwnerLoginActivity.class);
            startActivity(intent);
        });

    }

    private void makeDeviceToken() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, result -> {
            newToken = result.getToken();
            Log.d("이 기기의 토큰 ::: ", newToken);
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("앱을 종료하시겠습니까?");

        alBuilder.setPositiveButton("예", (dialog, which) -> finish());
        alBuilder.setNegativeButton("아니오", (dialog, which) -> {
            return;
        });
        alBuilder.setTitle("앱 종료");
        alBuilder.show();
    }

}