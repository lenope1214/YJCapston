package com.example.jmjapplication2.user;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jmjapplication2.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class OperationGuide extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    TextView tv_guide1;
    TextView tv_guide2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_guide);
        tv_guide1 = findViewById(R.id.tv_guide1);
        tv_guide2 = findViewById(R.id.tv_guide2);

        tv_guide1.setVisibility(View.VISIBLE);
        tv_guide2.setVisibility(View.GONE);

        bottomNavigation = findViewById(R.id.guide_bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.tab1:
                    tv_guide1.setVisibility(View.VISIBLE);
                    tv_guide2.setVisibility(View.GONE);
                    return true;
                case R.id.tab2:
                    tv_guide1.setVisibility(View.GONE);
                    tv_guide2.setVisibility(View.VISIBLE);
                    return true;
            }
            return true;
        });

    }
}