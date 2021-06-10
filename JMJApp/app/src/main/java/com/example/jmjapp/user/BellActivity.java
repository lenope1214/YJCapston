package com.example.jmjapp.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityBellBinding;
import com.example.jmjapp.databinding.ActivityMain4Binding;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.network.Server;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BellActivity extends AppCompatActivity {
    private ActivityBellBinding binding;
    private String device_token, jwt, orderId, msg;
    private Call<Order> orderCall;

    TextView user_alarm_shopName, user_alarm_result;
    ConstraintLayout no_orderId_cons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBellBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("com.google.android.gms.appid", MODE_PRIVATE);
        device_token = pref.getString("|T|217981185828|*", "HI, BITCH");

        SharedPreferences pref2 = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref2.getString("token", "qweqwqweqwee");

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        msg = intent.getStringExtra("msg");

        Log.d("qweqwe",jwt+"$$"+orderId);

        user_alarm_shopName = binding.userAlarmShopName;
        user_alarm_result = binding.userAlarmResult;
        no_orderId_cons = binding.noOrderIdCons;

        if (orderId == null) {
            no_orderId_cons.setVisibility(View.VISIBLE);
        }

        orderCall = Server.getInstance().getApi().orderOne("Bearer " + jwt, orderId);
        orderCall.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    no_orderId_cons.setVisibility(View.GONE);
                    Log.d("orderOne성공", "orderOne성공");
                    String shopName = response.body().getShopName();
                    user_alarm_shopName.setText(shopName);
                } else {
                    Log.d("orderOne실패1", "orderOne실패1");
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("orderOne실패2", "orderOne실패2" + t.getMessage() +"%%%%"+t.getCause());
            }
        });
        
        if(msg != null) {
            if (msg.equals("accept")) {
                user_alarm_result.setText("예약주문이 수락되었습니다!");
            } else if (msg.equals("reject")) {
                user_alarm_result.setText("에약주문이 거절되었습니다!");
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}