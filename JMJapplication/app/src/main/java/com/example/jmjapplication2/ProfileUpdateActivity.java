package com.example.jmjapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ProfileUpdateActivity extends AppCompatActivity {
    TextView logout_btn, user_profile_id;
    private AlertDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileupdate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        String id =  ((JMJApplication)getApplication()).getId();

        user_profile_id = findViewById(R.id.user_profile_id);
        user_profile_id.setText(id);

        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                builder.setTitle("알림");
                builder.setMessage("로그아웃하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("ㄱㄷㄴ","Awda");
                    }
                });
                builder.show();
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