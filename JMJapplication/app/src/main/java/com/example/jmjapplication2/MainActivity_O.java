package com.example.jmjapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_O extends AppCompatActivity {

    HomeFragment_O homeFragment_o;
    PosFragment_O posFragment_o;
    ShopDetailFragment_O shopDetailFragment_o;

    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o);

        Intent intent = getIntent();
        String owner_number = intent.getStringExtra("owner_number");
        //Log.d("result :", id);

        homeFragment_o = new HomeFragment_O();
        posFragment_o = new PosFragment_O();
        shopDetailFragment_o = new ShopDetailFragment_O();

        Bundle bundle = new Bundle();
        bundle.putString("owner_number", owner_number);
        homeFragment_o.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment_o).commit();

        final BottomNavigationView bottomNavigation = findViewById(R.id.o_bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.o_tab2);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.o_tab1:
                        Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, posFragment_o).commit();
                        return true;

                    case R.id.o_tab2:
                        Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment_o).commit();
                        return true;

                    case R.id.o_tab3:
                        Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, shopDetailFragment_o).commit();
                        return true;

//                    case R.id.o_tab4:
//                        Toast.makeText(getApplicationContext(), "네 번째 탭 선택됨", Toast.LENGTH_LONG).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, reservationFragment_o).commit();
//                        return true;
//
//                    case R.id.o_tab5:
//                        Toast.makeText(getApplicationContext(), "다섯 번째 탭 선택됨", Toast.LENGTH_LONG).show();
//                        getSupportFragmentManager().beginTransaction().replace(R.id.container, chattingFragment_o).commit();
//                        return true;
                }
                return false;
            }
        });
    }

    public void onTabSelected(int position) {
        if(position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if(position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if(position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
//        else if(position == 3) {
//            bottomNavigation.setSelectedItemId(R.id.tab4);
//        } else if(position == 4) {
//            bottomNavigation.setSelectedItemId(R.id.tab5);
//        }
    }
}
