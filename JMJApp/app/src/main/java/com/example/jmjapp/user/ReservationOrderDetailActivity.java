package com.example.jmjapp.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityMain4Binding;
import com.example.jmjapp.databinding.ActivityReservationManagementBinding;
import com.example.jmjapp.databinding.ActivityReservationOrderDetailBinding;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.network.Server2;

import java.sql.Timestamp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationOrderDetailActivity extends AppCompatActivity {
    private ActivityReservationOrderDetailBinding binding;
    private String orderId, jwt;

    private Call<Order> orderCall;
    private Call<Shop> shopCall;

    private Call<ResponseBody> responseBodyCall;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationOrderDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.res_order_detail_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");

        binding.resOrderDetailMenuWatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ReservationOrderDetailActivity.this, "h2", Toast.LENGTH_SHORT).show();
            }
        });

        orderCall = Server.getInstance().getApi().orderOne("Bearer " + jwt, orderId);
        orderCall.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if (response.isSuccessful()) {
                    Log.d("orderOne 성공", "orderOne 성공");

                    shopCall = Server.getInstance().getApi().shop(response.body().getShopId());
                    shopCall.enqueue(new Callback<Shop>() {
                        @Override
                        public void onResponse(Call<Shop> call, Response<Shop> response) {
                            if (response.isSuccessful()) {
                                Log.d("shopOne 성공", "shopOne 성공");
                                binding.resOrderDetailAddr.setText(response.body().getAddress());
                                binding.resOrderDetailAddrDetail.setText(response.body().getAddressDetail());
                            } else {
                                Log.d("shopOne 실패1", "shopOne 실패1");
                            }
                        }

                        @Override
                        public void onFailure(Call<Shop> call, Throwable t) {
                            Log.d("shopOne 실패2", "shopOne 실패2");
                        }
                    });

                    if (response.body().getAccept() == 'N') {
                        binding.resOrderDetailIsAccept.setText("주문 대기 중");
                    } else {
                        binding.resOrderDetailIsAccept.setText("주문완료");
                        //binding.resOrderDetailIsAccept.setTextColor(Color.parseColor("#33FA01"));
                    }

                    if (response.body().getOrderRequest() == null) {
                        binding.resOrderDetailRequest.setText("요청사항이 없습니다");
                    } else {
                        binding.resOrderDetailRequest.setText(response.body().getOrderRequest());
                    }

                    binding.resOrderDetailShopName.setText(response.body().getShopName());
                    binding.resOrderDetailPayment.setText(response.body().getAmount() + "원");

                    String payTime = response.body().getPayTime();
                    String year = payTime.substring(0, 4);
                    String month = payTime.substring(5, 7);
                    String day = payTime.substring(8, 10);

                    String hour = payTime.substring(10, 12);
                    String min = payTime.substring(13, 15);

                    binding.resOrderDetailPayTime.setText(year+"년 "+month+"월 "+day+"일 "+hour+"시 "+min+"분");

                    String resTime = String.valueOf(new Timestamp(response.body().getArriveTime()));
                    String year1 = resTime.substring(0,4);
                    String month1 = resTime.substring(5,7);
                    String day1 = resTime.substring(8,10);

                    String hour1 = resTime.substring(11,13);
                    String min1 = resTime.substring(14,16);

                    binding.resOrderDetailResTime.setText(year1+"년 "+month1+"월 "+day1+"일 "+hour1+"시 "+min1+"분");
                } else {
                    Log.d("orderOne 실패1", "orderOne 실패1");
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("orderOne 실패2", "orderOne 실패2");
            }
        });

        binding.cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("알림");
                builder.setMessage("주문 취소(환불) 하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userRefund();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println("주문취소 안함");
                    }
                });
                builder.show();
            }
        });

    }

    private void userRefund() {
        responseBodyCall = Server2.getInstance2().getApi2().userRefund("Bearer " + jwt, orderId);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("사용자 측 환불 성공", "사용자 측 환불 성공");
                    finish();
                } else {
                    Log.d("사용자 측 환불 실패1", "사용자 측 환불 실패1");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("사용자 측 환불 실패2", "사용자 측 환불 실패2");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (orderCall != null)
            orderCall.cancel();
        if (shopCall != null)
            shopCall.cancel();
    }
}