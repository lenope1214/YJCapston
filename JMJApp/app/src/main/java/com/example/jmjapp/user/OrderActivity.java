package com.example.jmjapp.user;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity {
    private ActivityOrderBinding binding;
    private int count = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.order_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        binding.orderShopName.setText(ShopDetailActivity.shopName);

        binding.orderShopAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                builder.setTitle("가게상세주소");
                builder.setMessage(ShopDetailActivity.shopAddress + "\n" + ShopDetailActivity.shopDetailAddress)
                .setPositiveButton("확인", null).create();
                builder.show();
            }
        });

        SharedPreferences pref = getSharedPreferences("basket", MODE_PRIVATE);
        int list_size = pref.getInt("list_size", 0);
        int sum = 0;
        for (int i = 0; i < list_size; i++) {
            Integer[] priceList = new Integer[list_size];
            priceList[i] = pref.getInt("list_" + i + "_price", 0);
            sum = sum + priceList[i];
            Log.d("가격", String.valueOf(priceList[i]) + "#@2@2" + sum);
        }

        binding.orderPayMoney.setText(String.valueOf(sum)+"원");

        binding.orderCountPeople.setText(count+"명");
        binding.orderCountPeoplePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count < 10) {
                    count++;
                    binding.orderCountPeople.setText(count + "명");
                } else {
                    binding.orderCountPeopleWarning.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.orderCountPeopleMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count > 1) {
                    count--;
                    binding.orderCountPeople.setText(count + "명");
                    binding.orderCountPeopleWarning.setVisibility(View.GONE);
                }
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