package com.example.jmjapp.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.MyOrderListAdapter;
import com.example.jmjapp.Adapter.MyReviewListAdapter;
import com.example.jmjapp.Adapter.ReviewRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityRecordReviewBinding;
import com.example.jmjapp.databinding.ActivityReviewRegisterBinding;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordReview extends AppCompatActivity {
    private ActivityRecordReviewBinding binding;
    private MyReviewListAdapter adapter;
    private RecyclerView rv_my_review_list;
    private ArrayList<Review> mItems = new ArrayList();

    private String jwt;
    private Call<List<Review>> reviewCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.my_review_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        rv_my_review_list = binding.rvMyReviewList;
        adapter = new MyReviewListAdapter(getApplicationContext());

        reviewCall = Server.getInstance().getApi().myReviewList("Bearer " + jwt);
        reviewCall.enqueue(new Callback<List<Review>>() {
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    Log.d("reviewList 성공", "reviewList 성공");
                    List<Review> reviewList = response.body();
                    for(Review list : reviewList) {
                        mItems.add(new Review(list.getReviewId(), list.getUserId(), list.getShopId(),
                                list.getContent(), list.getParentId(), list.getRegDate(),
                                list.getScore(), list.getImgUrl(), list.getOrderId()));
                        Log.d("list", response.body().toString());
                        rv_my_review_list.setHasFixedSize(true);
                        adapter.setItems(mItems);
                        rv_my_review_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                        rv_my_review_list.setAdapter(adapter);
                    }
                } else {
                    Log.d("reviewList 실패1", "reviewList 실패1");
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.d("reviewList 실패2", "reviewList 실패2");
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}