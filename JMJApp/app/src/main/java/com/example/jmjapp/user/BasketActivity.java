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
import com.example.jmjapp.dto.Basket;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.network.Server;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    private String jwt;
    private Call<ResponseBody> responseBodyCall;


    SharedPreferences orderNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Toolbar toolbar = (Toolbar) findViewById(R.id.basket_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

//        shopNumber = ShopDetailActivity.shopNumber;
//        tableNumber = getIntent().getStringExtra("tableNumber");
//        orderNumber = getSharedPreferences("orderCheck",MODE_PRIVATE);
//        orderCheck = orderNumber.getInt("OrderNumber", 0);

        Intent intent = getIntent();
        String qr = intent.getStringExtra("qr");
        String shopId = intent.getStringExtra("shopNumber");

        System.out.println(ShopDetailActivity.shopNumber + "!@#" + ShopDetailActivity.tableNumber + "!@#" + ShopDetailActivity.orderCheck + "!@#");

        basket_reservation_btn = findViewById(R.id.basket_reservation_btn);
        rv_basket_list = (RecyclerView) findViewById(R.id.rv_basket_list);

        SharedPreferences pref = getSharedPreferences("basket", MODE_PRIVATE);
        SharedPreferences pref2 = getSharedPreferences("auth", MODE_PRIVATE);

        int list_size = pref.getInt("list_size", 0);
        jwt = pref2.getString("token", null);

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

//                try {
//                    ShopDetailActivity.orderCheck.equals("1");
//                } catch (Exception e) {
//                    ShopDetailActivity.orderCheck = "0";
//                }

                if(qr != null){
                    Log.d("qr주문","qr주문");
                    Log.d("shopId", shopId);
                    Log.d("orderId", QrReaderActivity.orderId);

                    SharedPreferences pref3 = getSharedPreferences("basket", MODE_PRIVATE);
                    int list_size2 = pref3.getInt("list_size", 0);

                    String[] list_id =  new String[list_size2];
                    int[] list_count = new int[list_size2];

                    for (int i = 0; i < list_size2; i++) {
                        list_id[i] = pref3.getString("list_" + i + "_id", null);
                        list_count[i] = pref3.getInt("list_" + i + "_count", 0);
                    }

                    Map<String, List<OrderMenu>> map = new HashMap();
                    List<OrderMenu> omList = new ArrayList<>();

                    for (int i = 0; i < list_size2; i++) {
                        OrderMenu om = new OrderMenu().builder()
                                .orderId(String.valueOf(QrReaderActivity.orderId))
                                .shopId(shopId)
                                .menuId(list_id[i])
                                .quantity(String.valueOf(list_count[i]))
                                .build();
                        omList.add(om);
                    }

                    map.put("list", omList);

                    responseBodyCall = Server.getInstance().getApi().order_menus("Bearer " + jwt, map);
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Log.d("orderMenu 성공", "orderMenu 성공");
                                Log.d("qweasd", response.body().string());
                            } else {
                                Log.d("orderMenu 실패1", "orderMenu 실패1"+response.errorBody().string());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("orderMenu 실패2", "orderMenu 실패2");
                        }
                    });

                    Intent intent = new Intent(BasketActivity.this, MainActivity.class);
                    intent.putExtra("QROrder","QROrder");
                    intent.putExtra("shopNumber", shopId);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(BasketActivity.this, OrderActivity.class);
                    startActivity(intent);
//                    Map<String, String> map = new HashMap();
//                    map.put("shopId", ShopDetailActivity.shopNumber);
//
//                    if (jwt != null) {
//                        responseBodyCall = Server.getInstance().getApi().order("Bearer " + jwt, map);
//                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
//                            @SneakyThrows
//                            @Override
//                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                                if (response.code() == 201) {
//                                    Log.d("성공", "성공");
//                                    JSONObject jsonObject = new JSONObject(response.body().string());
//                                    Log.d("result ", String.valueOf(jsonObject));
//                                    Long orderId = (Long) jsonObject.get("orderId");
//                                    System.out.println(orderId);
//
//                                    Intent intent = new Intent(BasketActivity.this, OrderActivity.class);
//                                    intent.putExtra("orderId", orderId);
//                                    startActivity(intent);
//                                } else {
//                                    Log.d("실패", "실패" + response.code());
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                                Log.d("연결실패", "연결실패");
//                            }
//                        });
//                    } else {
//                        Snackbar.make(v, "로그인이 필요한 서비스입니다.", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                return;
//                            }
//                        }).show();
//                    }
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

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(responseBodyCall!=null)
            responseBodyCall.cancel();
    }

}
