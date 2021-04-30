package com.example.jmjapp.user;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityOrderBinding;
import com.example.jmjapp.owner.ShopUpdateActivity;
import com.example.jmjapp.payment.PaymentWebview;

import java.util.Calendar;

import lombok.SneakyThrows;

public class OrderActivity extends AppCompatActivity {
    private static final int PAYMENT_ACTIVITY = 10000;

    private ActivityOrderBinding binding;
    private int count = 1;
    int t1Hour, t1Minute;
    DatePickerDialog.OnDateSetListener setListener;

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

        Intent intent = getIntent();
        Long orderId = intent.getLongExtra("orderId", 1);
        System.out.println(orderId);

        binding.orderShopName.setText(ShopDetailActivity.shopName);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.orderChooseDateImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(OrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = year+"/"+month+"/"+day;
                        binding.orderChooseDateText.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        binding.orderChooseTimeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        OrderActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @SneakyThrows
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                String time = t1Hour + ":" + t1Minute;
                                Log.d("daw", String.valueOf(t1Minute));


                                time(time, t1Minute, t1Hour, binding.orderChooseTimeText);
                            }
                        }, 12, 0, true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1Hour, t1Minute);
                timePickerDialog.show();
            }
        });

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

        binding.orderPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderActivity.this, PaymentWebview.class);
                startActivityForResult(intent, PAYMENT_ACTIVITY);
                /**
                *  웹뷰로 데이터 전달 필요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                **/
            }
        });
    }

    private void time(String time, int t2Minute, int t2Hour, TextView shop_update_closetime) {
        if (String.valueOf(t2Minute).toString().length() == 1 && String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0"+ t2Hour + ":" + "0"+ t2Minute);
        } else if(String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0"+ t2Hour + ":" + t2Minute);
        } else if(String.valueOf(t2Minute).toString().length() == 1) {
            shop_update_closetime.setText(t2Hour + ":" + "0"+ t2Minute);
        } else {
            shop_update_closetime.setText(time);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case PAYMENT_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(this, "HIHIHIHI!!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}