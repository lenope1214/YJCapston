package com.example.jmjapp.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jmjapp.*;
import com.example.jmjapp.Adapter.MenuChooseOptionGroupsAdapter;
import com.example.jmjapp.Adapter.MenuMoveOptionAdapter;
import com.example.jmjapp.Adapter.RestaurantRecyclerAdapter;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuChoosedActivity extends AppCompatActivity {
    private String menuName, menuImage;
    private int menuCount;
    public static int menuPrice;
    private String menuIntro, menuId, jwt;
    TextView menu_choosed_name, menu_choosed_price, menu_choosed_intro;
    ImageView menu_choosed_img;
    Button menu_choosed_btn;
    boolean is_it_already;
    private RecyclerView menu_choose_option_list;
    private Call<List<OptionGroups>> optionGroupsCall;
    private MenuChooseOptionGroupsAdapter adapter;
    ArrayList<OptionGroups> mItems = new ArrayList<>();

    private char is_rs_pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_choosed);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_choosed_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Intent intent = getIntent();
        menuId = intent.getStringExtra("menuId");
        menuName = intent.getStringExtra("menuName");
        menuPrice = intent.getIntExtra("menuPrice", 0);
        menuIntro = intent.getStringExtra("menuIntro");
        menuImage = intent.getStringExtra("menuImage");
//        Log.d("shopNumber", ShopDetailActivity.shopNumber);
//        Log.d("menuId", menuId);

        String qr = intent.getStringExtra("qr");
        if (qr != null) {
            Log.d("qr rs_pos", "qr rs_pos");
            is_rs_pos = QrMenuActivity.is_rs_pos;
        } else {
            Log.d("rs_pos", "rs_pos");
            is_rs_pos = ShopDetailActivity.shopIsRsPos;
        }

        menu_choosed_name = findViewById(R.id.menu_choosed_name);
        menu_choosed_price = findViewById(R.id.menu_choosed_price);
        menu_choosed_intro = findViewById(R.id.menu_choosed_intro);
        menu_choosed_img = findViewById(R.id.menu_choosed_img);
        menu_choosed_btn = findViewById(R.id.menu_choosed_btn);
        //menu_choose_option_list = findViewById(R.id.menu_choose_option_list);

        menu_choosed_name.setText(menuName);
        menu_choosed_price.setText(menuPrice + "원");
        menu_choosed_intro.setText(menuIntro);
        Glide.with(this).load("http://3.34.55.186:8088/" + menuImage).override(500, 500).into(menu_choosed_img);

        if (is_rs_pos == 'Y') {
            menu_choosed_btn.setOnClickListener(v -> {
                SharedPreferences pref2 = getSharedPreferences("basket_shop", MODE_PRIVATE);
                SharedPreferences.Editor editor2 = pref2.edit();
                String shopNumber = pref2.getString("shop_number", "no_shop");

                if (shopNumber.equals("no_shop")) {
                    if (qr != null) {
                        editor2.putString("shop_number", QrMenuActivity.shopNumber);
                        editor2.apply();
                        shopNumber = pref2.getString("shop_number", "no_shop");
                    } else {
                        editor2.putString("shop_number", ShopDetailActivity.shopNumber);
                        editor2.apply();
                        shopNumber = pref2.getString("shop_number", "no_shop");
                    }
                }
                if (shopNumber.equals(ShopDetailActivity.shopNumber) || shopNumber.equals(QrMenuActivity.shopNumber)) {
                    SharedPreferences pref1 = getSharedPreferences("basket", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref1.edit();

                    int list_size = pref1.getInt("list_size", 0);
                    if (list_size != 0) {
                        for (int i = 0; i < list_size; i++) {
                            String[] menu_already_is = new String[list_size];
                            menu_already_is[i] = pref1.getString("list_" + i + "_name", null);
                            if (menu_already_is[i].equals(menuName)) {
                                is_it_already = true;
                            }
                        }
                    }

                    if (is_it_already == false) {
                        menuCount = pref1.getInt("list_" + list_size + "_count", 1);
                        editor.putString("list_" + list_size + "_name", menuName);
                        editor.putInt("list_" + list_size + "_price", menuPrice);
                        editor.putInt("list_" + list_size + "_count", menuCount);
                        editor.putString("list_" + list_size + "_img", menuImage);
                        editor.putString("list_" + list_size + "_id", menuId);
                        editor.putInt("list_size", list_size + 1);
                        editor.apply();
                    } else {
                        for (int i = 0; i < list_size; i++) {
                            String[] nameList = new String[list_size];
                            Integer[] priceList = new Integer[list_size];
                            Integer[] countList = new Integer[list_size];
                            nameList[i] = pref1.getString("list_" + i + "_name", null);
                            priceList[i] = pref1.getInt("list_" + i + "_price", 0);
                            countList[i] = pref1.getInt("list_" + i + "_count", 1);
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
                    Snackbar.make(v, "장바구니에는 같은 가게의 메뉴만 담을 수 있습니다.", Snackbar.LENGTH_SHORT).setAction("확인", v1 -> {
                        return;
                    }).show();
                }
            });
        } else {
            menu_choosed_btn.setOnClickListener(v -> Snackbar.make(v, "예약이 가능한 매장이 아닙니다.", Snackbar.LENGTH_SHORT).setAction("확인", v12 -> {
                return;
            }).show());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}