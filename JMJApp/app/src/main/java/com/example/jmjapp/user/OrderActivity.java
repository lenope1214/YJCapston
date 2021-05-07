package com.example.jmjapp.user;

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
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityOrderBinding;
import com.example.jmjapp.dto.MemberDTO;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.payment.PaymentWebview;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private static final int PAYMENT_ACTIVITY = 10000;

    private ActivityOrderBinding binding;
    private int count = 1;
    int t1Hour, t1Minute;
    DatePickerDialog.OnDateSetListener setListener;
    private AlertDialog dialog;
    private Call<MemberDTO> memberDTOCall;
    private Call<Shop> shopCall;
    private int sum = 0;
    private String resTime, resDate, resName, resPhone, resAddr, resShop, jwt;
    private Long orderId;

    private String order_request_et;

    private Call<ResponseBody> responseBodyCall;

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
        orderId = intent.getLongExtra("orderId", 1);
        System.out.println(orderId);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "tokenIsNull");

//        memberDTOCall = Server.getInstance().getApi().getUser("Bearer " + jwt);
//        memberDTOCall.enqueue(new Callback<MemberDTO>() {
//            @Override
//            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
//                if (response.code() == 200) {
//                    MemberDTO.User user = new MemberDTO.User();
//                    user = response.body().getUser();
//                    Log.d("rse", response.body().toString());
//                    resName = user.getName();
//                    resPhone = user.getPhone();
//                    Log.d("result999 ", "성공" + resName + resPhone);
//                } else {
//                    Log.d("result ", "연결실패1" + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<MemberDTO> call, Throwable t) {
//                Log.d("result ", "연결실패2");
//            }
//        });

        shopCall = Server.getInstance().getApi().shop(ShopDetailActivity.shopNumber);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.code() == 200) {
                    resAddr = response.body().getAddress();
                    resShop = response.body().getName();
                    Log.d("result123 ", "성공" + resShop + resAddr);
                } else {
                    Log.d("wlfkfdmfgofk", "wlfkfdmfgofk");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
            }
        });

        order_request_et = binding.orderRequestEt.getText().toString();

        binding.orderShopName.setText(ShopDetailActivity.shopName);

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.orderChooseDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(OrderActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        resDate = year + "년" + month + "월" + day + "일";
                        binding.orderChooseDateText.setText(resDate);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        binding.orderChooseTimeText.setOnClickListener(new View.OnClickListener() {
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

                                resTime = t1Hour + "시" + t1Minute + "분";
                                Log.d("daw", String.valueOf(t1Minute));


                                time(resTime, t1Minute, t1Hour, binding.orderChooseTimeText);
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

        pref = getSharedPreferences("basket", MODE_PRIVATE);
        int list_size = pref.getInt("list_size", 0);
        for (int i = 0; i < list_size; i++) {
            Integer[] priceList = new Integer[list_size];
            priceList[i] = pref.getInt("list_" + i + "_price", 0);
            sum = sum + priceList[i];
        }

        binding.orderPayMoney.setText(String.valueOf(sum) + "원");

        binding.orderCountPeople.setText(count + "명");
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

        binding.orderPossibleCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderActivity.this, "no", Toast.LENGTH_SHORT).show();
            }
        });

        binding.orderPossiblePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderActivity.this, "no", Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("orderId1", String.valueOf(orderId));
        Log.d("orderRequest1", binding.orderRequestEt.getText().toString());
        Log.d("people1", String.valueOf(count));
        Log.d("amount2", String.valueOf(sum));

        binding.orderPayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.orderChooseTimeText.getText().toString().equals("예약시간") ||
                        binding.orderChooseDateText.getText().toString().equals("예약날짜")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
                    dialog = builder.setMessage("예약날짜와 예약시간을 확인해주세요!").setPositiveButton("확인", null).create();
                    dialog.show();
                } else {
                    Map<String, String> map = new HashMap();
                    map.put("orderId", String.valueOf(orderId));
                    map.put("orderRequest", binding.orderRequestEt.getText().toString());
                    map.put("people", String.valueOf(count));

                    Log.d("orderId1", String.valueOf(orderId));
                    Log.d("orderRequest1", binding.orderRequestEt.getText().toString());
                    Log.d("people1", String.valueOf(count));

                    responseBodyCall = Server.getInstance().getApi().updateOrder("Bearer " + jwt, map);
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Log.d("연결성공여부", String.valueOf(response.code()));
                            } else {
                                Log.d("연결실패1", "연결실패1");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("연결실패2", "연결실패2");
                        }
                    });

                    Map<String, String> map2 = new HashMap();
                    map2.put("orderId", String.valueOf(orderId));
                    map2.put("amount", String.valueOf(sum));
                    map2.put("usePoint", "359");
                    map2.put("pg", "inicis");
                    map2.put("payMethod", "card");

                    Log.d("orderId2", String.valueOf(orderId));
                    Log.d("amount2", String.valueOf(sum));


                    responseBodyCall = Server.getInstance().getApi().payment("Bearer " + jwt, map2);
                    responseBodyCall.enqueue(new Callback<ResponseBody>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Log.d("성공123", "성공123");

                                Intent intent = new Intent(OrderActivity.this, PaymentWebview.class);
                                intent.putExtra("shopNumber", ShopDetailActivity.shopNumber);
                                intent.putExtra("price", sum);
                                intent.putExtra("people", count);
                                intent.putExtra("resDate", resDate);
                                intent.putExtra("resTime", resTime);
                                intent.putExtra("resShop", resShop);
                                intent.putExtra("resAddr", resAddr);
                                intent.putExtra("resName", resName);
                                intent.putExtra("resPhone", resPhone);
                                intent.putExtra("orderId", orderId);
                                startActivityForResult(intent, PAYMENT_ACTIVITY);
                                /**
                                 *  웹뷰로 데이터 전달 필요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                 **/
                            } else {
                                Log.d("실패13", "실패13" + response.errorBody().string() + response.code());
                                return;
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("실패2", "실패2");
                        }
                    });


                }
            }
        });
    }

    private void time(String time, int t2Minute, int t2Hour, TextView shop_update_closetime) {
        if (String.valueOf(t2Minute).toString().length() == 1 && String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0" + t2Hour + "시" + ":" + "0" + t2Minute + "분");
        } else if (String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0" + t2Hour + "시" + ":" + t2Minute + "분");
        } else if (String.valueOf(t2Minute).toString().length() == 1) {
            shop_update_closetime.setText(t2Hour + "시" + ":" + "0" + t2Minute + "분");
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
        if (item.getItemId() == android.R.id.home) {
            AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
            alBuilder.setMessage("다음에 다시 주문하시겠습니까?");

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
            alBuilder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("다음에 다시 주문하시겠습니까?");

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
        alBuilder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (memberDTOCall != null)
            memberDTOCall.cancel();
        if (shopCall != null)
            shopCall.cancel();
        if (responseBodyCall != null)
            responseBodyCall.cancel();
    }
}