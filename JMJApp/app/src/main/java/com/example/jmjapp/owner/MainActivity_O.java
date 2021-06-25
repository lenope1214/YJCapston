package com.example.jmjapp.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.jmjapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity_O extends AppCompatActivity {

    static public String shopNumber;

    HomeFragment_O homeFragment_o;
    PosFragment_O posFragment_o;
    ShopDetailFragment_O shopDetailFragment_o;

    static public BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o);

        Intent intent = getIntent();
        shopNumber = intent.getStringExtra("shopNumber");
        String shopName = intent.getStringExtra("shopName");
//        Log.d("re", shopNumber);

        homeFragment_o = new HomeFragment_O();
        posFragment_o = new PosFragment_O();
        shopDetailFragment_o = new ShopDetailFragment_O();

        Bundle bundle = new Bundle();
        bundle.putString("shopNumber", shopNumber);
        bundle.putString("shopName", shopName);
        homeFragment_o.setArguments(bundle);
        shopDetailFragment_o.setArguments(bundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment_o).commit();

        bottomNavigation = findViewById(R.id.o_bottom_navigation);
        bottomNavigation.setSelectedItemId(R.id.o_tab2);

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.o_tab1:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, posFragment_o).commit();
                    return true;

                case R.id.o_tab2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment_o).commit();
                    return true;

                case R.id.o_tab3:
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
        });
    }

    public void onTabSelected(int position) {
        if (position == 0) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
        } else if (position == 1) {
            bottomNavigation.setSelectedItemId(R.id.tab2);
        } else if (position == 2) {
            bottomNavigation.setSelectedItemId(R.id.tab3);
        }
//        else if(position == 3) {
//            bottomNavigation.setSelectedItemId(R.id.tab4);
//        } else if(position == 4) {
//            bottomNavigation.setSelectedItemId(R.id.tab5);
//        }
    }
}
