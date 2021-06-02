package com.example.jmjapp.owner;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.Adapter.MyOrderListAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityChatbotManagementBinding;
import com.example.jmjapp.dto.Chatbot;
import com.example.jmjapp.dto.Employee;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.network.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ChatbotManagementActivity extends AppCompatActivity {
    ActivityChatbotManagementBinding binding;
    FloatingActionButton fab_addchatbots;
    RecyclerView rv_chatbots;
    ArrayList<Chatbot> mItems = new ArrayList<>();
    private Call<List<Chatbot>> listChatbotCall;
    private String jwt, shopId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatbotManagementBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fab_addchatbots = binding.fabAddchatbots;

        fab_addchatbots.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddChatbotActivity.class);
            startActivity(intent);
        });


        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", null);

        shopId = MainActivity_O.shopNumber;
        Toolbar toolbar = (Toolbar) findViewById(R.id.chatbot_toolbar);
        toolbar.setTitle("툴바셋타이틀");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);


        rv_chatbots = binding.rvChatbots;

        listChatbotCall = Server.getInstance().getApi().myShopsChatbots("Bearer " + jwt, shopId);
        listChatbotCall.enqueue(new Callback<List<Chatbot>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Chatbot>> call, Response<List<Chatbot>> response) {
                if (response.isSuccessful()) {
                    Log.d("mychatbots 성공", "mychatbots 성공");
//                    List<Order> orderList = response.body();
//                    Log.d("or", String.valueOf(orderList));
//                    mItems.clear();
//                    for (Order list : orderList) {
//                        mItems.add(new Order(list.getOrderId(), list.getStatus(), list.getOrderRequest(),
//                                list.getPeople(), list.getUsePoint(), list.getAmount(),
//                                list.getArriveTime(), list.getPayTime(), list.getPg(),
//                                list.getPayMethod(), list.getShopId(), list.getShopName(), list.getUserName(),
//                                list.getReason(), list.getReviewed(), list.getUserId(), list.getAccept()));
//                        Log.d("accept", String.valueOf(list.getAccept()));
//                        rs_order_list.setHasFixedSize(true);
//                        adapter = new MyOrderListAdapter(getActivity(), mItems);
//                        rs_order_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//                        rs_order_list.setAdapter(adapter);
                } else {
                    Log.d("mychatbots 실패1", "mychatbots 실패1" + response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<Chatbot>> call, Throwable t) {
                Log.d("mychatbots 실패2", "mychatbots 실패2" + t.getCause());
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "onStart 실행");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "onResume 실행");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach", "onDetach 실행");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "onDestroy 실행");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop", "onStop 실행");
    }

}