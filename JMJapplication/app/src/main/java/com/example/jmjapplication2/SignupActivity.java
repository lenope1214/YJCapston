package com.example.jmjapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapplication2.dto.MemberDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import java.util.HashMap;
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
    boolean isChecked;
    private AlertDialog dialog;

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
                    dataService.validate.validateOne(et_id.getText().toString()).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()) {
                                Log.e("결과", "연결성공!");

                                ResponseBody body = response.body();
                                String result = null;
                                try {
                                    result = body.string();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                if (result.trim().length() == 0) {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                    dialog = builder.setMessage("이미 사용 중인 아이디입니다.").setPositiveButton("확인", null).create();
                                    dialog.show();
                                    isChecked = false;
                                    et_id.setText("");
                                    et_id.requestFocus();
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                                    dialog = builder.setMessage("사용 가능한 아이디입니다.").setPositiveButton("확인", null).create();
                                    dialog.show();
                                    isChecked = true;
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

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
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("아이디는 2~8자로 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_id.requestFocus();
                } else if(!(et_pw.getText().toString().length() >= 8 && et_pw.getText().toString().length() <= 20)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("비밀번호는 8~20자로 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_pw.requestFocus();
                } else if(!(et_name.getText().toString().matches("^[a-zA-Zㄱ-ㅎ가-힣]+$"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("이름을 정확하게 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_name.requestFocus();
                } else if(et_phone.getText().toString().length() != 11 || !(et_phone.getText().toString().matches("^[0-9]+$"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("전화번호를 정확하게 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_phone.requestFocus();
                } else if(id != R.id.rb_owner && id != R.id.rb_user) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("회원 종류를 선택해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                } else if(isChecked == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                    dialog = builder.setMessage("아이디 중복체크를 해주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
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
                            AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                            dialog = builder.setMessage("회원가입되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).create();
                            builder.setCancelable(false);
                            dialog.show();
                            et_id.setText("");
                            et_pw.setText("");
                            et_name.setText("");
                            et_phone.setText("");
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
