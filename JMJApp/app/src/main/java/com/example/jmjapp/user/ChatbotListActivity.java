package com.example.jmjapp.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jmjapp.Adapter.ChatbotListUserAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Chatbot;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatbotListActivity extends AppCompatActivity {
    ChatbotListUserAdapter adapter;
    RecyclerView rv_chatbots;
    ArrayList<Chatbot> mItems = new ArrayList<>();
    private Call<List<Chatbot>> listChatbotCall;
    private String jwt, shopId, qrShopId, qr, shopName;

    TextView tv_shop_name, chatbot_answer2;
    ImageView imageView25;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot_list);

        Intent intent = getIntent();
        qrShopId = intent.getStringExtra("shopId");
        qr = intent.getStringExtra("qr");
        shopName = intent.getStringExtra("shopName");

        tv_shop_name = findViewById(R.id.tv_shop_name);
        rv_chatbots = findViewById(R.id.rv_chatbots);
        chatbot_answer2 = findViewById(R.id.chatbot_answer2);
        imageView25 = findViewById(R.id.imageView25);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", null);

        if (qr == null) {
            // 식당id
            shopId = ShopDetailActivity.shopNumber;

            tv_shop_name.setText(ShopDetailActivity.shopName);

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
                            adapter = new ChatbotListUserAdapter(getApplicationContext(), mItems);
                            rv_chatbots.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.addOnClick((view, position) -> {
                                Chatbot chatbot1 = adapter.getItem(position);
                                chatbot_answer2.setText(chatbot1.getAnswer());
                            });
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
        } else {
            tv_shop_name.setText(shopName);

            listChatbotCall = Server.getInstance().getApi().myShopsChatbots("Bearer " + jwt, qrShopId);
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
                            adapter = new ChatbotListUserAdapter(getApplicationContext(), mItems);
                            rv_chatbots.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            adapter.addOnClick((view, position) -> {
                                Chatbot chatbot1 = adapter.getItem(position);
                                chatbot_answer2.setText(chatbot1.getAnswer());
                            });
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

    }
}