package com.example.jmjapp.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.jmjapp.Adapter.RestaurantRecyclerAdapter;
import com.example.jmjapp.Adapter.SearchShopAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityBellBinding;
import com.example.jmjapp.databinding.ActivitySearchShopListBinding;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchShopList extends AppCompatActivity {
    private ActivitySearchShopListBinding binding;

    private SearchShopAdapter adapter;
    private RecyclerView search_shop_list;
    ArrayList<Shop> mItems = new ArrayList<>();

    private Call<List<Shop>> listShopCall;

    private String shopName, jwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchShopListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.search_shop_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Intent intent = getIntent();
        shopName = intent.getStringExtra("shopName");

        binding.tvBell.setText(shopName);

        search_shop_list = binding.searchShopList;
        adapter = new SearchShopAdapter(getApplicationContext());

        showList(shopName);

    }

    private void showList(String shopName) {
        listShopCall = Server.getInstance().getApi().shopList2(null, shopName);
        listShopCall.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        List<Shop> shopList = response.body();
                        for(Shop list : shopList) {
                            Log.e("result : ", list.getCategory());
                            mItems.add(new Shop(list.getShopId(), list.getName(), list.getIntro(),
                                    list.getCloseTime(), list.getOpenTime(),
                                    list.getAddress(), list.getAddressDetail(), list.getIsRsPos(),
                                    list.getCategory(), list.getIsOpen(), list.getImgPath(), list.getOwnerId(), list.getPhone()));
                            search_shop_list.setHasFixedSize(true);
                            adapter.setItems(mItems);
                            search_shop_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            search_shop_list.setAdapter(adapter);
                        }
                    } else {
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}