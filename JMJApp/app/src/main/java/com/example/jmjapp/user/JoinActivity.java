package com.example.jmjapp.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jmjapp.R;
import com.example.jmjapp.network.Server;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class JoinActivity extends AppCompatActivity {

    EditText et_id;
    EditText et_pw;
    EditText et_repw;
    EditText et_name;
    EditText et_phone;
    Button btn_insert;
    Button btn_id_validate;
    boolean isChecked;
    private AlertDialog dialog;
    private Call<ResponseBody> responseBodyCall;
    private Call<String> stringCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.signup_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);

        et_id = (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_password);
        et_repw = (EditText) findViewById(R.id.et_password2);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_insert = findViewById(R.id.btn_next);
        btn_id_validate = findViewById(R.id.id_validate);

        btn_id_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_id.equals("") || !(et_id.getText().toString().length() >= 2 && et_id.getText().toString().length() <= 10)) {
                    Toast.makeText(JoinActivity.this, "아이디는 2~8자로 입력해주세요!", Toast.LENGTH_SHORT).show();
                } else {
                    responseBodyCall = Server.getInstance().getApi().validateOne(et_id.getText().toString());
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                            if (response.code() == 200) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("사용 가능한 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                isChecked = true;

                            } else if (response.code() == 400) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                                dialog = builder.setMessage("이미 사용 중인 아이디입니다.").setPositiveButton("확인", null).create();
                                dialog.show();
                                isChecked = false;
                                et_id.setText("");
                                et_id.requestFocus();

                            } else {
                                Log.d("D", response.errorBody().string());
                                Log.d("result ", "연결실패312");
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
                if (!(et_id.getText().toString().length() >= 2 && et_id.getText().toString().length() <= 10)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("아이디는 2~8자로 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_id.requestFocus();
                } else if (!(et_pw.getText().toString().length() >= 8 && et_pw.getText().toString().length() <= 20)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("비밀번호는 8~20자로 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_pw.requestFocus();
                } else if (!(et_pw.getText().toString().equals(et_repw.getText().toString()))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("비밀번호를 정확하게 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_repw.requestFocus();
                } else if (!(et_name.getText().toString().matches("^[a-zA-Zㄱ-ㅎ가-힣]+$"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("이름을 정확하게 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_name.requestFocus();
                } else if (et_phone.getText().toString().length() != 11 || !(et_phone.getText().toString().matches("^[0-9]+$"))) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("전화번호를 정확하게 입력해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                    et_phone.requestFocus();
                } else if (isChecked == false) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                    dialog = builder.setMessage("아이디 중복체크를 해주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                } else {
                    Map<String, String> map = new HashMap();
                    map.put("id", et_id.getText().toString());
                    map.put("password", et_pw.getText().toString());
                    map.put("name", et_name.getText().toString());
                    map.put("phone", et_phone.getText().toString());
                    map.put("role", "ROLE_USER");

                    stringCall = Server.getInstance().getApi().join(map);
                    stringCall.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                            dialog = builder.setMessage("회원가입되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(JoinActivity.this, LoginActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
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
                        public void onFailure(Call<String> call, Throwable t) {
                            t.printStackTrace();
                        }
                    });
                }
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
    protected void onDestroy(){
        super.onDestroy();
        if(responseBodyCall!=null)
            responseBodyCall.cancel();
        if(stringCall!=null)
            stringCall.cancel();
    }
}

