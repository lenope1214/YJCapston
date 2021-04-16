package com.example.jmjapplication2.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapplication2.*;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MenuChoosedActivity extends AppCompatActivity {
    private String menuName;
    private int menuCount;
    public static int menuPrice;
    private String menuIntro;
    TextView menu_choosed_name, menu_choosed_price, menu_choosed_intro;
    ImageView menu_choosed_img;
    Button menu_choosed_btn;
    boolean is_it_already;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choosed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_choosed_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        Intent intent = getIntent();
        menuName = intent.getStringExtra("menuName");
        menuPrice = intent.getIntExtra("menuPrice", 0);
        menuIntro = intent.getStringExtra("menuIntro");
        Log.d("shopNumber", ShopDetailActivity.shopNumber);

        char is_rs_pos = ShopDetailActivity.shopIsRsPos;

        menu_choosed_name = findViewById(R.id.menu_choosed_name);
        menu_choosed_price = findViewById(R.id.menu_choosed_price);
        menu_choosed_intro = findViewById(R.id.menu_choosed_intro);
        menu_choosed_img = findViewById(R.id.menu_choosed_img);
        menu_choosed_btn = findViewById(R.id.menu_choosed_btn);

        menu_choosed_name.setText(menuName);
        menu_choosed_price.setText(menuPrice + "원");
        menu_choosed_intro.setText(menuIntro);

        if (is_rs_pos == 'Y') {
            menu_choosed_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences pref2 = getSharedPreferences("basket_shop", MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = pref2.edit();
                    String shopNumber = pref2.getString("shop_number", "no_shop");

                    if(shopNumber.equals("no_shop")) {
                        editor2.putString("shop_number", ShopDetailActivity.shopNumber);
                        editor2.apply();
                        shopNumber = pref2.getString("shop_number", "no_shop");
                    }

                    if (shopNumber.equals(ShopDetailActivity.shopNumber)) {
                        SharedPreferences pref = getSharedPreferences("basket", MODE_PRIVATE);
                        SharedPreferences.Editor editor = pref.edit();

                        int list_size = pref.getInt("list_size", 0);
                        if (list_size != 0) {
                            for (int i = 0; i < list_size; i++) {
                                String[] menu_already_is = new String[list_size];
                                menu_already_is[i] = pref.getString("list_" + i + "_name", null);
                                if (menu_already_is[i].equals(menuName)) {
                                    is_it_already = true;
                                }
                            }
                        }

                        if (is_it_already == false) {
                            menuCount = pref.getInt("list_" + list_size + "_count", 1);
                            editor.putString("list_" + list_size + "_name", menuName);
                            editor.putInt("list_" + list_size + "_price", menuPrice);
                            editor.putInt("list_" + list_size + "_count", menuCount);
                            editor.putInt("list_size", list_size + 1);
                            editor.apply();
                        } else {
                            for (int i = 0; i < list_size; i++) {
                                String[] nameList = new String[list_size];
                                Integer[] priceList = new Integer[list_size];
                                Integer[] countList = new Integer[list_size];
                                nameList[i] = pref.getString("list_" + i + "_name", null);
                                priceList[i] = pref.getInt("list_" + i + "_price", 0);
                                countList[i] = pref.getInt("list_" + i + "_count", 1);
                                if (nameList[i].equals(menuName)) {
                                    editor.putInt("list_" + i + "_count", countList[i] + 1);
                                    editor.putInt("list_" + i + "_price", priceList[i] + menuPrice);
                                }
                            }
                            editor.apply();
                        }

                        Toast.makeText(getApplication(), "메뉴를 담았습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Snackbar.make(v, "장바구니에는 같은 가게의 메뉴만 담을 수 있습니다.", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                return;
                            }
                        }).show();
                    }
                }
            });
        } else {
            menu_choosed_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "예약이 가능한 매장이 아닙니다.", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            return;
                        }
                    }).show();
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}