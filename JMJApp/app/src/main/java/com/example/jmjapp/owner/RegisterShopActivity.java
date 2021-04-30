package com.example.jmjapp.owner;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class RegisterShopActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    EditText shop_et_id, shop_et_name, shop_et_intro, shop_et_addr, shop_et_addr_detail;
    Spinner shop_isres, shop_category;
    TextView shop_open, shop_close;
    Button shop_register, search_addr;
    int t1Hour, t1Minute, t2Hour, t2Minute;

    private String jwt;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataService dataService = new DataService();
        
        String member_id = ((JMJApplication)this.getApplication()).getId();
        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        String jwt = pref.getString("token", null);
        System.out.println(jwt);

        //Log.d("res jwtjwtjwt", jwt);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_shop);

        shop_et_id = findViewById(R.id.shop_et_id);
        shop_et_name = findViewById(R.id.shop_et_name);
        shop_et_intro = findViewById(R.id.shop_et_intro);
        shop_et_addr = findViewById(R.id.shop_et_addr);
        shop_et_addr_detail = findViewById(R.id.shop_et_addr_detail);
        shop_open = findViewById(R.id.shop_et_open);
        shop_close = findViewById(R.id.shop_et_close);
        shop_category = findViewById(R.id.shop_et_category);
        shop_isres = findViewById(R.id.shop_et_isres);
        shop_register = findViewById(R.id.shop_register);
        search_addr = findViewById(R.id.search_addr);

        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shop_category.setAdapter(categoryAdapter);

        shop_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        RegisterShopActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @SneakyThrows
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                String time = t1Hour + ":" + t1Minute;
                                Log.d("daw", String.valueOf(t1Minute));


                                time(time, t1Minute, t1Hour, shop_open);
                            }
                        }, 12, 0, true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1Hour, t1Minute);
                timePickerDialog.show();
            }
        });

        shop_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        RegisterShopActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @SneakyThrows
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t2Hour = hourOfDay;
                                t2Minute = minute;

                                String time = t2Hour + ":" + t2Minute;
                                time(time, t2Minute, t2Hour, shop_close);
                            }
                        }, 12, 0, true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hour, t2Minute);
                timePickerDialog.show();
            }
        });

        search_addr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterShopActivity.this, WebViewActivity.class);
                startActivityForResult(intent, SEARCH_ADDRESS_ACTIVITY);
            }
        });

        shop_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody idBody = RequestBody.create(MediaType.parse("text/plain"), shop_et_id.getText().toString());
                RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), shop_et_name.getText().toString());
                RequestBody introBody = RequestBody.create(MediaType.parse("text/plain"), shop_et_intro.getText().toString());
                RequestBody addressBody = RequestBody.create(MediaType.parse("text/plain"), shop_et_addr.getText().toString());
                RequestBody address_detailBody = RequestBody.create(MediaType.parse("text/plain"), shop_et_addr_detail.getText().toString());
                RequestBody open_timeBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(shop_open.getText().toString()));
                RequestBody close_timeBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(shop_close.getText().toString()));
                RequestBody categoryBody = RequestBody.create(MediaType.parse("text/plain"), shop_category.getSelectedItem().toString());

                Map<String, RequestBody> map = new HashMap();
                map.put("id", idBody);
                map.put("name", nameBody);
                map.put("intro", introBody);
                map.put("address", addressBody);
                map.put("addressDetail", address_detailBody);
                map.put("openTime", open_timeBody);
                map.put("closeTime", close_timeBody);
                map.put("category", categoryBody);

                //Log.d("qweq@@@", String.valueOf(idBody.)+nameBody+introBody+addressBody+address_detailBody+open_timeBody+close_timeBody+categoryBody);

                dataService.create.insertShop("Bearer " + jwt, map).enqueue(new Callback<Shop>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<Shop> call, Response<Shop> response) {
                        if(response.code() == 201) {
                            Log.d("result : " , "매장등록 성공");
                            //Log.d("result : ", response.body().getShopId());
                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterShopActivity.this);
                            dialog = builder.setMessage("매장이 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(RegisterShopActivity.this, ListShopActivity.class);
                                    //intent.putExtra("owner_number", response.body().getId());
                                    startActivity(intent);
                                    finish();
                                }
                            }).create();
                            builder.setCancelable(false);
                            dialog.show();
                        } else if(response.code() == 400) {

                            Log.d("result : ", "매장등록 실패");
                        } else {

                            Log.d("result : " , "연결실패"+"@@@@@"+response.errorBody().string());
                        }
                    }

                    @Override
                    public void onFailure(Call<Shop> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
    }

    private void time(String time, int t2Minute, int t2Hour, TextView shop_update_closetime) {
        if (String.valueOf(t2Minute).toString().length() == 1 && String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0"+ t2Hour + ":" + "0"+ t2Minute);
        } else if(String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0"+ t2Hour + ":" + t2Minute);
        } else if(String.valueOf(t2Minute).toString().length() == 1) {
            shop_update_closetime.setText(t2Hour + ":" + "0"+ t2Minute);
        } else {
            shop_update_closetime.setText(time);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if(resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if(data != null) {
                        String data2 = data.substring(7);
                        shop_et_addr.setText(data2);
                    }
                }
                break;
        }
    }
}
