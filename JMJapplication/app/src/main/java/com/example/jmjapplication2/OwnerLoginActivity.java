package com.example.jmjapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapplication2.dto.Shop;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnerLoginActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    EditText et_owner_id, et_owner_pw;
    TextView et_owner_join;
    Button owner_login_button;

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_login_o);

        Toolbar toolbar = (Toolbar) findViewById(R.id.login_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        et_owner_id = findViewById(R.id.et_owner_id);
        et_owner_pw = findViewById(R.id.et_owner_pw);
        et_owner_join = findViewById(R.id.et_owner_join);
        owner_login_button = findViewById(R.id.owner_login_button);

        owner_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_owner_id.getText().toString().length() == 0 || et_owner_pw.getText().toString().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(OwnerLoginActivity.this);
                    dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                    dialog.show();
                } else {
                    String id = et_owner_id.getText().toString();
                    String password = et_owner_pw.getText().toString();
                    Map<String ,String> map = new HashMap();
                    map.put("id", id);
                    map.put("password", password);

                    dataService.login.LoginOne(map).enqueue(new Callback<ResponseBody>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.code() == 200) {
                                JSONObject jsonObject = new JSONObject(response.body().string());
                                Log.d("result ", String.valueOf(jsonObject));
                                String jwt = (String) jsonObject.get("access_token");
                                String role = (String) jsonObject.get("role");
                                Log.d("jsonobject :: jwt >> ", jwt);
                                Log.d("jsonobject :: role >> ", role);
                                Log.d("jsonobject :: userid >> ", et_owner_id.getText().toString());

                                if(role.equals("ROLE_OWNER")) {
                                    SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pref.edit();
                                    editor.putString("token", jwt);
                                    editor.apply();

                                    ((JMJApplication)getApplication()).setId(et_owner_id.getText().toString());
                                    ((JMJApplication)getApplication()).setJwt(jwt);

                                    Log.d("result : ", "사업자 로그인 성공");
                                    dataService.myShop.myShop2("Bearer " + jwt).enqueue(new Callback<List<Shop>>() {
                                        @Override
                                        public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                                            if(response.code() == 200) {
                                                Log.d("result : ", "매장있음" + response.body().size());
                                                String[] shopId = new String[response.body().size()];
                                                for(int i =0; i < response.body().size(); i++) {
                                                    Log.d("result : ", "매장있음" + response.body().get(i).getId());
                                                    shopId[i] = response.body().get(i).getId();
                                                }
                                                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerLoginActivity.this);
                                                dialog = builder.setMessage("로그인되었습니다").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Intent intent = new Intent(OwnerLoginActivity.this, ListShopActivity.class);
                                                        intent.putExtra("owner_number_size", response.body().size());
                                                        intent.putExtra("owner_id", et_owner_id.getText().toString());
                                                        for (int i=0; i<response.body().size(); i++) {
                                                            intent.putExtra("owner_number" + i, response.body().get(i).getId());
                                                        }
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                }).create();
                                                builder.setCancelable(false);
                                                dialog.show();
                                            } else if(response.code() == 400) {
                                                Log.d("result : " , response.message());
                                                Log.d("result : ", "매장없음");
                                                Intent intent = new Intent(OwnerLoginActivity.this, ListShopActivity.class);
                                                intent.putExtra("owner_id", et_owner_id.getText().toString());
                                                intent.putExtra("jwt", jwt);
                                                startActivity(intent);
                                                finish();
                                            } else {
                                                Log.d("result : ", "연결실패");
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<Shop>> call, Throwable t) {

                                        }
                                    });
                                } else {
                                    Log.d("result : " , response.message());
                                    Log.d("result : " , "로그인실패");
                                    AlertDialog.Builder builder = new AlertDialog.Builder(OwnerLoginActivity.this);
                                    dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                                    dialog.show();
                                }
                            } else if(response.code() == 400) {
                                Log.d("result : " , response.errorBody().string());
                                Log.d("result : " , "로그인실패");
                                AlertDialog.Builder builder = new AlertDialog.Builder(OwnerLoginActivity.this);
                                dialog = builder.setMessage("아이디와 비밀번호를 확인해 주세요.").setPositiveButton("확인", null).create();
                                dialog.show();
                            } else {
                                Log.d("result : " , response.errorBody().string());
                                Log.d("result : " , "연결실패");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
                }
            }
        });

        et_owner_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OwnerLoginActivity.this, OwnerJoinActivity.class);
                startActivity(intent);
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