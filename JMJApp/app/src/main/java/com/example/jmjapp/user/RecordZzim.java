package com.example.jmjapp.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.ZzimAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Mark;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordZzim extends AppCompatActivity {

    private RecyclerView rv_zzim;
    private String jwt;
    private Call<Mark.MarkList> getMarks;
    private ArrayList<Shop> mItems = new ArrayList<>();
    private RecyclerView.Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_zzim);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.zzim_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        rv_zzim = findViewById(R.id.rv_zzim);

        showZzimList();
    }

    private void showZzimList() {
        getMarks = Server.getInstance().getApi().getMarks("Bearer " + jwt);
        getMarks.enqueue(new Callback<Mark.MarkList>() {
            @Override
            public void onResponse(Call<Mark.MarkList> call, Response<Mark.MarkList> response) {
                if (response.isSuccessful()) {
                    List<Shop> markList = response.body().getShopList();

                    for (Shop list : markList) {
                        System.out.println(list + "qqqqqqqqqqqqqq");
                        mItems.add(new Shop(list.getImgPath(), list.getName(), list.getAddress(), list.getCategory()));
                        System.out.println(mItems + "qqqqqqqqqqqqqqq");
                        rv_zzim.setHasFixedSize(true);
                        adapter = new ZzimAdapter(getApplicationContext(), mItems);
                        rv_zzim.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        rv_zzim.setAdapter(adapter);
                    }

                } else {
                    System.out.println("찜 목록 조회 실패");
                }
            }

            @Override
            public void onFailure(Call<Mark.MarkList> call, Throwable t) {
                System.out.println("찜 목록 네트워크 실패");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getMarks != null)
            getMarks.cancel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}