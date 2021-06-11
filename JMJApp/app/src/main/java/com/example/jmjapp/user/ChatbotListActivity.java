package com.example.jmjapp.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.jmjapp.Adapter.ChatbotListAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Chatbot;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ChatbotListActivity extends AppCompatActivity {
    ChatbotListAdapter adapter;
    RecyclerView rv_chatbots;
    ArrayList<Chatbot> mItems = new ArrayList<>();
    private Call<List<Chatbot>> listChatbotCall;
    private String jwt, shopId;

    TextView tv_shop_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot_list);

        tv_shop_name = findViewById(R.id.tv_shop_name);
        

    }
}