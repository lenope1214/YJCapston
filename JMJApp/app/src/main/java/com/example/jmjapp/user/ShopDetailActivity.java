package com.example.jmjapp.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.example.jmjapp.Adapter.DetailPagerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailActivity extends AppCompatActivity {


    static public String shopName;
    static public String shopNumber;
    static public String shopIntro;
    static public String shopOpen;
    static public String shopClose;
    static public char shopIsOpen;
    static public char shopIsRsPos;
    static public String shopAddress;
    static public String shopDetailAddress;
    static public String shopImgPath;

    TextView shop_detail_shopname, shop_detail_review, shop_detail_reply, shop_detail_avgtext;
    ImageView shop_detail_avgstar, shop_detail_phonecall_img, shop_detail_zzim_img;
    ConstraintLayout shop_detail_phonecall, shop_detail_zzim;
    FloatingActionButton order_basket;

    private Call<Shop> shopCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.shop_detail_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        shop_detail_shopname = (TextView)findViewById(R.id.shop_detail_shopname);
        shop_detail_review = (TextView) findViewById(R.id.shop_detail_review);
        shop_detail_reply = (TextView) findViewById(R.id.shop_detail_reply);
        //shop_detail_avgtext = (TextView) findViewById(R.id.shop_detail_avgtext);
        //shop_detail_avgstar = (ImageView) findViewById(R.id.shop_detail_avgstar);
        shop_detail_phonecall = (ConstraintLayout) findViewById(R.id.shop_detail_phonecall);
        shop_detail_zzim = (ConstraintLayout) findViewById(R.id.shop_detail_zzim);
        shop_detail_phonecall_img = (ImageView) findViewById(R.id.shop_detail_phonecall_img);
        shop_detail_zzim_img = (ImageView) findViewById(R.id.shop_detail_zzim_img);
        order_basket = (FloatingActionButton) findViewById(R.id.order_basket);

        
        shop_detail_phonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShopDetailActivity.this, "전화", Toast.LENGTH_SHORT).show();
            }
        });
        
        shop_detail_zzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shop_detail_zzim_img.setImageResource(R.drawable.zzimred);
            }
        });

        order_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("basket", MODE_PRIVATE);
                int list_size = pref.getInt("list_size", 0);
                if(list_size == 0) {
                    Toast.makeText(ShopDetailActivity.this, "메뉴를 선택해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(ShopDetailActivity.this, BasketActivity.class);
                    startActivity(intent);
                }
            }
        });

        shopNumber = getIntent().getStringExtra("shopNumber");
        showDetail();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDetail() {
        shopCall = Server.getInstance().getApi().shop(shopNumber);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if(response.code() == 200) {
                    Shop shop = response.body();
                    shop_detail_shopname.setText(shop.getName());

                    shopName = shop.getName();
                    shopIntro = shop.getIntro();
                    shopOpen = shop.getOpenTime();
                    shopClose = shop.getCloseTime();
                    shopIsOpen = shop.getIsOpen();
                    shopIsRsPos = shop.getIsRsPos();
                    shopAddress = shop.getAddress();
                    shopDetailAddress = shop.getAddressDetail();
                    shopImgPath = shop.getImgPath();
                    Log.d("rawa@@@@@@@@w",shopIntro + "@" + shopOpen + "@"  + shopClose + "@" + shopIsOpen + "@"
                            + shopIsRsPos + "@" + shopAddress + "@" + shopDetailAddress);

                    DetailPagerAdapter detailPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager());
                    ViewPager viewPager = (ViewPager)findViewById(R.id.shop_detail_viewpager3);
                    viewPager.setAdapter(detailPagerAdapter);
                    TabLayout tabLayout = (TabLayout)findViewById(R.id.shop_detail_tabs);
                    tabLayout.setupWithViewPager(viewPager);
                } else {
                    Log.d("result : ", "상세 조회 실패");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(shopCall!=null)
            shopCall.cancel();
    }
}
