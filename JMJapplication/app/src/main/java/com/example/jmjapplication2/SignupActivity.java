package com.example.jmjapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapplication2.dto.MemberDTO;
import com.example.jmjapplication2.dto.Validate;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    MemberDTO memberDTO;
    EditText et_id;
    EditText et_pw;
    EditText et_name;
    EditText et_phone;
    Button btn_insert;
    Button btn_id_validate;

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
        btn_insert = findViewById(R.id.btn_next);;
        btn_id_validate = findViewById(R.id.id_validate);

        btn_id_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_id.equals("") || !(et_id.getText().toString().length() >= 2 && et_id.getText().toString().length() <= 10)) {
                    Toast.makeText(SignupActivity.this, "아이디는 2~8자로 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    dataService.validate.validateOne(et_id.getText().toString()).enqueue(new Callback<Validate>() {
                        @Override
                        public void onResponse(Call<Validate> call, Response<Validate> response) {
                            if(response.isSuccessful() && response.body() != null) {
                                Boolean validate = response.body().getResult();
                                if(validate) {
                                    Toast.makeText(SignupActivity.this, "사용가능", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignupActivity.this, "사용 불 가능", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Validate> call, Throwable t) {

                        }
                    });
                }
            }
        });

        btn_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup rg = (RadioGroup) findViewById(R.id.rg_radiogroup);
                int id = rg.getCheckedRadioButtonId();

                if(!(et_id.getText().toString().length() >= 2 && et_id.getText().toString().length() <= 10)) {
                    Toast.makeText(SignupActivity.this, "아이디는 2~8자로 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(!(et_pw.getText().toString().length() >= 8 && et_pw.getText().toString().length() <= 20)) {
                    Toast.makeText(SignupActivity.this, "비밀번호는 8~20자로 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(!(et_name.getText().toString().matches("^[a-zA-Zㄱ-ㅎ가-힣]+$"))) {
                    Toast.makeText(SignupActivity.this, "이름을 정확하게 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(et_phone.getText().toString().length() != 11 || !(et_phone.getText().toString().matches("^[0-9]+$"))) {
                    Toast.makeText(SignupActivity.this, "전화번호를 정확하게 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else if(id != R.id.rb_owner && id != R.id.rb_user) {
                    Toast.makeText(SignupActivity.this, "회원 종류를 선택해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    Map<String, String> map = new HashMap();
                    map.put("userid", et_id.getText().toString());
                    map.put("userpw", et_pw.getText().toString());
                    map.put("name", et_name.getText().toString());
                    map.put("phone", et_phone.getText().toString());

                    if (id == R.id.rb_user) {
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

                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<MemberDTO> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
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
