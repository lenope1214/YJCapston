package com.example.jmjapp.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MembershipWithdrawal extends AppCompatActivity {
    CheckBox cb_coupon;
    Button button_withdrawal;
    DataService dataService = new DataService();
    private String jwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_withdrawal);



        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        cb_coupon = (CheckBox) findViewById(R.id.cb_coupon);
        button_withdrawal = (Button)findViewById(R.id.button_withdrawal);

        cb_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedbox(cb_coupon.isChecked());
            }
        });

        // jwt 값 받아오기
        SharedPreferences pref = getSharedPreferences("auth",MODE_PRIVATE);
        jwt = pref.getString("token",null);
    }

    public void checkedbox(boolean checked){
        if(checked){
            button_withdrawal.setVisibility(View.VISIBLE);
            button_withdrawal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MembershipWithdrawal.this);
                    builder.setTitle("알림");
                    builder.setMessage("회원탈퇴하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dataService.delete.deleteOne("Bearer " + jwt).enqueue(new Callback<ResponseBody>() {
                                @SneakyThrows
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if(response.isSuccessful()){
                                            Log.d("탈퇴성공","ㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎㅎ");

                                        // 값버리기
                                        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = pref.edit();
                                        editor.remove("token");
                                        editor.apply();

                                        // 앱 변수버리기
                                        ((JMJApplication) getApplication()).setId(null);
                                        ((JMJApplication) getApplication()).setJwt(null);

                                        Intent intent = new Intent(MembershipWithdrawal.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);

                                    }else {
                                        Log.d("asdasd",response.errorBody().string());
                                        Log.d("탈퇴실패", "ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ");
                                    }
                                }

                                @SneakyThrows
                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("연결실패","ㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐㅐ");
                                }
                            });
                        }
                    });

                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //원래 페이지로 이동
                        }
                    });
                    builder.show();
                }
            });
        } else {
            button_withdrawal.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}