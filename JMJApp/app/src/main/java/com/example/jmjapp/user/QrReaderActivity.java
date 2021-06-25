package com.example.jmjapp.user;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.network.Server;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QrReaderActivity extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    static public String orderCheck = "0";
    static public String tablenum, shopnum1, shopnum2, orderId, orderNumber;

    private String jwt;

    private Call<Order> orderCall;
    private Call<ResponseBody> responseBodyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_reader);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        scanCode();
    }

    private void scanCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(CaptureAct.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning Code");
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //QRcode 결과가 있으면
            if (result.getContents() != null) {
                String str = result.getContents();
                shopnum1 = str.substring(str.lastIndexOf("shopcontent/") + 12);
                shopnum2 = shopnum1.substring(0, 10);

                if (shopnum1.length() == 12) {
                    tablenum = shopnum1.substring(11, 12);
                } else if (shopnum1.length() == 13) {
                    tablenum = shopnum1.substring(11, 13);
                }

                Map<String, String> map = new HashMap();
                map.put("shopId", shopnum2);

                orderCall = Server.getInstance().getApi().order("Bearer " + jwt, map);
                orderCall.enqueue(new Callback<Order>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        if (response.isSuccessful()) {
                            Log.d("orderTable 성공", "orderTable 성공");
                            orderId = response.body().getOrderId();
                            Log.d("orderId", orderId);
                            updateTable(orderId);
                        } else {
                            Log.d("orderTable 실패1", "orderTable 실패1" + response.errorBody().string());
                        }
                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {
                        Log.d("orderTable 실패2", "orderTable 실패2" + t.getCause());
                    }
                });

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("어서오세요!");
                builder.setTitle("알림");
                builder.setPositiveButton("확인", (dialog, which) -> {
                    try {
                        System.out.println(result.getContents());
                        System.out.println(shopnum1);
                        System.out.println(shopnum2);
                        System.out.println(tablenum);

                        finish();

                        orderCheck = "1";

                        Intent intent = new Intent(QrReaderActivity.this, MainActivity.class);
                        intent.putExtra("shopNumber", shopnum2);
                        intent.putExtra("tableNumber", tablenum);
                        intent.putExtra("orderCheck", orderCheck);
                        intent.putExtra("QR", "order");
                        intent.putExtra("orderId", orderId);

                        startActivity(intent);
                    } catch (Exception e) {
                        e.getStackTrace();
                    }

                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            //QRcode 결과가 없으면
            else {
                Toast.makeText(this, "No Results", Toast.LENGTH_LONG).show();
                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateTable(String orderId) {
        Map<String, String> map = new HashMap();
        map.put("shopId", shopnum2);
        map.put("no", tablenum);
        map.put("orderId", orderId);
        map.put("using", "Y");
        Log.d("orderIdtoUpdateTable", orderId);

        responseBodyCall = Server.getInstance().getApi().updateTable("Bearer " + jwt, map);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("updateTable 성공", "updateTable 성공");
                } else {
                    Log.d("updateTable 실패1", "updateTable 실패1" + response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("updateTable 실패2", "updateTable 실패2" + t.getCause());
            }
        });
    }

}
