package com.example.jmjapp.owner.push;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.AlarmListRecyclerAdapter;
import com.example.jmjapp.Adapter.BasketRecyclerAdapter;
import com.example.jmjapp.Adapter.MenuRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityMain4Binding;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.owner.MainActivity_O;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BellActivity_O extends AppCompatActivity {
    private ActivityMain4Binding binding;
    View view;
    private AlarmListRecyclerAdapter adapter;
    private RecyclerView alarm_list;
    private ArrayList<Order> mItems = new ArrayList();

    private Call<List<Order>> listOrderCall;

    private String resTime, from, resDate, resShop, sum, count, orderId, jwt, resId, jwtUser;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.alarm_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        //textView = binding.textView;
        Log.d("bellactivity_o","oncreate");

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(this, new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult result) {
                String newToken = result.getToken();
                Log.d("se", newToken);
            }
        });

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", "");
        alarm_list = findViewById(R.id.alarm_list);

        Intent intent = getIntent();
        from = intent.getStringExtra("from");
//        resTime = intent.getStringExtra("resTime");
//        resDate = intent.getStringExtra("resDate");
//        sum = intent.getStringExtra("sum");
//        count = intent.getStringExtra("count");
//        resShop = intent.getStringExtra("resShop");
        orderId = intent.getStringExtra("orderId");
        resId = intent.getStringExtra("resId");
        jwtUser = intent.getStringExtra("jwt");
        //binding.qwe123.setText(resTime);

        if (from != null) {
            Log.d("from123", from);
            Log.d("resId", resId);
            Log.d("orderId", orderId);
            Log.d("jwt", jwt);

            listOrderCall = Server.getInstance().getApi().orderList("Bearer " + jwt, resId);
            listOrderCall.enqueue(new Callback<List<Order>>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (response.isSuccessful()) {
                        Log.d("bell성공", "bell성공");
                        List<Order> orderList = response.body();
                        for(Order list : orderList) {
                            if (list.getAccept() == 'N') {
                                mItems.add(new Order(list.getOrderId(), list.getStatus(), list.getOrderRequest(),
                                        list.getPeople(), list.getUsePoint(), list.getAmount(),
                                        list.getArriveTime(), list.getPayTime(), list.getPg(),
                                        list.getPayMethod(), list.getShopId(), list.getShopName(), list.getUserName(),
                                        list.getReason(), list.getReviewed(), list.getUserId(), list.getAccept()));
                                Log.d("accept", String.valueOf(list.getAccept()));
                                alarm_list.setHasFixedSize(true);
                                adapter = new AlarmListRecyclerAdapter(getApplicationContext(), mItems, jwtUser);
                                alarm_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                                alarm_list.setAdapter(adapter);
                                Log.d("awdawd", list.getOrderId());
                            }
                       }
                    } else {
                        Log.d("bell실패", "bell실패");
                    }
                }
                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    Log.d("bell실패2", "bell실패2");
                }
            });
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (intent != null) {
            Log.d("bellactivity_o","onnewintent");
            processIntent(intent);
        }
        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent) {
        String from = intent.getStringExtra("from");
        if (from == null) {
            return;
        }
        SharedPreferences pref = getSharedPreferences("res_order", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        String resTime = intent.getStringExtra("resTime");
        String resDate = intent.getStringExtra("resDate");
        String sum = intent.getStringExtra("sum");
        String count = intent.getStringExtra("count");
        String resShop = intent.getStringExtra("resShop");

        //binding.qwer123.setText(resTime);
        Log.d("qwe","qweqweqwe");
        editor.putString("resTime", resTime);
        editor.putString("resDate", resDate);
        editor.putString("count", count);
//
//        adapter = new AlarmListRecyclerAdapter(getApplicationContext());
//
//        mItems.add(resTime);
//        mItems.add(resDate);
//        mItems.add(sum);
//        mItems.add(count);
//        mItems.add(resShop);
//
//        alarm_list.setHasFixedSize(true);
//        adapter.setItems(mItems);
//        alarm_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        alarm_list.setAdapter(adapter);

        //textView.setText("[" + from + "]로부터 수신한 데이터 : " + resTime +"***"+ resDate+"***"+sum +"***"+ count+"***"+resShop);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (listOrderCall != null)
            listOrderCall.cancel();
    }
}
