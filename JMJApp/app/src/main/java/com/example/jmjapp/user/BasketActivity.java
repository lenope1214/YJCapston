package com.example.jmjapp.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.*;
import com.example.jmjapp.Adapter.BasketRecyclerAdapter;
import com.example.jmjapp.dto.Menu;

import java.util.ArrayList;

public class BasketActivity extends AppCompatActivity {
    Button basket_reservation_btn;
    private RecyclerView rv_basket_list;
    private BasketRecyclerAdapter adapter;
    private ArrayList<Menu> mItems = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        Toolbar toolbar = (Toolbar) findViewById(R.id.basket_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        basket_reservation_btn = findViewById(R.id.basket_reservation_btn);
        rv_basket_list = (RecyclerView) findViewById(R.id.rv_basket_list);

        SharedPreferences pref = getSharedPreferences("basket", MODE_PRIVATE);
        int list_size = pref.getInt("list_size", 0);

        for(int i = list_size - 1; i >= 0; i--) {
            mItems.add(new Menu(i, pref.getString("list_" + i + "_name", "메뉴이름"),
                    pref.getInt("list_" + i + "_price", 0)));
        }
        rv_basket_list.setHasFixedSize(true);
        adapter = new BasketRecyclerAdapter(getApplicationContext(), mItems);
        rv_basket_list.setLayoutManager(new LinearLayoutManager(getApplication()));
        rv_basket_list.setAdapter(adapter);

        basket_reservation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BasketActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}