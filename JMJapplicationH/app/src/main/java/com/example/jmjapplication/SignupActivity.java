package com.example.jmjapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapplication.dto.MemberDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    EditText et_id;
    EditText et_pw;
    EditText et_name;
    EditText et_phone;
    RadioGroup radioGroup;
    RadioButton rb_user;
    RadioButton rb_owner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Toolbar toolbar = (Toolbar) findViewById(R.id.signup_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);

        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);

        Button btn_insert = findViewById(R.id.btn_next);
        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, String> map = new HashMap();
                map.put("userid", et_id.getText().toString());
                map.put("userpw", et_pw.getText().toString());
                map.put("name", et_name.getText().toString());
                map.put("phone", et_phone.getText().toString());

                RadioGroup rg = (RadioGroup) findViewById(R.id.rg_radiogroup);
                int id = rg.getCheckedRadioButtonId();
                if(id == R.id.rb_user) {
                    map.put("role", "ROLE_USER");
                } else {
                    map.put("role", "ROLE_OWNER");
                }

                dataService.insert.insertOne(map).enqueue(new Callback<MemberDTO>() {
                    @Override
                    public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                        Toast.makeText(getApplicationContext(), "유저 등록 완료", Toast.LENGTH_LONG).show();
                        et_id.setText("");
                        et_pw.setText("");
                        et_name.setText("");
                        et_phone.setText("");

                        Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<MemberDTO> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
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
