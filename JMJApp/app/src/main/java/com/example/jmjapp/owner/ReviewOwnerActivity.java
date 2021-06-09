package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;

import com.example.jmjapp.Adapter.ReviewOwerAdapter;
import com.example.jmjapp.Adapter.ReviewRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityBellBinding;
import com.example.jmjapp.databinding.ActivityReviewOwnerBinding;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewOwnerActivity extends AppCompatActivity {
    private ActivityReviewOwnerBinding binding;

    private ReviewOwerAdapter adapter;
    private RecyclerView rv_owner_review_list;
    private ArrayList<Review> mItems = new ArrayList<>();
    private String jwt, shopId, orderId;
    private Call<List<Review>> reviewCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewOwnerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.review_owner_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        rv_owner_review_list = binding.rvOwnerReviewList;
        adapter = new ReviewOwerAdapter(getApplicationContext());

        reviewCall = Server.getInstance().getApi().reviewList("Bearer " + jwt, MainActivity_O.shopNumber);
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
                        rv_owner_review_list.setHasFixedSize(true);
                        adapter.setItems(mItems);
                        rv_owner_review_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                        rv_owner_review_list.setAdapter(adapter);
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