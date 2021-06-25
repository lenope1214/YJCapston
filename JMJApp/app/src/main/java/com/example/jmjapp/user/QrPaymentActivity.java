package com.example.jmjapp.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityBellBinding;
import com.example.jmjapp.databinding.ActivityQrPaymentBinding;
import com.example.jmjapp.dto.MemberDTO;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.payment.PaymentWebview;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrPaymentActivity extends AppCompatActivity {
    private ActivityQrPaymentBinding binding;
    private static final int PAYMENT_ACTIVITY = 10000;

    private AlertDialog dialog;

    static public String shopId, jwt, shopAddress, shopDetailAddress, orderId, shopName, shopAddresss;
    static public int sum = 0;

    private Call<Shop> shopCall;
    private Call<Order.OrderMenuList> getOrderMenus;
    private Call<ResponseBody> responseBodyCall;
    private Call<MemberDTO> memberDTOCall;
    private String sum1 = "";

    static public int point, usePoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQrPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.qr_payment_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Intent intent = getIntent();
        shopId = intent.getStringExtra("shopId");
        orderId = intent.getStringExtra("orderId");
        sum = PosFragment.sum;

        shopCall = Server.getInstance().getApi().shop(shopId);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.isSuccessful()) {
                    Log.d("shop 조회 성공", "shop 조회 성공");
                    binding.qrOrderShopName.setText(response.body().getName());
                    shopAddress = response.body().getAddress();
                    shopDetailAddress = response.body().getAddressDetail();
                    shopName = response.body().getName();
                    shopAddresss = shopAddress + " " + shopDetailAddress;
                } else {
                    Log.d("shop 조회 실패1", "shop 조회 실패1");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.d("shop 조회 실패2", "shop 조회 실패2");
            }
        });

        binding.qrOrderShopAddr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QrPaymentActivity.this);
                builder.setTitle("가게상세주소");
                builder.setMessage(shopAddress + "\n" + shopDetailAddress)
                        .setPositiveButton("확인", null).create();
                builder.show();
            }
        });

        binding.qrOrderShopMenu.setOnClickListener(v -> {
            getOrderMenus = Server.getInstance().getApi().orderOneMenu(orderId);
            getOrderMenus.enqueue(new Callback<Order.OrderMenuList>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<Order.OrderMenuList> call, Response<Order.OrderMenuList> response) {
                    if (response.isSuccessful()) {
                        Log.d("orderMenu 성공", "orderMenu 성공");
                        List<OrderMenu> orderMenuList = response.body().getOrderMenuList();

                        String[] list_menuName = new String[orderMenuList.size()];
                        int[] list_menuCount = new int[orderMenuList.size()];

                        int index = 0;

                        for (OrderMenu list : orderMenuList) {
                            list_menuName[index] = list.getMenuName();
                            list_menuCount[index] = Integer.parseInt(list.getQuantity());
                            index++;
                        }

                        for (int i = 0; i < orderMenuList.size(); i++) {
                            String value = list_menuName[i] + "\t\t\t\t" + list_menuCount[i] + "개\n";
                            sum1 = sum1 + value;
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("주문메뉴");
                        builder.setMessage(sum1).setPositiveButton("확인", (dialog, which) -> {
                            sum1 = "";
                        }).create();
                        builder.show();
                    } else {
                        Log.d("orderMenu 실패1", "orderMenu 실패1" + response.errorBody().string());
                    }
                }

                @Override
                public void onFailure(Call<Order.OrderMenuList> call, Throwable t) {
                    Log.d("orderMenu 실패2", "orderMenu 실패2");
                }
            });
        });

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
                    Log.d("유저조회 실패1 ", "유저조회 실패1" + response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Log.d("유저조회 실패2 ", "유저조회 실패2" + t.getCause());
            }
        });

        binding.qrOrderPayMoney.setText(sum + "원");

        binding.qrOrderPossiblePoint.setOnClickListener(v -> {
            Log.d("포인트클릭", "포인트클릭");
            if (point == 0) {
                Snackbar.make(v, "현재 소유한 포인트가 없습니다", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        return;
                    }
                }).show();
            } else {
                final EditText editText = new EditText(QrPaymentActivity.this);
                final AlertDialog.Builder builder = new AlertDialog.Builder(QrPaymentActivity.this, R.style.MyAlertDialogStyle);
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
                                    binding.qrOrderPossiblePoint.setText(String.valueOf(value));
                                    binding.qrOrderPayMoney.setText(sum - Integer.parseInt(binding.qrOrderPossiblePoint.getText().toString()) + "원");
                                }
                            }
                        });
                builder.create();
                builder.show();
            }
        });

        binding.qrOrderPayBtn.setOnClickListener(v -> payment());
    }

    private void payment() {
        if (!binding.qrOrderPossiblePoint.getText().toString().equals("")) {
            if (binding.qrOrderPossiblePoint.getText().toString().equals("0")) {
                usePoint = 0;
            } else {
                usePoint = Integer.parseInt(binding.qrOrderPossiblePoint.getText().toString());
            }
        }

        Intent intent = new Intent(QrPaymentActivity.this, PaymentWebview.class);
        intent.putExtra("qr", "qr");
        intent.putExtra("price", sum - usePoint);
        intent.putExtra("resShop", shopName);
        intent.putExtra("resAddr", shopAddresss);
        startActivityForResult(intent, PAYMENT_ACTIVITY);
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
            alBuilder.setMessage("결제화면을 나가시겠습니까?");

            alBuilder.setPositiveButton("예", (dialog, which) -> finish());
            alBuilder.setNegativeButton("아니오", (dialog, which) -> {
                return;
            });
            alBuilder.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("결제화면을 나가시겠습니까?");

        alBuilder.setPositiveButton("예", (dialog, which) -> finish());
        alBuilder.setNegativeButton("아니오", (dialog, which) -> {
            return;
        });
        alBuilder.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getOrderMenus != null)
            getOrderMenus.cancel();
        if (shopCall != null)
            shopCall.cancel();
        if (responseBodyCall != null)
            responseBodyCall.cancel();
    }
}