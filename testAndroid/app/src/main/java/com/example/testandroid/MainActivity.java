package com.example.testandroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    EditText et_id;
    EditText et_pw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        et_id = (EditText) findViewById(R.id.edit_id);
        et_pw = (EditText) findViewById(R.id.edit_pw);

        Button btn_insert = findViewById(R.id.btn_signup);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap();
                map.put("userid", et_id.getText().toString());
                map.put("userpw", et_pw.getText().toString());
                dataService.insert.insertOne(map).enqueue(new Callback<MemberDTO>() {
                    @Override
                    public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                        Toast.makeText(getApplicationContext(), "유저 등록 완료", Toast.LENGTH_LONG).show();
                        et_id.setText("");
                        et_pw.setText("");
                    }

                    @Override
                    public void onFailure(Call<MemberDTO> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }
}