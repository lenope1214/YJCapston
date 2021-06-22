package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityProfitDetailBinding;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.network.Server;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfitDetailActivity extends AppCompatActivity {
    private ActivityProfitDetailBinding binding;

    private Call<List<Order>> listOrderCall;

    private String jwt;

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfitDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.profit_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        barChart = binding.barChart;

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        listOrderCall = Server.getInstance().getApi().orderList("Bearer " + jwt, MainActivity_O.shopNumber);
        listOrderCall.enqueue(new Callback<List<Order>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    Log.d("orderList 성공", "orderList 성공");
                    List<Order> orderList = response.body();

                    int[] list_comp_amount = new int[orderList.size()];
                    String[] list_pay_time = new String[orderList.size()];
                    String[] list_status = new String[orderList.size()];

                    int index = 0;

                    for (Order list : orderList) {
                        if (list.getPayTime() != null) {
                            list_pay_time[index] = list.getPayTime().substring(8, 10);
                            list_comp_amount[index] = list.getCompleAmount();
                            list_status[index] = list.getStatus();
                            System.out.println("status : " + list_status[index]);
                            System.out.println(list_pay_time[index]);
                        }
                        System.out.println(String.valueOf(list_pay_time[index]));
                        index++;
                    }

                    boolean isEmpty = true;
                    for (int i = 0; i < list_pay_time.length; i++) {
                        if (list_pay_time[i] != null) {
                            isEmpty = false;
                            break;
                        }
                    }
                    
                    if (isEmpty == false) {
                        binding.barChart.setVisibility(View.VISIBLE);
                        binding.noProfit.setVisibility(View.GONE);

                        // 현재날짜
                        long now = System.currentTimeMillis();
                        Date mDate = new Date(now);
                        SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
                        String getTime = simpleDate.format(mDate);
                        Log.d("now", getTime);

//                    // 한달전 날짜
//                    Calendar mon = Calendar.getInstance();
//                    mon.add(Calendar.MONTH, -1);
//                    String beforeMonth = new java.text.SimpleDateFormat("MM").format(mon.getTime());
//                    System.out.println(beforeMonth);

                        // 현재날짜의 월별 일수
                        String nowMon = getTime.substring(5, 7);
                        System.out.println(nowMon);

                        int mi = 0;

                        if (nowMon.equals("01") || nowMon.equals("03") || nowMon.equals("05") || nowMon.equals("07")
                                || nowMon.equals("08") || nowMon.equals("10") || nowMon.equals("12")) {
                            mi = 31;
                        } else if (nowMon.equals("04") || nowMon.equals("06") || nowMon.equals("09") || nowMon.equals("11")) {
                            mi = 30;
                        } else {
                            mi = 29;
                        }
                        Log.d("mi", String.valueOf(mi));


                        ArrayList<BarEntry> profit = new ArrayList<>();
                        for (int i = 1; i < mi + 1; i++) {
                            int daySum = 0;
                            for (int j = 0; j < orderList.size(); j++) {
                                if (list_pay_time[j] != null && !list_status[j].equals("rf")) {
                                    if (Integer.parseInt(list_pay_time[j]) == i) {
                                        daySum = daySum + list_comp_amount[j];
                                    }
                                }
                            }
                            profit.add(new BarEntry(i, daySum));
                        }

                        BarDataSet barDataSet = new BarDataSet(profit, "이번 달 매출");
                        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        barDataSet.setValueTextColor(Color.BLACK);
                        barDataSet.setValueTextSize(12f);

                        BarData barData = new BarData(barDataSet);

                        barChart.setFitBars(true);
                        barChart.setData(barData);
                        barChart.getDescription().setText("원");
                        barChart.animateY(2000);
                    } else {
                        binding.barChart.setVisibility(View.GONE);
                        binding.noProfit.setVisibility(View.VISIBLE);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}