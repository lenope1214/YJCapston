package com.example.jmjapp.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.*;
import com.example.jmjapp.Adapter.BasketRecyclerAdapter;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.network.Server;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketActivity extends AppCompatActivity {
    Button basket_reservation_btn;
    private RecyclerView rv_basket_list;
    private BasketRecyclerAdapter adapter;
    private ArrayList<Menu> mItems = new ArrayList();

    private String jwt, shopId;
    private Call<ResponseBody> responseBodyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Toolbar toolbar = (Toolbar) findViewById(R.id.basket_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        basket_reservation_btn = findViewById(R.id.basket_reservation_btn);
        rv_basket_list = (RecyclerView) findViewById(R.id.rv_basket_list);

        SharedPreferences pref = getSharedPreferences("basket", MODE_PRIVATE);
        SharedPreferences pref2 = getSharedPreferences("auth", MODE_PRIVATE);
        int list_size = pref.getInt("list_size", 0);
        jwt = pref2.getString("token", null);
        shopId = ShopDetailActivity.shopNumber;

        for(int i = list_size - 1; i >= 0; i--) {
            mItems.add(new Menu(i, pref.getString("list_" + i + "_name", "메뉴이름"),
                    pref.getInt("list_" + i + "_price", 0)));
        }
        rv_basket_list.setHasFixedSize(true);
        adapter = new BasketRecyclerAdapter(getApplicationContext(), mItems);
        rv_basket_list.setLayoutManager(new LinearLayoutManager(getApplication()));
        rv_basket_list.setAdapter(adapter);

        basket_reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasketActivity.this, OrderActivity.class);
                startActivity(intent);

//                Map<String, String> map = new HashMap();
//                map.put("shopId", shopId);
//
//                if (jwt != null) {
//                    responseBodyCall = Server.getInstance().getApi().order("Bearer " + jwt, map);
//                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
//                        @SneakyThrows
//                        @Override
//                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                            if (response.code() == 201) {
//                                Log.d("성공", "성공");
//                                JSONObject jsonObject = new JSONObject(response.body().string());
//                                Log.d("result ", String.valueOf(jsonObject));
//                                Long orderId = (Long) jsonObject.get("orderId");
//                                System.out.println(orderId);
//
//                                Intent intent = new Intent(BasketActivity.this, OrderActivity.class);
//                                intent.putExtra("orderId", orderId);
//                                startActivity(intent);
//                            } else {
//                                Log.d("실패", "실패" + response.code());
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Log.d("연결실패", "연결실패");
//                        }
//                    });
//                } else {
//                    Snackbar.make(v, "로그인이 필요한 서비스입니다.", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            return;
//                        }
//                    }).show();
//                }
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(responseBodyCall!=null)
            responseBodyCall.cancel();
    }

}