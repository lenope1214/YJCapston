package com.example.jmjapp.user;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityBellBinding;
import com.example.jmjapp.databinding.ActivityRecordPointBinding;
import com.example.jmjapp.dto.MemberDTO;
import com.example.jmjapp.network.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordPoint extends AppCompatActivity {
    private ActivityRecordPointBinding binding;

    private String jwt;
    private int point;

    private Call<MemberDTO> memberDTOCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecordPointBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.point_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        memberDTOCall = Server.getInstance().getApi().getUser("Bearer " + jwt);
        memberDTOCall.enqueue(new Callback<MemberDTO>() {
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                if (response.isSuccessful()) {
                    Log.d("getUser 성공", "getUser 성공");
                    point = response.body().getUser().getPoint();
                    if (point == 0) {
                        binding.constraintLayoutNoPoint.setVisibility(View.VISIBLE);
                        binding.constraintLayoutHavePoint.setVisibility(View.GONE);
                    } else {
                        binding.constraintLayoutNoPoint.setVisibility(View.GONE);
                        binding.constraintLayoutHavePoint.setVisibility(View.VISIBLE);
                        binding.yesPointTv.setText("현재 보유 포인트 : " + point + "원");
                    }
                } else {
                    Log.d("getUser 실패1", "getUser 실패1");
                }
            }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Log.d("getUser 실패2", "getUser 실패2");
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}