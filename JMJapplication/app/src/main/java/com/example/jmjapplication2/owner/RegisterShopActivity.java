package com.example.jmjapplication2.owner;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.jmjapplication2.JMJApplication;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Shop;
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
    Spinner shop_open, shop_close, shop_isres, shop_category;
    Button shop_register, search_addr;

    private String jwt;
    private AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataService dataService = new DataService();
        
        String member_id = ((JMJApplication)this.getApplication()).getId();
        String jwt = ((JMJApplication)this.getApplication()).getJwt();

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

        //spinner
        ArrayAdapter openAdapter = ArrayAdapter.createFromResource(this, R.array.open_time, android.R.layout.simple_spinner_item);
        openAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shop_open.setAdapter(openAdapter);

        ArrayAdapter closeAdapter = ArrayAdapter.createFromResource(this, R.array.close_time, android.R.layout.simple_spinner_item);
        closeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shop_close.setAdapter(closeAdapter);

        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(this, R.array.category, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shop_category.setAdapter(categoryAdapter);

//        ArrayAdapter isresAdapter = ArrayAdapter.createFromResource(this, R.array.reservation, android.R.layout.simple_spinner_item);
//        isresAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        shop_isres.setAdapter(isresAdapter);

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
                RequestBody open_timeBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(shop_open.getSelectedItem()));
                RequestBody close_timeBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(shop_close.getSelectedItem()));
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


//                map.put("id", shop_et_id.getText().toString());
//                map.put("name", shop_et_name.getText().toString());
//                map.put("intro", shop_et_intro.getText().toString());
//                map.put("address", shop_et_addr.getText().toString());
//                map.put("address_detail", shop_et_addr_detail.getText().toString());
//                map.put("open_time", shop_open.getSelectedItem());
//                map.put("close_time", shop_close.getSelectedItem());
//                map.put("category", shop_category.getSelectedItem().toString());
                //map.put("isRePos", shop_isres.getSelectedItem().toString());
                //map.put("member_id", member_id);

//                SharedPreferences pref =  getSharedPreferences("auth", MODE_PRIVATE);
//                String jwt = pref.getString("token", "");
//                Log.d("awdkmawd", jwt);

                dataService.create.insertShop("Bearer " + jwt, map).enqueue(new Callback<Shop>() {
                    @Override
                    public void onResponse(Call<Shop> call, Response<Shop> response) {
                        if(response.code() == 201) {
                            Log.d("result : " , "매장등록 성공");
                            Log.d("result : ", response.body().getId());
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
                            Log.d("result : " , "연결실패");
                        }
//                        if(response.isSuccessful()) {
//                            Log.d("result : " , "연결성공");
//                            AlertDialog.Builder builder = new AlertDialog.Builder(RegisterShopActivity.this);
//                            dialog = builder.setMessage("매장이 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    Intent intent = new Intent(RegisterShopActivity.this, MainActivity_O.class);
//                                    startActivity(intent);
//                                }
//                            }).create();
//                            builder.setCancelable(false);
//                            dialog.show();
//                        } else {
//                            Log.d("result : " , "연결실패");
//                        }
                    }

                    @Override
                    public void onFailure(Call<Shop> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });
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
