package com.example.jmjapp.user;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.jmjapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

//이용약관
public class OperationGuideActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    TextView tv_guide1;
    TextView tv_guide2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operation_guide);

        Toolbar toolbar = (Toolbar) findViewById(R.id.bell_toolbar_guide);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}