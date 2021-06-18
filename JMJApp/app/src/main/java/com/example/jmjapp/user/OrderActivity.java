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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityOrderBinding;
import com.example.jmjapp.dto.MemberDTO;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.payment.PaymentWebview;
import com.google.android.gms.dynamic.IFragmentWrapper;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    private static final int PAYMENT_ACTIVITY = 10000;

    private ActivityOrderBinding binding;
    static public int count = 1;
    int t1Hour, t1Minute;
    DatePickerDialog.OnDateSetListener setListener;
    private AlertDialog dialog;
    private Call<MemberDTO> memberDTOCall;
    private Call<Shop> shopCall;
    static public int sum, sum2 = 0;
    static public String resTime, resDate, resId, resName, resPhone, resAddr, resShop, jwt, userId, resDate2;
    static public Long orderId;

    EditText order_request_et;

    static public String orderRequest;

    private Call<ResponseBody> responseBodyCall;
    private Call<Order> orderCall;
    private int point, usePoint;

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
        userId = pref.getString("user_id", "");
        Log.d("userid",userId);

        memberDTOCall = Server.getInstance().getApi().getUser("Bearer " + jwt);
        memberDTOCall.enqueue(new Callback<MemberDTO>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                if (response.code() == 200) {
                    Log.d("유저조회 성공 ", "유저조회 성공");
                    point = response.body().getUser().getPoint();
                    Log.d("point", String.valueOf(point));
                } else {
                    Log.d("유저조회 실패1 ", "유저조회 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Log.d("유저조회 실패2 ", "유저조회 실패2"+t.getCause());
            }
        });

        shopCall = Server.getInstance().getApi().shop(ShopDetailActivity.shopNumber);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.code() == 200) {
                    resAddr = response.body().getAddress();
                    resShop = response.body().getName();
                    resId = response.body().getShopId();
                    Log.d("result123 ", "성공" + resShop + resAddr + resId);
                } else {
                    Log.d("wlfkfdmfgofk", "wlfkfdmfgofk");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
            }
        });

       // order_request_et = binding.orderRequestEt.getText().toString();
        //Log.d("orderrrrr", order_request_et);

        order_request_et = (EditText) findViewById(R.id.order_request_et);


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
                        resDate2 = String.valueOf(year+"-"+month+"-"+day);
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
                                Log.d("daw", String.valueOf(t1Hour+t1Minute));


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
                Snackbar.make(v, "사용 가능한 쿠폰이 없습니다", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        return;
                    }
                }).show();
            }
        });

        binding.orderPossiblePoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("포인트클릭","포인트클릭");
                if (point == 0) {
                    Snackbar.make(v, "현재 소유한 포인트가 없습니다", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            return;
                        }
                    }).show();
                } else {
                    final EditText editText = new EditText(OrderActivity.this);
                    final AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this, R.style.MyAlertDialogStyle);
                    builder.setTitle("포인트")
                           .setMessage("사용할 포인트")
                           .setCancelable(false)
                           .setView(editText)
                           .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    int value = Integer.parseInt(editText.getText().toString());
                                    if (point < value) {
                                        Snackbar.make(v, "현재 소유하신 포인트보다 작게 입력해주세요", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                return;
                                            }
                                        }).show();
                                    } else {
                                        binding.orderPossiblePoint.setText(String.valueOf(value));
                                        binding.orderPayMoney.setText(sum-Integer.parseInt(binding.orderPossiblePoint.getText().toString())+"원");
                                    }
                                }
                            });
                    builder.create();
                    builder.show();
                }
            }
        });

        Log.d("orderId1", String.valueOf(orderId));
        Log.d("orderRequest1", binding.orderRequestEt.getText().toString());
        Log.d("people1", String.valueOf(count));
        Log.d("amount2", String.valueOf(sum));
        orderRequest = binding.orderRequestEt.getText().toString();

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
                    map.put("shopId", ShopDetailActivity.shopNumber);
                    //map.put("orderId", String.valueOf(orderId));
//                    Log.d("res",resDate2);
//                    Log.d("result", resDate2 +" "+t1Hour+":"+t1Minute+":"+ 0 + 0);
                    String strDate = resDate2 +" "+t1Hour+":"+t1Minute+":"+ 0 + 0;
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dateSeongbok = dateFormat.parse(strDate, new ParsePosition(0));
                    Long dateSeongbok2 = dateSeongbok.getTime();
//                    Log.d("soeng", String.valueOf(dateSeongbok2));

                    Long arriveTime = new Date(year, month, day, t1Hour, t1Minute).getTime();
//                    Log.d("arriveTime",String.valueOf(arriveTime));

                    map.put("arriveTime", String.valueOf(dateSeongbok2));
                    map.put("orderRequest", binding.orderRequestEt.getText().toString());
                    map.put("people", String.valueOf(count));
                    map.put("amount", String.valueOf(sum));

//                    Log.d("orderId1", ShopDetailActivity.shopNumber);
//                    Log.d("orderRequest1", binding.orderRequestEt.getText().toString());
//                    Log.d("people1", String.valueOf(count));

                    orderCall = Server.getInstance().getApi().updateOrder("Bearer " + jwt, map);
                    orderCall.enqueue(new Callback<Order>() {
                        @Override
                        public void onResponse(Call<Order> call, Response<Order> response) {
                            if (response.isSuccessful()) {
                                Log.d("연결성공여부", String.valueOf(response.code()));
                                orderId = Long.valueOf(response.body().getOrderId());
                                Log.d("orderididid", String.valueOf(orderId));

                                SharedPreferences pref2 = getSharedPreferences("basket", MODE_PRIVATE);
                                int list_size = pref2.getInt("list_size", 0);
                                String[] list_id =  new String[list_size];
                                int[] list_count = new int[list_size];

                                for (int i = 0; i < list_size; i++) {
                                    list_id[i] = pref2.getString("list_" + i + "_id", null);
                                    list_count[i] = pref2.getInt("list_" + i + "_count", 0);
                                }

                                Map<String, List<OrderMenu>> map2 = new HashMap();
                                List<OrderMenu> omList = new ArrayList<>();

                                for (int i = 0; i < list_size; i++) {
                                    OrderMenu om = new OrderMenu().builder()
                                            .orderId(String.valueOf(orderId))
                                            .shopId(resId)
                                            .menuId(list_id[i])
                                            .quantity(String.valueOf(list_count[i]))
                                            .build();
                                    omList.add(om);
                                }

                                map2.put("list", omList);

                                responseBodyCall = Server.getInstance().getApi().order_menus("Bearer " + jwt, map2);
                                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                    @SneakyThrows
                                    @Override
                                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                        if (response.isSuccessful()) {
                                            Log.d("orderMenu 성공", "orderMenu 성공");
                                            payment(orderId);
                                        } else {
                                            Log.d("orderMenu 실패1", "orderMenu 실패1"+response.errorBody().string());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                                        Log.d("orderMenu 실패2", "orderMenu 실패2");
                                    }
                                });
                            } else {
                                Log.d("연결실패1", "연결실패1");
                            }
                        }

                        @Override
                        public void onFailure(Call<Order> call, Throwable t) {
                            Log.d("연결실패232", "연결실패232"+t.getCause());
                        }
                    });
                }
            }
        });
    }

    private void payment(Long orderId) {
        if (!binding.orderPossiblePoint.getText().toString().equals("")) {
            if (binding.orderPossiblePoint.getText().toString().equals("0")) {
                usePoint = 0;
            } else {
                usePoint = Integer.parseInt(binding.orderPossiblePoint.getText().toString());
            }
        }

        Map<String, String> map2 = new HashMap();
        map2.put("orderId", String.valueOf(orderId));
        map2.put("amount", String.valueOf(sum));
        map2.put("usePoint", String.valueOf(usePoint));
        map2.put("pg", "inicis");
        map2.put("payMethod", "card");

        responseBodyCall = Server.getInstance().getApi().payment("Bearer " + jwt, map2);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("성공123", "성공123");

                    Intent intent = new Intent(OrderActivity.this, PaymentWebview.class);
                    intent.putExtra("shopNumber", ShopDetailActivity.shopNumber);
                    intent.putExtra("price", sum-usePoint);
                    intent.putExtra("people", count);
                    intent.putExtra("resDate", resDate);
                    intent.putExtra("resTime", resTime);
                    intent.putExtra("resShop", resShop);
                    intent.putExtra("resAddr", resAddr);
                    intent.putExtra("resId", resId);
                    intent.putExtra("resPhone", resPhone);
                    intent.putExtra("orderId", orderId);
                    intent.putExtra("orderRequest", orderRequest);
                    intent.putExtra("jwt", jwt);
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

    private void time(String time, int t2Minute, int t2Hour, TextView shop_update_closetime) {
        if (String.valueOf(t2Minute).toString().length() == 1 && String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText(String.format("%02d", t2Hour) + "시" + ":" + String.format("%02d", t2Minute) + "분");
        } else if (String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText(String.format("%02d", t2Hour) + "시" + ":" + t2Minute + "분");
        } else if (String.valueOf(t2Minute).toString().length() == 1) {
            shop_update_closetime.setText(t2Hour + "시" + ":" + String.format("%02d", t2Minute) + "분");
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