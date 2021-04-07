package com.example.jmjapplication2;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jmjapplication2.dto.Menu;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class MenuRegisterActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    EditText menu_name_et, menu_intro_et, menu_price_et, menu_time_et;
    Button menu_register_btn;
    ImageView menu_register_img;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

        Intent intent = getIntent();
        String shopNumber = intent.getStringExtra("shopNumber");
        Log.d("awd2222222",shopNumber);

        menu_name_et = findViewById(R.id.menu_name_et);
        menu_intro_et = findViewById(R.id.menu_intro_et);
        menu_price_et = findViewById(R.id.menu_price_et);
        menu_time_et = findViewById(R.id.menu_time_et);
        menu_register_btn = findViewById(R.id.menu_register_btn);
        menu_register_img = findViewById(R.id.menu_register_img);




        menu_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody idBody = RequestBody.create(MediaType.parse("text.plain"), shopNumber);
                RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), menu_name_et.getText().toString());
                RequestBody introBody = RequestBody.create(MediaType.parse("text/plain"), menu_intro_et.getText().toString());
                RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), menu_price_et.getText().toString());
                RequestBody timeBody = RequestBody.create(MediaType.parse("text/plain"), menu_time_et.getText().toString());

                Map<String, RequestBody> map = new HashMap();
                map.put("shopId", idBody);
                map.put("name", nameBody);
                map.put("intro", introBody);
                map.put("price", priceBody);
                map.put("duration", timeBody);

                dataService.insertShop.insertMenu(map).enqueue(new Callback<Menu>() {
                    @Override
                    public void onResponse(Call<Menu> call, Response<Menu> response) {
                        if(response.code() == 201) {
                            Log.d("result : " , "메뉴등록 성공");
                            AlertDialog.Builder builder = new AlertDialog.Builder(MenuRegisterActivity.this);
                            dialog = builder.setMessage("매장이 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MenuRegisterActivity.this, MenuDetailActivity.class);
                                    //intent.putExtra("owner_number", response.body().getId());
                                    startActivity(intent);
                                    finish();
                                }
                            }).create();
                            builder.setCancelable(false);
                            dialog.show();
                        } else if(response.code() == 400) {
                            Log.d("result : ", "메뉴등록 실패");
                        } else {
                            Log.d("result : " , "연결실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<Menu> call, Throwable t) {
                        Log.d("result : " , "연결실패2");
                    }
                });
            }
        });

    }
}