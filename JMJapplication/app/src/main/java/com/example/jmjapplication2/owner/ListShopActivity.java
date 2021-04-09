package com.example.jmjapplication2.owner;

import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.Adapter.RestaurantListRecyclerAdapter;
import com.example.jmjapplication2.JMJApplication;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Shop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class ListShopActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_restaurant_list;
    ArrayList<Shop> mItems = new ArrayList<>();

    TextView new_shop_register;

    private String jwt;
    private String owner_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shop);


        Intent intent = getIntent();
        int owner_number_size = intent.getIntExtra("owner_number_size", 1);
        owner_id = intent.getStringExtra("owner_id");
        jwt = ((JMJApplication)this.getApplication()).getJwt();

        String[] shopId = new String[owner_number_size];
        for (int i=0; i < owner_number_size; i++) {
            shopId[i] = intent.getStringExtra("owner_number" + i);
            Log.d("result", "shopId"+i+" : " + shopId[i]);
        }
        System.out.println(owner_id);
        System.out.println(jwt);

        new_shop_register = findViewById(R.id.new_shop_register);
        new_shop_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListShopActivity.this, RegisterShopActivity.class);
                intent.putExtra("jwt", jwt);
                startActivity(intent);
            }
        });

        rv_restaurant_list = findViewById(R.id.rv_restaurant_list_shop);

        showShopList();
    }

    private void showShopList() {
        dataService.read.myShop2(jwt).enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        List<Shop> shopList = response.body();
                        for(Shop list : shopList) {
                            mItems.add(new Shop(list.getId(), list.getName(),
                                    list.getIntro(), list.getCloseTime(),
                                    list.getOpenTime(), list.getAddress(),
                                    list.getAddressDetail(), list.getIsRsPos(),
                                    list.getCategory(), list.getIsOpen()));
                            rv_restaurant_list.setHasFixedSize(true);
                            adapter = new RestaurantListRecyclerAdapter(getApplicationContext(), mItems);
                            rv_restaurant_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            rv_restaurant_list.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "조회 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("앱을 종료하시겠습니까?");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        alBuilder.setTitle("앱 종료");
        alBuilder.show();
    }

}