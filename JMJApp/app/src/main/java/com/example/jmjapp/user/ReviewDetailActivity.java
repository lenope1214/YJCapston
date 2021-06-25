package com.example.jmjapp.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.bumptech.glide.Glide;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityReviewDetailBinding;
import com.example.jmjapp.databinding.ActivityReviewRegisterBinding;

import java.util.PrimitiveIterator;

public class ReviewDetailActivity extends AppCompatActivity {
    private ActivityReviewDetailBinding binding;
    private String content, userId, imgUrl, shopId, regDate;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.review_detail_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        shopId = intent.getStringExtra("shopId");
        content = intent.getStringExtra("content");
        imgUrl = intent.getStringExtra("imgUrl");
        regDate = intent.getStringExtra("regDate");
        score = intent.getIntExtra("score", 0);

        binding.reviewDetailUser.setText(userId + "님");
        binding.reviewDetailContent.setText(content);
        binding.reviewDetailRatingBar.setRating(score);

        Glide.with(this).load("http://3.34.55.186:8088/" + imgUrl).into(binding.reviewDetailImg);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}