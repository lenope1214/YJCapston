package com.example.jmjapplication2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterNoActivity extends AppCompatActivity {
    TextView logout, register_shop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_no_shop);

        logout = findViewById(R.id.res_logout);
        register_shop = findViewById(R.id.res_shop_tv);

        register_shop.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(RegisterNoActivity.this, RegisterShopActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });

        logout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent intent = new Intent(RegisterNoActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                // 값버리기
                SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("token");
                editor.commit();

                // 앱 변수버리기
                ((JMJApplication)getApplication()).setId(null);

                return false;
            }
        });
    }
}
