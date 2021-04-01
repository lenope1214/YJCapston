package com.example.jmjapplication2;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class IntroScreen extends AppCompatActivity {
    ImageView user_jmj_img, owner_jmj_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.introscreen);

        user_jmj_img = findViewById(R.id.user_jmj_img);
        owner_jmj_img = findViewById(R.id.owner_jmj_img);

        user_jmj_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntroScreen.this, MainActivity.class);
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



}