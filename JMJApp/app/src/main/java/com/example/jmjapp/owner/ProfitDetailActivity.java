package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

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

                    int list_comp_amount[] = new int[orderList.size()];
                    String list_pay_time[] = new String[orderList.size()];

                    int index = 0;

                    for(Order list : orderList) {
                        list_comp_amount[index] = list.getCompleAmount();
                        list_pay_time[index] = list.getPayTime();
                        System.out.println(String.valueOf(list_pay_time[index]));
                        index++;
                    }

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
                    String nowMon = getTime.substring(5,7);
                    System.out.println(nowMon);

                    int mi = 0;

                    if (nowMon.equals("01") || nowMon.equals("03") || nowMon.equals("05") || nowMon.equals("07")
                            || nowMon.equals("08") || nowMon.equals("10") || nowMon.equals("12")) {
                        mi = 31;
                    } else if (nowMon.equals("04") || nowMon.equals("06") || nowMon.equals("09")|| nowMon.equals("11")) {
                        mi = 30;
                    } else {
                        mi = 29;
                    }
                    Log.d("mi", String.valueOf(mi));


                    ArrayList<BarEntry> profit = new ArrayList<>();
                    for (int i = 1; i < mi+1; i++) {
                        profit.add(new BarEntry(i, 400));
                    }

                    BarDataSet barDataSet = new BarDataSet(profit, "Profit");
                    barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                    barDataSet.setValueTextColor(Color.BLACK);
                    barDataSet.setValueTextSize(10f);

                    BarData barData = new BarData(barDataSet);

                    barChart.setFitBars(true);
                    barChart.setData(barData);
                    barChart.getDescription().setText("Bar Chart Example");
                    barChart.animateY(2000);

                } else {
                    Log.d("orderList 실패1", "orderList 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("orderList 실패2", "orderList 실패2"+t.getCause());
            }
        });

//        ArrayList<BarEntry> profit = new ArrayList<>();
//        profit.add(new BarEntry(2014, 400));
//        profit.add(new BarEntry(2015, 500));
//        profit.add(new BarEntry(2016, 600));
//        profit.add(new BarEntry(2017, 700));
//        profit.add(new BarEntry(2018, 800));
//        profit.add(new BarEntry(2019, 900));
//        profit.add(new BarEntry(2020, 1000));
//        profit.add(new BarEntry(2021, 1100));
//        profit.add(new BarEntry(2022, 1200));
//        profit.add(new BarEntry(2023, 1100));
//        profit.add(new BarEntry(2024, 1000));
//        profit.add(new BarEntry(2025, 900));
//        profit.add(new BarEntry(2026, 800));
//        profit.add(new BarEntry(2027, 700));
//        profit.add(new BarEntry(2028, 600));
//        profit.add(new BarEntry(2029, 500));
//        profit.add(new BarEntry(2030, 1200));
//        profit.add(new BarEntry(2031, 1100));
//        profit.add(new BarEntry(2032, 1000));
//        profit.add(new BarEntry(2033, 900));
//        profit.add(new BarEntry(2034, 800));
//        profit.add(new BarEntry(2035, 700));
//        profit.add(new BarEntry(2036, 600));
//        profit.add(new BarEntry(2037, 500));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}