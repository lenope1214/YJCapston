package com.example.jmjapp.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.jmjapp.databinding.ActivityPaymentResultBinding;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.user.MainActivity;
import com.example.jmjapp.user.OrderActivity;
import com.example.jmjapp.user.QrPaymentActivity;
import com.example.jmjapp.user.QrReaderActivity;
import com.example.jmjapp.user.ShopDetailActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import lombok.val;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentResultActivity extends AppCompatActivity {
    ActivityPaymentResultBinding binding;

    static RequestQueue requestQueue;
    private String reqId, jwt, device_token, resTime,
            resDate, orderRquest, resShop, ownerId, resId, qr;
    private Long orderId;
    private int sum, count, usePoint;

    private Call<ResponseBody> responseBodyCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token",   "tokenIsNull");

        qr = QrReaderActivity.orderId;
        ownerId = ShopDetailActivity.ownerId;

        resTime = OrderActivity.resTime;
        resDate = OrderActivity.resDate;
        sum = OrderActivity.sum;
        count = OrderActivity.count;
        orderRquest = OrderActivity.orderRequest;
        resShop = OrderActivity.resShop;
        orderId = OrderActivity.orderId;
        resId = OrderActivity.resId;
        usePoint = OrderActivity.usePoint;

        if (qr == null) {
            binding.reservationQr.setVisibility(View.GONE);
            binding.reservationComp.setVisibility(View.VISIBLE);

            Map<String, String> map = new HashMap();
//        map.put("resTime", resTime);
//        map.put("resDate", resDate);
//        map.put("sum", String.valueOf(sum));
//        map.put("count", String.valueOf(count));
//        map.put("orderRequest", orderRquest);
//        map.put("resShop", resShop);
            map.put("orderId", String.valueOf(orderId));
            map.put("resId", resId);
            map.put("status", "toOwner");
            map.put("jwt", jwt);

            binding.reservationComp.setOnClickListener(v -> {
                Map<String, String> map2 = new HashMap<>();
                map2.put("orderId", String.valueOf(orderId));
                map2.put("amount", String.valueOf(sum));
                map2.put("usePoint", String.valueOf(usePoint));
                map2.put("pg", "inicis");
                map2.put("payMethod", "card");

                System.out.println(orderId + "@" + sum + "@" + usePoint);

                responseBodyCall = Server.getInstance().getApi().payment("Bearer " + jwt, map2);
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d("결제 성공", "결제 성공");

                            Map<String, String> map3 = new HashMap<>();
                            map3.put("orderId", String.valueOf(orderId));

                            responseBodyCall = Server.getInstance().getApi().rdToPd("Bearer " + jwt, map3);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("rdToPd 성공", "rdToPd 성공");

                                        SharedPreferences pref1 = getSharedPreferences("basket", MODE_PRIVATE);
                                        SharedPreferences.Editor editor1 = pref1.edit();
                                        editor1.clear();
                                        editor1.apply();

                                        SharedPreferences pref2 = getSharedPreferences("basket_shop", MODE_PRIVATE);
                                        SharedPreferences.Editor editor2 = pref2.edit();
                                        editor2.clear();
                                        editor2.apply();

                                        send(map);
                                        Intent intent = new Intent(PaymentResultActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Log.d("rdToPd 실패1", "rdToPd 실패1");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("rdToPd 실패2", "rdToPd 실패2");
                                }
                            });
                        } else {
                            Log.d("결제 실패1", "결제 실패1"+response.errorBody().string());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("결제 실패2", "결제 실패2"+t.getCause());
                    }
                });
            });

            responseBodyCall = Server.getInstance().getApi().deviceToken(ownerId);
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        device_token = response.body().string();
                        Log.d("deviceToken!!!!!!!!!!", device_token);
                    } else {
                        Log.d("실패1111", "실패1111");
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Log.d("실패2222", "실패2222");
                }
            });

            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(getApplicationContext());
            }

        } else {
            binding.reservationQr.setVisibility(View.VISIBLE);
            binding.reservationComp.setVisibility(View.GONE);

            binding.reservationQr.setOnClickListener(v -> {
                Map<String, String> map4 = new HashMap();
                map4.put("orderId", QrPaymentActivity.orderId);
                map4.put("amount", String.valueOf(QrPaymentActivity.sum));
                map4.put("usePoint", String.valueOf(QrPaymentActivity.usePoint));
                map4.put("pg", "inicis");
                map4.put("payMethod", "card");

                responseBodyCall = Server.getInstance().getApi().payment("Bearer " + jwt, map4);
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Log.d("결제 성공", "결제 성공");

                            Map<String, String> map5 = new HashMap<>();
                            map5.put("orderId", QrPaymentActivity.orderId);

                            responseBodyCall = Server.getInstance().getApi().rdToPd("Bearer " + jwt, map5);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("rdToPd 성공", "rdToPd 성공");

                                        SharedPreferences pref3 = getSharedPreferences("basket_shop", MODE_PRIVATE);
                                        SharedPreferences.Editor editor3 = pref3.edit();
                                        editor3.clear();
                                        editor3.apply();

                                        finishAffinity();
                                        Intent intent = new Intent(PaymentResultActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        System.exit(0);

                                    } else {
                                        Log.d("rdToPd 실패1", "rdToPd 실패1");
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("rdToPd 실패2", "rdToPd 실패2");
                                }
                            });
                        } else {
                            Log.d("결제 실패1", "결제 실패1");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("결제 실패2", "결제 실패2");
                    }
                });
            });
        }
    }

    private void send(Map<String, String> map) {
        JSONObject requestData = new JSONObject();

        try {
            requestData.put("priority", "high");

            JSONObject dataObj = new JSONObject();

            dataObj.put("resTime", map.get("resTime"));
            dataObj.put("resDate", map.get("resDate"));
            dataObj.put("sum", map.get("sum"));
            dataObj.put("count", map.get("count"));
            dataObj.put("resShop", map.get("resShop"));
            dataObj.put("orderId", map.get("orderId"));
            dataObj.put("resId", map.get("resId"));
            dataObj.put("status", map.get("status"));
            dataObj.put("jwt", map.get("jwt"));
            requestData.put("data", dataObj);
            JSONArray idArray = new JSONArray();

            Log.d("qweasd","Qweasd");

            idArray.put(0, "cc2AJr2H-MI:APA91bFjpQu8I_QdOr1wepmi2xCh-UqdGA4iJKkUEZZtwdDmXob1KYL7-5zGpuJ-pnLhn72usaEINLr3NZzGuGkiifzsnz8L82vLMGHHdN_vHmFMJ_MOmnbVOhcW8EK5BD5Cbg19ZM-W");
            requestData.put("registration_ids", idArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        sendData(requestData, new SendResponseListener() {

            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted() 호출됨.");
            }

            @Override
            public void onRequestStarted() {
                println("onRequestStarted() 호출됨.");
            }

            @Override
            public void onRequestWithError(VolleyError error) {
                println("onRequestWithError() 호출됨.");
            }
        });
    }

    public interface SendResponseListener {
        public void onRequestStarted();
        public void onRequestCompleted();
        public void onRequestWithError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(

                Request.Method.POST, "https://fcm.googleapis.com/fcm/send", requestData, new com.android.volley.Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                listener.onRequestCompleted();
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestWithError(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", "key=AAAASNKJ6g4:APA91bEbZnK-PXSqFhJ1CVQ4DIa1NZ1NUHCtRyJ9fIWJPJBebuQosfVDa75uI0Nl7qQKZ8RFFb-s2H9bGVIBYw3od5zg6fKxPUgpms2Hk1O_IMxNAGU-8P4ir-Og-Z1lGIK1-ZYptD_t");
                return headers;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        requestQueue.add(request);
    }

    public void println(String data) {
        binding.textView123.append("!@#$%^&*()@#$%^&*"+data + "\n");
    }
}
