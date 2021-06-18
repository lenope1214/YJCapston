package com.example.jmjapp.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Mark;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.google.android.gms.common.api.Api;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.login.LoginException;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import okhttp3.WebSocket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompHeader;
import ua.naiksoftware.stomp.client.StompClient;
import ua.naiksoftware.stomp.client.StompMessage;

public class ProfileUpdateActivity extends AppCompatActivity {

    TextView logout_btn, user_profile_id, delete_id;
    EditText update_password;
    Button update_pw_btn;
    private AlertDialog dialog;
    private String jwt;
    private Call<ResponseBody> responseBodyCall;
    //private Call<OrderMenu> orderMenuCall;
    private Call<List<OrderMenu>> orderMenuCall;
    private Call<Order.OrderMenuList> getOrderMenus;
    private Call<Order> orderCall;

    public static final String TAG = "stomp좀 되라 시발";
    private StompClient mStompClient;
    private Button btn2;
    private boolean isUnexpectedClosed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profileupdate);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        String id = ((JMJApplication) getApplication()).getId();

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", null);
        Log.d("jwt", "Bearer " + jwt);
        Log.d("jwt123", jwt);



        user_profile_id = findViewById(R.id.user_profile_id);
        user_profile_id.setText(id);

        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                builder.setTitle("알림");
                builder.setMessage("로그아웃하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ProfileUpdateActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();

                        // 값버리기
                        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();
                        editor.remove("token");
                        editor.remove("user_id");
                        editor.remove("role");
                        editor.apply();

                        // 앱 변수버리기
                        ((JMJApplication) getApplication()).setId(null);
                        ((JMJApplication) getApplication()).setJwt(null);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("ㄱㄷㄴ", "Awda");
                    }
                });
                builder.show();
            }
        });
        delete_id = findViewById(R.id.delete_id);
        delete_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileUpdateActivity.this, MembershipWithdrawal.class);
                startActivity(intent);
            }
        });

        update_password = findViewById(R.id.update_password);
        update_pw_btn = findViewById(R.id.update_pw_btn);


        update_pw_btn.setOnClickListener((v) -> {
            String newpassword = update_password.getText().toString();
            if (newpassword.length() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                dialog = builder.setMessage("비밀번호를 확인해주세요.").setPositiveButton("확인", null).create();
                dialog.show();
            } else {
                Map<String, String> map = new HashMap();
                map.put("password", newpassword);

                responseBodyCall = Server.getInstance().getApi().updateOne("Bearer " + jwt, map);
                responseBodyCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        System.out.println(response.code() + "7777777777777777777");
                        if (response.code() == 200) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                            dialog = builder.setMessage("비밀번호가 변경되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(ProfileUpdateActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).create();
                            builder.setCancelable(false);
                            dialog.show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ProfileUpdateActivity.this);
                            dialog = builder.setMessage("비밀번호 변경에 실패했습니다.").setPositiveButton("확인", null).create();
                            dialog.show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("연결실패", "왜???????????");

                    }
                });
            }
        });
//        new LongOperation().execute("");
        initStomp();

        Button btn = findViewById(R.id.testbtn);
        btn.setOnClickListener(v -> {
            onClickBtn();
        });

        btn2 = findViewById(R.id.testbtn2);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                orderCall = Server.getInstance().getApi().orderOne("Bearer " + jwt, "1622685111001");
////                orderCall.enqueue(new Callback<Order>() {
////                    @SneakyThrows
////                    @Override
////                    public void onResponse(Call<Order> call, Response<Order> response) {
////                        if (response.isSuccessful()) {
////                            Log.d("s","s");
////                        } else {
////                            Log.d("no","no"+response.errorBody().string());
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<Order> call, Throwable t) {
////                        Log.d("s2","s2");
////                    }
////                });
//
////                getOrderMenus = Server.getInstance().getApi().orderOneMenu("Bearer " + jwt, "1622591304095");
////                getOrderMenus.enqueue(new Callback<Order.OrderMenuList>() {
////                    @SneakyThrows
////                    @Override
////                    public void onResponse(Call<Order.OrderMenuList> call, Response<Order.OrderMenuList> response) {
////                        if (response.isSuccessful()) {
////                            Log.d("order_orderMenu 성공", "order_orderMenu 성공");
////                            List<OrderMenu> orderMenuList = response.body().getOrderMenuList();
////
////                            for (OrderMenu list : orderMenuList) {
////                                Log.d("list_quantity", list.getQuantity());
////                            }
////                        } else {
////                            Log.d("order_orderMenu 실패1", "order_orderMenu 실패1"+response.errorBody().string());
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<Order.OrderMenuList> call, Throwable t) {
////                        Log.d("order_orderMenu 실패2", "order_orderMenu 실패2");
////                    }
////                });
//
////                orderMenuCall = Server.getInstance().getApi().order_orderMenu("Bearer " + jwt, "1621620310128");
////                orderMenuCall.enqueue(new Callback<List<OrderMenu>>() {
////                    @SneakyThrows
////                    @Override
////                    public void onResponse(Call<List<OrderMenu>> call, Response<List<OrderMenu>> response) {
////                        if (response.isSuccessful()) {
////                            Log.d("oprderId", "1621620310128");
////                            Log.d("order_orderMenu 성공", "order_orderMenu 성공");
////                            List<OrderMenu> orderMenuList = response.body();
////                            Log.d("response.body", response.body().toString());
////                            for (OrderMenu list : orderMenuList) {
////
////                            }
////                            String[] list_id = new String[orderMenuList.size()];
////                            int[] list_count = new int[orderMenuList.size()];
////                            Log.d("size", String.valueOf(orderMenuList.size()));
////                            for(OrderMenu list : orderMenuList) {
////                                for (int i = 0; i < orderMenuList.size(); i++) {
////                                    list_id[i] = list.getMenuId();
////                                    Log.d("list_id", list_id[i]);
////                                }
////                            }
////                        } else {
////                            Log.d("order_orderMenu 실패1", "order_orderMenu 실패1"+response.errorBody().string());
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<List<OrderMenu>> call, Throwable t) {
////                        Log.d("order_orderMenu 실패2", "order_orderMenu 실패2");
////                    }
////                });
////                Log.d("btn실행", "btn실행");
////
////                Map<String, List<OrderMenu>> map = new HashMap();
////                List<OrderMenu> omList = new ArrayList<>();
////
////                OrderMenu om1 = new OrderMenu().builder()
////                        .orderId("1622108941558")
////                        .shopId("0532552288")
////                        .menuId("51m20210408072439")
////                        .quantity("3")
////                        .build();
////                omList.add(om1);
////
////                map.put("list", omList);
//
////                responseBodyCall = Server.getInstance().getApi().order_menus("Bearer " + jwt, map);
////                responseBodyCall.enqueue(new Callback<ResponseBody>() {
////                    @SneakyThrows
////                    @Override
////                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                        if (response.isSuccessful()) {
////                            Log.d("성공","성공");
////                        } else {
////                            Log.d("실패","실패"+response.errorBody().string());
////                        }
////                    }
////
////                    @Override
////                    public void onFailure(Call<ResponseBody> call, Throwable t) {
////                        Log.d("실패2","실패2");
////                    }
////                });
//            }
//        });
    } // on create 끝 -------------------------

    private StompClient stompClient;
    private List<StompHeader> headerList;
    public void onClickBtn(){
        stompClient.send("/pub/jmj","데이터 간다").subscribe();
    }

    public void initStomp(){
        stompClient= Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.51:8088/ws-stomp/websocket");

        stompClient.lifecycle().subscribe(lifecycleEvent -> {
            switch (lifecycleEvent.getType()) {
                case OPENED:
                    Log.d(TAG, "Stomp connection opened");
                    break;
                case ERROR:
                    Log.e(TAG, "Error", lifecycleEvent.getException());
                    if(lifecycleEvent.getException().getMessage().contains("EOF")){
                        isUnexpectedClosed=true;
                    }
                    break;
                case CLOSED:
                    Log.d(TAG, "Stomp connection closed");
                    if(isUnexpectedClosed){
                        /**
                         * EOF Error
                         */
                        initStomp();
                        isUnexpectedClosed=false;
                    }
                    break;
            }
        });

        // add Header
        headerList=new ArrayList<>();
//        headerList.add(new StompHeader("Authorization", G.accessToken));
        stompClient.connect(headerList);
    }

//    @SneakyThrows
//    private void initStomp() {
//        //mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.51:8088/ws-stomp/websocket");
//        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://3.34.55.186:8088/ws-stomp/websocket");
//
//        mStompClient.lifecycle().subscribe(lifecycleEvent -> {
//            switch (lifecycleEvent.getType()) {
//                case OPENED:
//                    Log.d(TAG, "Stomp connection opened???????????????1");
//                    break;
//                case ERROR:
//                    Log.e(TAG, "Error", lifecycleEvent.getException());
//                    if (lifecycleEvent.getException().getMessage().contains("EOF")) {
//                        isUnexpectedClosed = true;
//                    }
//                    break;
//                case CLOSED:
//                    Log.d(TAG, "Stomp connection closed");
//                    if (isUnexpectedClosed) {
//                        /**
//                         * EOF Error
//                         */
//                        initStomp();
//                        isUnexpectedClosed = false;
//                    }
//                    break;
//            }
//        });
////        headerList=new ArrayList<>();
////        headerList.add(new StompHeader("Authorization", jwt));
////        mStompClient.connect(headerList);
//        mStompClient.connect();
//
//        mStompClient.topic("/sub/4850101906/u/2").subscribe(topicMessage -> {
//            Log.d(TAG, topicMessage.getPayload());
//        });
//
//        JSONObject obj = new JSONObject();
//        obj.put("msg", "hello");
//
//        String jsonObject = obj.toString();
//
//
//        mStompClient.send("/pub/chat", jsonObject).subscribe();
//        mStompClient.send("/pub/chat", jsonObject).subscribe();
//        mStompClient.send("/chat", jsonObject).subscribe();
//        mStompClient.send("/chat", jsonObject);
//
//        mStompClient.send("pub", jsonObject).subscribe();
//        mStompClient.send("/pub", jsonObject).subscribe();
//        mStompClient.send("/pub/chat", jsonObject).subscribe();
//        mStompClient.send("/pub/chat");
//        mStompClient.send("/pub/chat").subscribe();
//        mStompClient.send("/chat", "asdtasdtdfasdf").subscribe();
//        mStompClient.send("/chat", "asdtasdtdfasdf");
//
//        mStompClient.send("/sub/4850101906/u/2", "My first STOMP message!1").subscribe();
//        mStompClient.send("/pub", "My first STOMP message!2").subscribe();
//        mStompClient.send("/pub/chat", "My first STOMP message!3").subscribe();
//
//
//
//        Log.d("qwert", "qwert");
//    }
//
//
//    private class LongOperation extends AsyncTask<String, Void, String> {
//        private StompClient mStompClient;
//        String TAG = "STOMP ?연결?";
//        String shopId = "7389801057";
//
//        public LongOperation(){
//            mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://192.168.1.51:8088/ws-stomp/websocket");
//            mStompClient.setHeartbeat(4000);
//            List<StompHeader> stompHeaders = new ArrayList<>();
//            stompHeaders.add(new StompHeader(StompHeader.DESTINATION, "/pub/jmj"));
//            mStompClient.connect(stompHeaders);
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            //mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://3.34.55.186:8088/ws-stomp/websocket");
//
//            mStompClient.topic("/sub/jmj");
//
//            mStompClient.topic("/sub/jmj").subscribe(topicMessage -> {
////                Log.d(TAG, topicMessage.getPayload());
//                System.out.println("JMJ 구독 완료 !");
//            });
//            btn2.setOnClickListener(v -> {
//                Log.d(mStompClient., "doInBackground: ");
//                System.out.println("버튼 클릭!" + (mStompClient.isConnecting()));
//                List<StompHeader> stompHeaders = new ArrayList<>();
//                stompHeaders.add(new StompHeader(StompHeader.DESTINATION, "/pub/jmj"));
//                stompHeaders.add(new StompHeader("simpMessageType", "MESSAGE"));
//                StompMessage stm = new StompMessage("MESSAGE", stompHeaders, "데이터 20세기야" );
//                mStompClient.connect(stompHeaders);
////                mStompClient.send("/pub/chat", "ㄷㅊㅁㄴㅇㄻㄴㅇㄹ");
////                mStompClient.send("/pub/chat", "ㅁㄴㅇㄻㄴㅇㄹ ㅁㄴ아ㅓ로민다 ㄷㅎ").subscribe();
////                mStompClient.send("/pub/jmj", "ㄷㅊㅁㄴㅇㄻㄴㅇㄹ");
////                mStompClient.send("/pub/jmj", "ㅁㄴㅇㄻㄴㅇㄹ ㅁㄴ아ㅓ로민다 ㄷㅎ").subscribe();
//            });
//
//            mStompClient.lifecycle().subscribe(lifecycleEvent -> {
//                switch (lifecycleEvent.getType()) {
//
//                    case OPENED:
//                        Log.d(TAG, "Stomp connection opened");
//                        System.out.println("나우 연결 ? " + (mStompClient.isConnecting()));
////                        mStompClient.send("/pub/chat", "4");
////                        System.out.println("4 전송 완료?");
////
////                        mStompClient.send("/pub/chat", "3").subscribe();
////                        System.out.println("3 전송 완료?");
////
////                        mStompClient.send("/pub/jmj", "2");
////                        System.out.println("2 전송 완료?");
////
////                        mStompClient.send("/pub/jmj", "1").subscribe();
////                        System.out.println("1 전송 완료?");
//                        break;
//
//                    case ERROR:
//                        Log.e(TAG, "Error", lifecycleEvent.getException());
//                        break;
//
//                    case CLOSED:
//                        Log.d(TAG, "Stomp connection closed");
//                        break;
//                }
//            });
//            return "Executed";
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//
//        }
//    }


//    private void connectStomp() {
//        StompClient client = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws:/3.34.55.186:8088/ws-stomp/websocket");
//        //client.setHeartbeat(10000);
//        client.connect();
//        Log.d("connect after","after connection");///
//
//        client.topic("/topic/chatting").subscribe(message -> {
//            Log.i("message","->");
//            Log.i(TAG, "Received message: " + message.getPayload());
//        }); // 받는거
//
////        client.send("/sub/7389801057/o/roomId", "world").subscribe(
////                () -> Log.d(TAG, "Sent data!"),
////                error -> Log.e(TAG, "Encountered error while sending data!", error)
////        ); // 보내는거
//        client.send("/app/file", "world").subscribe(
//                () -> Log.d(TAG, "Sent data!"),
//                error -> Log.e(TAG, "Encountered error while sending data!", error)
//        ); // 보내는거
//
//        //client.disconnect();
//
//        client.lifecycle().subscribe(lifecycleEvent -> {
//            switch (lifecycleEvent.getType()) {
//                case OPENED:
//                    Log.d(TAG, "Stomp connection opened");
//                    break;
//                case CLOSED:
//                    Log.d(TAG, "Stomp connection closed");
//                    break;
//                case ERROR:
//                    Log.e(TAG, "Stomp connection error", lifecycleEvent.getException());
//                    break;
//            }
//        });
//        return "Executed";
//    }


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
        if (responseBodyCall != null)
            responseBodyCall.cancel();
    }

}