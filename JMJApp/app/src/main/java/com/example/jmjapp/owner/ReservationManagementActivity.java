package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.jmjapp.Adapter.AlarmListRecyclerAdapter;
import com.example.jmjapp.Adapter.ReservationManagementAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityReservationManagementBinding;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationManagementActivity extends AppCompatActivity {
    private ActivityReservationManagementBinding binding;

    private ReservationManagementAdapter adapter;
    private RecyclerView res_manage_list;
    private ArrayList<Order> mItems = new ArrayList();
    private Call<List<Order>> listOrderCall;

    private String jwt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationManagementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.res_manage_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        res_manage_list = (RecyclerView) findViewById(R.id.res_manage_list);

        listOrderCall = Server.getInstance().getApi().orderList("Bearer " + jwt, MainActivity_O.shopNumber);
        listOrderCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    List<Order> orderList = response.body();
                    for(Order list : orderList) {
                        if (list.getAccept() == 'Y') {
                            mItems.add(new Order(list.getOrderId(), list.getStatus(), list.getOrderRequest(),
                                    list.getPeople(), list.getUsePoint(), list.getAmount(),
                                    list.getArriveTime(), list.getPayTime(), list.getPg(),
                                    list.getPayMethod(), list.getShopId(), list.getShopName(), list.getUserName(),
                                    list.getReason(), list.getReviewed(), list.getUserId(), list.getAccept()));
                            Log.d("accept", String.valueOf(list.getAccept()));
                            res_manage_list.setHasFixedSize(true);
                            adapter = new ReservationManagementAdapter(getApplicationContext(), mItems);
                            res_manage_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                            res_manage_list.setAdapter(adapter);
                        }
                    }
                } else {
                    Log.d("res_manage실패1", "res_manage실패1");
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("res_manage실패2", "res_manage실패2");
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