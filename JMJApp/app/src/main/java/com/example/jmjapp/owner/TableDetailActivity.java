package com.example.jmjapp.owner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;

import com.example.jmjapp.Adapter.BasketRecyclerAdapter;
import com.example.jmjapp.Adapter.PosMenuListAdapter;
import com.example.jmjapp.Adapter.QrMenuRecyclerAdapter;
import com.example.jmjapp.Adapter.TableDetailAdapter;
import com.example.jmjapp.R;
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

    private String tabId, shopId, no, tabNo, orderId;
    private char using;
    private Call<Order.OrderMenuList> getOrderMenus;
    private int sum = 0;

    private RecyclerView rv_table_detail_list;
    private TableDetailAdapter adapter;
    private ArrayList<OrderMenu> mItems = new ArrayList();

    Thread thread;
    boolean isThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTableDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.table_detail_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        Intent intent = getIntent();
        tabId = intent.getStringExtra("tabId");
        shopId = intent.getStringExtra("shopId");
        no = intent.getStringExtra("no");
        using = intent.getCharExtra("using", 'p');
        orderId = intent.getStringExtra("orderId");

        if (no.substring(0, 1).equals("0")) {
            tabNo = no.substring(1, 2);
        } else {
            tabNo = no;
        }

        binding.tableDetailNo.setText(tabNo + "번 테이블");

        rv_table_detail_list = binding.rvTableDetailList;
        adapter = new TableDetailAdapter(getApplicationContext());

        isThread = true;
        thread = new Thread() {
            public void run() {
                while (isThread) {
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendEmptyMessage(0);
                }
            }
        };
        thread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            mItems.clear();
            getOrderMenus = Server.getInstance().getApi().orderOneMenu(orderId);
            getOrderMenus.enqueue(new retrofit2.Callback<Order.OrderMenuList>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<Order.OrderMenuList> call, Response<Order.OrderMenuList> response) {
                    if (response.isSuccessful()) {
                        Log.d("orderMenu 성공", "orderMenu 성공");
                        List<OrderMenu> orderMenuList = response.body().getOrderMenuList();

                        for (OrderMenu list : orderMenuList) {
                            mItems.add(new OrderMenu(list.getMenuName(), list.getQuantity()));
                            Log.d("list", response.body().toString());
                            rv_table_detail_list.setHasFixedSize(true);
                            adapter.setItems(mItems);
                            rv_table_detail_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                            rv_table_detail_list.setAdapter(adapter);
                        }
                    } else {
                        Log.d("orderMenu 실패1", "orderMenu 실패1" + response.errorBody().string());
                    }
                }

                @Override
                public void onFailure(Call<Order.OrderMenuList> call, Throwable t) {
                    Log.d("orderMenu 실패2", "orderMenu 실패2");
                }
            });
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isThread = false;
        thread.interrupt();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}