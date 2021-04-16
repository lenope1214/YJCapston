package com.example.jmjapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jmjapplication2.owner.ListShopActivity;
import com.example.jmjapplication2.owner.OwnerLoginActivity;
import com.example.jmjapplication2.user.MainActivity;

public class IntroScreen extends AppCompatActivity {
    ImageView user_jmj_img, owner_jmj_img;
    private AlertDialog dialog;
    String loginId, role, loginId2, role2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introscreen);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);

        loginId = pref.getString("user_id", null);
        role = pref.getString("role", null);

        SharedPreferences pref2 = getSharedPreferences("auth_o", MODE_PRIVATE);

        loginId2 = pref2.getString("owner_id", null);
        role2 = pref2.getString("role", null);

        if(loginId != null && role != null) {
            Intent intent = new Intent(IntroScreen.this, MainActivity.class);
            intent.putExtra("user_id", loginId);
            startActivity(intent);
            finish();
            //Log.d("결과@@#!@#", loginId + "님 일반회원 로그인 성공!");
        } else if(loginId2 != null && role2 != null) {
            Intent intent = new Intent(IntroScreen.this, ListShopActivity.class);
            intent.putExtra("owner_id", loginId2);
            startActivity(intent);
            finish();
        }

        user_jmj_img = findViewById(R.id.user_jmj_img);
        owner_jmj_img = findViewById(R.id.owner_jmj_img);

        user_jmj_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroScreen.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        owner_jmj_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroScreen.this, OwnerLoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("앱을 종료하시겠습니까?");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alBuilder.setTitle("앱 종료");
        alBuilder.show();
    }

}