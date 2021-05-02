package com.example.jmjapp.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;
import com.example.jmjapp.network.Server;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileUpdateActivity extends AppCompatActivity {

    TextView logout_btn, user_profile_id, delete_id;
    EditText update_password;
    Button update_pw_btn;
    private AlertDialog dialog;
    private String jwt;
    private Call<ResponseBody> responseBodyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileupdate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        String id = ((JMJApplication) getApplication()).getId();

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", null);
        Log.d("llllllllllllllllll", "Bearer " + jwt);

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
                        editor.remove("user_id");
                        editor.remove("role");
                        editor.apply();

                        // 앱 변수버리기
                        ((JMJApplication) getApplication()).setId(null);
                        ((JMJApplication) getApplication()).setJwt(null);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("ㄱㄷㄴ", "Awda");
                    }
                });
                builder.show();
            }
        });
        delete_id = findViewById(R.id.delete_id);
        delete_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileUpdateActivity.this, MembershipWithdrawal.class);
                startActivity(intent);
            }
        });

        update_password = findViewById(R.id.update_password);
        update_pw_btn = findViewById(R.id.update_pw_btn);


        update_pw_btn.setOnClickListener((v) -> {
            String newpassword = update_password.getText().toString();
            if (newpassword.length() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                dialog = builder.setMessage("비밀번호를 확인해주세요.").setPositiveButton("확인", null).create();
                dialog.show();
            } else {
                Map<String, String> map = new HashMap();
                map.put("password", newpassword);

                responseBodyCall = Server.getInstance().getApi().updateOne("Bearer " + jwt, map);
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(response.code() + "7777777777777777777");
                        if (response.code() == 200) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                            dialog = builder.setMessage("비밀번호가 변경되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ProfileUpdateActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).create();
                            builder.setCancelable(false);
                            dialog.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                            dialog = builder.setMessage("비밀번호 변경에 실패했습니다.").setPositiveButton("확인", null).create();
                            dialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("연결실패", "왜???????????");

                    }
                });
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (responseBodyCall != null)
            responseBodyCall.cancel();
    }

}