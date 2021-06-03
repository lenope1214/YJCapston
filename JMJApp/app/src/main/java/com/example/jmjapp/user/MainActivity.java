package com.example.jmjapp.user;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;

import com.example.jmjapp.*;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class MainActivity extends AppCompatActivity {
    HomeFragment homeFragment;
    PosFragment posFragment;
    MapFragment mapFragment;
    MyFragment myFragment;
    ZzimFragment zzimFragment;

    BottomNavigationView bottomNavigation;
    SharedPreferences orderNumber;
    String QR = null;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getHashKey();

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        String user_id = pref.getString("user_id", null);

        homeFragment = new HomeFragment();
        posFragment = new PosFragment();
        mapFragment = new MapFragment();
        myFragment = new MyFragment();
        zzimFragment = new ZzimFragment();

        try {
            ShopDetailActivity.orderCheck.equals("1");
        } catch (Exception e) {
            ShopDetailActivity.orderCheck = "0";
        }

        Bundle bundle = new Bundle();
        bundle.putString("user_id", user_id);
        myFragment.setArguments(bundle);

//
//        try {
//            QR = getIntent().getStringExtra("QR");
//
//            if (orderCheck == 1 && QR.equals("order")) {
//                getSupportFragmentManager().beginTransaction().replace(R.id.container, posFragment).commit();
//            }
//
//        } catch (Exception e) {
//            return;
//        }

        final BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);


        if (ShopDetailActivity.orderCheck.equals("1")){
            bottomNavigation.setSelectedItemId(R.id.tab1);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, posFragment).commit();
        } else {
            bottomNavigation.setSelectedItemId(R.id.tab3);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        }

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.tab1:
                        //Toast.makeText(getApplicationContext(), "첫 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, posFragment).commit();
                        return true;

                    case R.id.tab2:
                        //Toast.makeText(getApplicationContext(), "두 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();
                        return true;

                    case R.id.tab3:
                        //Toast.makeText(getApplicationContext(), "세 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;

                    case R.id.tab4:
                        //Toast.makeText(getApplicationContext(), "네 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, zzimFragment).commit();
                        return true;

                    case R.id.tab5:
                        //Toast.makeText(getApplicationContext(), "다섯 번째 탭 선택됨", Toast.LENGTH_LONG).show();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
                        return true;
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
        } else if(position == 3) {
            bottomNavigation.setSelectedItemId(R.id.tab4);
        } else if(position == 4) {
            bottomNavigation.setSelectedItemId(R.id.tab5);
        }
    }

    private void getHashKey(){
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(this);
        alBuilder.setMessage("앱을 종료하시겠습니까?");

        alBuilder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        alBuilder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });

        alBuilder.setTitle("앱 종료");
        alBuilder.show();
    }

}