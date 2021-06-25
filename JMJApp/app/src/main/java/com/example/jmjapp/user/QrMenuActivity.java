package com.example.jmjapp.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jmjapp.Adapter.DetailPagerAdapter;
import com.example.jmjapp.Adapter.MenuRecyclerAdapter;
import com.example.jmjapp.Adapter.QrMenuRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityOrderBinding;
import com.example.jmjapp.databinding.ActivityQrMenuBinding;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrMenuActivity extends AppCompatActivity {
    private ActivityQrMenuBinding binding;

    private Call<List<Menu>> listMenuCall;
    private ArrayList<Menu> mItems = new ArrayList<>();
    private QrMenuRecyclerAdapter adapter;
    private RecyclerView rv_qr_menu_list;

    static public String shopNumber;
    static public char is_rs_pos;

    private Call<Shop> shopCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.qr_menu_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        Intent intent = getIntent();
        String shopId = intent.getStringExtra("shopId");
        Log.d("shopId", shopId);

        shopCall = Server.getInstance().getApi().shop(shopId);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.code() == 200) {
                    Shop shop = response.body();
                    is_rs_pos = shop.getIsRsPos();
                    shopNumber = shop.getShopId();
                } else {
                    Log.d("result : ", "상세 조회 실패");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
            }
        });

        rv_qr_menu_list = binding.rvQrMenuList;
        adapter = new QrMenuRecyclerAdapter(getApplicationContext());

        listMenuCall = Server.getInstance().getApi().menuList(shopId);
        listMenuCall.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        Log.d("성공", "성공");
                        List<Menu> menuList = response.body();
                        for (Menu list : menuList) {
                            mItems.add(new Menu(list.getMenuId(), list.getName(), list.getIntro(),
                                    list.getIsSale(), list.getIsPopular(), list.getPrice(),
                                    list.getDuration(), list.getImgPath()));
                            Log.d("list", response.body().toString());
                            rv_qr_menu_list.setHasFixedSize(true);
                            adapter.setItems(mItems);
                            rv_qr_menu_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                            rv_qr_menu_list.setAdapter(adapter);
                        }
                    } else {
                        Log.d("ㅅㅍ", "ㅅㅍ");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.d("성공ㅅㅍ", "성공ㅅㅍ");
            }
        });

        binding.qrOrderBasket.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("basket", MODE_PRIVATE);
            int list_size = pref.getInt("list_size", 0);
            if (list_size == 0) {
                Toast.makeText(QrMenuActivity.this, "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent1 = new Intent(QrMenuActivity.this, BasketActivity.class);
                intent1.putExtra("shopNumber", shopId);
                intent1.putExtra("qr", "qr");
                startActivity(intent1);
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
}