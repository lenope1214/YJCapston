package com.example.jmjapp.user;

import android.content.DialogInterface;
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

    private AlertDialog dialog;

    static public BottomNavigationView bottomNavigation;

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

        Bundle bundle = new Bundle();
        bundle.putString("user_id", user_id);
        myFragment.setArguments(bundle);

        bottomNavigation = findViewById(R.id.bottom_navigation);


        if (QrReaderActivity.orderCheck.equals("1")) {
            bottomNavigation.setSelectedItemId(R.id.tab1);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, posFragment).commit();
        } else {
            bottomNavigation.setSelectedItemId(R.id.tab3);
            getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        }

        bottomNavigation.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()) {
                case R.id.tab1:
                    if (QrReaderActivity.shopnum1 == null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        dialog = builder.setMessage("매장을 방문해주세요!").setPositiveButton("확인", null).create();
                        dialog.show();
                    } else {
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, posFragment).commit();
                    }
                    return true;

                case R.id.tab2:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, mapFragment).commit();
                    return true;

                case R.id.tab3:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                    return true;

                case R.id.tab4:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, zzimFragment).commit();
                    return true;

                case R.id.tab5:
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, myFragment).commit();
                    return true;
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
        } else if (position == 3) {
            bottomNavigation.setSelectedItemId(R.id.tab4);
        } else if (position == 4) {
            bottomNavigation.setSelectedItemId(R.id.tab5);
        }
    }

    private void getHashKey() {
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

        alBuilder.setPositiveButton("예", (dialog, which) -> finish());
        alBuilder.setNegativeButton("아니오", (dialog, which) -> {
            return;
        });

        alBuilder.setTitle("앱 종료");
        alBuilder.show();
    }

}
