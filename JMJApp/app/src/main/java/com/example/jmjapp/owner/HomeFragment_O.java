package com.example.jmjapp.owner;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.owner.push.BellActivity_O;
import com.example.jmjapp.user.MainActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment_O extends Fragment {
    ToggleButton toggle_Button;
    TextView text_myshop_name, profit_detail_tv, profit_today;
    Button bell_button;
    ViewFlipper viewFlipper;

    private String jwt, orderId;
    private int sum;

    static public String shopNumber;
    MediaPlayer mediaPlayer;

    private Call<List<Order>> listOrderCall;

    Thread thread;
    boolean isThread = false;

    public HomeFragment_O() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment_o, container, false);
        Bundle bundle = getArguments();
        shopNumber = bundle.getString("shopNumber");
        String shopName = bundle.getString("shopName");

        text_myshop_name = rootView.findViewById(R.id.text_myshop_name);
        text_myshop_name.setText("현재 매장 : " + shopName);

        profit_today = rootView.findViewById(R.id.profit_today);

//        isThread = true;
//        thread = new Thread() {
//            public void run() {
//                while (isThread) {
//                    try {
//                        sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    handler.sendEmptyMessage(0);
//                }
//            }
//        };
//        thread.start();

        int images[] = {
            R.drawable.adv1,
            R.drawable.adv2,
            R.drawable.adv3
        };

        viewFlipper = rootView.findViewById(R.id.owner_img_slide);

        for (int image : images) {
            slideImage(image);
        }

        toggle_Button = rootView.findViewById(R.id.toggle_button);
        toggle_Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String toastMessage;
                if(isChecked) {
                    toastMessage = "매장 On";
                } else {
                    toastMessage = "매장 Off";
                }
                Toast.makeText(getActivity().getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });

        bell_button = rootView.findViewById(R.id.bell_button);
        bell_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BellActivity_O.class);
                startActivity(intent);
            }
        });

        profit_detail_tv = rootView.findViewById(R.id.profit_detail_tv);
        profit_detail_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfitDetailActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences pref = getActivity().getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        jwt = pref.getString("token", "");

        todayProfit();

        return rootView;
    }

    private void slideImage(int image) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(getActivity(),android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(),android.R.anim.slide_out_right);
    }

    private void todayProfit() {
        listOrderCall = Server.getInstance().getApi().orderList("Bearer " + jwt, shopNumber);
        listOrderCall.enqueue(new Callback<List<Order>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    Log.d("orderList 성공", "orderList 성공");
                    List<Order> orderList = response.body();

                    int list_comp_amount[] = new int[orderList.size()];
                    String list_pay_time[] = new String[orderList.size()];
                    String list_pay_time_real[] = new String[orderList.size()];
                    String list_status[] = new String[orderList.size()];

                    int index = 0;

                    for (Order list : orderList) {
                        if (list.getPayTime() != null) {
                            list_comp_amount[index] = list.getCompleAmount();
                            list_pay_time[index] = list.getPayTime();
                            list_status[index] = list.getStatus();
                            index++;
                        }
                    }

                    boolean isEmpty = true;
                    for (int i = 0; i < list_pay_time.length; i++) {
                        if (list_pay_time[i] != null) {
                            isEmpty = false;
                            break;
                        }
                    }

                    if (isEmpty == false) {
                        for (int i = 0; i < list_pay_time.length; i++) {
                            if (list_pay_time[i] != null) {
                                list_pay_time_real[i] = list_pay_time[i].substring(0, 4) + "-" + list_pay_time[i].substring(5, 7) + "-" + list_pay_time[i].substring(8, 10);
                                System.out.println(list_pay_time_real[i]);
                            }
                        }

                        // 현재날짜
                        long now = System.currentTimeMillis();
                        Date mDate = new Date(now);
                        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                        String getTime = simpleDate.format(mDate);
                        System.out.println("지금시간 : " + getTime);

                        sum = 0;
                        for (int i = 0; i < orderList.size(); i++) {
                            if (list_pay_time_real[i] != null) {
                                if (list_pay_time_real[i].equals(getTime)) {
                                    if (!(list_status[i].equals("rf"))) {
                                        sum = sum + list_comp_amount[i];
                                        System.out.println("sum : " + sum);
                                    }
                                } else {
                                    System.out.println("qwe");
                                }
                            }
                        }
                        System.out.println("배열이 있습니다.");
                        profit_today.setText(String.valueOf(sum) + "원");
                    } else {
                        System.out.println("배열이 비었습니다.");
                        profit_today.setText("0원");
                    }
                } else {
                    Log.d("orderList 실패1", "orderList 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("orderList 실패2", "orderList 실패2"+t.getCause());
            }
        });
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            listOrderCall = Server.getInstance().getApi().orderList("Bearer " + jwt, shopNumber);
            listOrderCall.enqueue(new retrofit2.Callback<List<Order>>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                    if (response.isSuccessful()) {
                        Log.d("orderList 성공", "orderList 성공");
                        Date date1 = null;
                        List<Order> orderList = response.body();

                        String list_orderId[] = new String[orderList.size()];
                        String list_payTime[] = new String[orderList.size()];

                        int index = 0;

                        for (Order list : orderList) {
                            if (list.getPayTime() != null) {
                                list_orderId[index] = list.getOrderId();

                                String sDate = list.getPayTime();

                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                                date1 = sdf.parse(sDate.substring(0,4) + "-" + sDate.substring(5,7) + "-" + sDate.substring(8,10)
                                                        + " " + sDate.substring(10,15));

                                list_payTime[index] = sdf.format(date1);

                                System.out.println("orderId : " + list_orderId[index]);
                                System.out.println("payTime : " + list_payTime[index]);

                                index++;
                            }
                        }

                        for (int i = 0; i < orderList.size(); i++) {
                            // 현재날짜
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                            Date time = new Date();
                            String time1 = simpleDateFormat.format(time);

                            Calendar cal = Calendar.getInstance();

                            cal.setTime(time);

                            String today = null;

                            cal.add(Calendar.MINUTE, -1);
                            today = simpleDateFormat.format(cal.getTime());


                            if ((simpleDateFormat.parse(list_payTime[i]).equals(time) || simpleDateFormat.parse(list_payTime[i]).before(time))
                                    && simpleDateFormat.parse(list_payTime[i]).after(simpleDateFormat.parse(today))){
                                System.out.println("페이타임이 현재시간보다 과거입니다.");
                                System.out.println("현재시간 : " + time1);
                                System.out.println("1분 전 : " + today);
                                System.out.println("페이타임 : " + list_payTime[i]);
                                sendNotification(list_orderId[i]);
                                isThread = false;
                            } else {
                                System.out.println("오류");
                                System.out.println("현재시간 : " + time1);
                                System.out.println("1분 전 : " + today);
                                System.out.println("페이타임 : " + list_payTime[i]);
                            }
                        }

                    } else {
                        Log.d("orderList 실패1", "orderList 실패1");
                    }
                }

                @Override
                public void onFailure(Call<List<Order>> call, Throwable t) {
                    Log.d("orderList 실패2", "orderList 실패2");
                }
            });
        }
    };

    private void sendNotification(String orderId) throws UnsupportedEncodingException {
        Intent intent = new Intent(getActivity(), BellActivity_O.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("resId", MainActivity_O.shopNumber);
        intent.putExtra("from", "nothing");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "default");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle(URLDecoder.decode("주문의 민족", "UTF-8"));
        builder.setContentText(URLDecoder.decode("예약주문 요청이 왔습니다!", "UTF-8"));
        builder.setSmallIcon(R.drawable.loginbackgroundblack);
        builder.setAutoCancel(true);
        builder.setContentIntent(pendingIntent);

        mediaPlayer = MediaPlayer.create(getActivity(),R.raw.alarm);
        mediaPlayer.start();

        // 알림 표시
        NotificationManager notificationManager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }

        // id값은
        // 정의해야하는 각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());
    }

    private void makeShopFragment() {
    }
}
