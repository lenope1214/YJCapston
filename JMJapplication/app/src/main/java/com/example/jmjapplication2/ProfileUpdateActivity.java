package com.example.jmjapplication2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileUpdateActivity extends AppCompatActivity {
    Button logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileupdate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileUpdateActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

                // 값버리기
                SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("token");
                editor.apply();

                // 앱 변수버리기
                ((JMJApplication)getApplication()).setId(null);
                ((JMJApplication)getApplication()).setJwt(null);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}