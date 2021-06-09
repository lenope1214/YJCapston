package com.example.jmjapp.owner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.jmjapp.Adapter.BasketRecyclerAdapter;
import com.example.jmjapp.Adapter.PosMenuListAdapter;
import com.example.jmjapp.Adapter.QrMenuRecyclerAdapter;
import com.example.jmjapp.Adapter.TableDetailAdapter;
import com.example.jmjapp.databinding.ActivityTableDetailBinding;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.user.QrReaderActivity;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TableDetailActivity extends AppCompatActivity {
    private ActivityTableDetailBinding binding;

    private String tabId, shopId, no, tabNo ,orderId;
    private char using;
    private Call<Order.OrderMenuList> getOrderMenus;
    private int sum = 0;

    private RecyclerView rv_table_detail_list;
    private TableDetailAdapter adapter;
    private ArrayList<OrderMenu> mItems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        tabId = intent.getStringExtra("tabId");
        shopId = intent.getStringExtra("shopId");
        no = intent.getStringExtra("no");
        using = intent.getCharExtra("using", 'p');
        orderId = intent.getStringExtra("orderId");

        if (no.substring(0,1).equals("0")) {
            tabNo = no.substring(1,2);
        } else {
            tabNo = no;
        }

        Log.d("tabId", tabId);
        Log.d("shopId", shopId);
        Log.d("no", no);
        Log.d("tabNo", tabNo);
        Log.d("using", String.valueOf(using));
        Log.d("orderId", orderId);

        rv_table_detail_list = binding.rvTableDetailList;
        adapter = new TableDetailAdapter(getApplicationContext());

        getOrderMenus = Server.getInstance().getApi().orderOneMenu(orderId);
        getOrderMenus.enqueue(new Callback<Order.OrderMenuList>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<Order.OrderMenuList> call, Response<Order.OrderMenuList> response) {
                if (response.isSuccessful()) {
                    Log.d("orderMenu 성공", "orderMenu 성공");
                    List<OrderMenu> orderMenuList = response.body().getOrderMenuList();
                    Log.d("qwe",orderMenuList.toString());

                    for (OrderMenu list : orderMenuList) {
                        mItems.add(new OrderMenu(list.getMenuName(), list.getQuantity()));
                        Log.d("list", response.body().toString());
                        rv_table_detail_list.setHasFixedSize(true);
                        adapter.setItems(mItems);
                        rv_table_detail_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                        rv_table_detail_list.setAdapter(adapter);
                    }
                } else {
                    Log.d("orderMenu 실패1", "orderMenu 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<Order.OrderMenuList> call, Throwable t) {
                Log.d("orderMenu 실패2", "orderMenu 실패2");
            }
        });


    }
}