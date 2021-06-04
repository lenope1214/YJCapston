package com.example.jmjapp.owner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.Adapter.ChatbotListAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityChatbotManagementBinding;
import com.example.jmjapp.dto.Chatbot;
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
    ChatbotListAdapter adapter;
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
            Intent intent = new Intent(this, ChatbotSettingActivity.class);
            intent.putExtra("isAdd", true);
            startActivity(intent);
        });




    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "onStart 실행");
        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", null);

        shopId = MainActivity_O.shopNumber;
        Toolbar toolbar = (Toolbar) findViewById(R.id.chatbot_toolbar);
        toolbar.setTitle(""); // 이거 없으면 JMJApplication 뜸.
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
                    Log.d("chatbot response : ", "onResponse: " + response.body());
                    List<Chatbot> chatbotList = response.body();
                    mItems.clear();
                    for (Chatbot chatbot : chatbotList) {
                        mItems.add(Chatbot.builder()
                                .chatbotId(chatbot.getChatbotId())
                                .question(chatbot.getQuestion())
                                .answer(chatbot.getAnswer())
                                .build());
                        System.out.println("chatbotList is null ? " + chatbotList == null);

                        rv_chatbots.setHasFixedSize(true);
                        adapter = new ChatbotListAdapter(getApplicationContext(), mItems);
                        rv_chatbots.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv_chatbots.setAdapter(adapter);

                    }
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
    public void onResume() {
        super.onResume();
        Log.d("onResume", "onResume 실행");

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