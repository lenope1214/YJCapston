package com.example.jmjapp.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;
import com.example.jmjapp.Adapter.DetailPagerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Mark;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopDetailActivity extends AppCompatActivity {

    static public String orderCheck;
    static public String tableNumber;
    static public String shopNumber;

    static public String shopName;
    static public String shopIntro;
    static public String shopOpen;
    static public String shopClose;
    static public char shopIsOpen;
    static public char shopIsRsPos;
    static public String shopAddress;
    static public String shopDetailAddress;
    static public String shopImgPath;
    static public String ownerId;
    static public String phone;

    TextView shop_detail_shopname, shop_detail_review, shop_detail_reply, shop_detail_avgtext, start_rating_tv;
    ImageView shop_detail_avgstar, shop_detail_phonecall_img, shop_detail_zzim_img;
    ConstraintLayout shop_detail_phonecall, shop_detail_zzim;
    FloatingActionButton order_basket;
    RatingBar ratingBar;

    SharedPreferences pref;

    private Call<Shop> shopCall;
    private Call<ResponseBody> regzzim;
    private Call<ResponseBody> delzzim;
    private Call<Mark.MarkList> getMarks;
    private Call<List<Review>> reviewCall;

    private String jwt, userId;

    private boolean zzim;
    private boolean zzimPlus, zzimReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail3);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", null);
        userId = pref.getString("user_id",null);
        Toolbar toolbar = (Toolbar) findViewById(R.id.shop_detail_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        shop_detail_shopname = (TextView)findViewById(R.id.shop_detail_shopname);
        shop_detail_review = (TextView) findViewById(R.id.shop_detail_review);
//        shop_detail_reply = (TextView) findViewById(R.id.shop_detail_reply);
        //shop_detail_avgtext = (TextView) findViewById(R.id.shop_detail_avgtext);
        //shop_detail_avgstar = (ImageView) findViewById(R.id.shop_detail_avgstar);
        shop_detail_phonecall = (ConstraintLayout) findViewById(R.id.shop_detail_phonecall);
        shop_detail_zzim = (ConstraintLayout) findViewById(R.id.shop_detail_zzim);
        shop_detail_phonecall_img = (ImageView) findViewById(R.id.shop_detail_phonecall_img);
        shop_detail_zzim_img = (ImageView) findViewById(R.id.shop_detail_zzim_img);
        order_basket = (FloatingActionButton) findViewById(R.id.order_basket);
        start_rating_tv = findViewById(R.id.start_rating_tv);
        ratingBar = findViewById(R.id.shop_detail_ratingBar);

        shopNumber = getIntent().getStringExtra("shopNumber");
        tableNumber = getIntent().getStringExtra("tableNumber");
        orderCheck = getIntent().getStringExtra("orderCheck");

//        pref = getSharedPreferences("orderCheck",MODE_PRIVATE);
//        orderCheck = pref.getInt("OrderNumber", 0);
//        System.out.println(orderCheck+"OOOOOOOOOOOOOOOOOOOOOOO");
        System.out.println(shopNumber + "%^&" + tableNumber + "%^&" + orderCheck + "%^&");


        shop_detail_phonecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ShopDetailActivity.this, "전화", Toast.LENGTH_SHORT).show();
            }
        });

        getMarks = Server.getInstance().getApi().getMarks("Bearer "+jwt);
        getMarks.enqueue(new Callback<Mark.MarkList>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<Mark.MarkList> call, Response<Mark.MarkList> response) {
                if (response.isSuccessful()){
                    List<Shop> markList = response.body().getShopList();

                    for (Shop list : markList){
                        if (list.getShopId().equals(shopNumber)){
                            shop_detail_zzim_img.setImageResource(R.drawable.zzimred);
                            zzim = false;
                        } else {
                            shop_detail_zzim_img.setImageResource(R.drawable.zzimblack);
                            zzim = true;
                        }
                    }

                } else {
                    System.out.println("찜 목록 조회 실패");
                }

            }

            @Override
            public void onFailure(Call<Mark.MarkList> call, Throwable t) {
                System.out.println("찜 목록 조회 네트워크 실패"+t.getMessage()+t.toString()+t.getCause());
            }
        });

        Map<String,String> map = new HashMap<>();
        map.put("shopId",shopNumber);

//   찜 ♥
        shop_detail_zzim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (zzim){
                    regzzim = Server.getInstance().getApi().regzzim("Bearer "+jwt, map);
                    regzzim.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            System.out.println(jwt+"//////////////////"+shopNumber);
                            if (response.code() == 201){
                                shop_detail_zzim_img.setImageResource(R.drawable.zzimred);
                                System.out.println("찜 나이스");
                                zzim = false;
                            } else {
                                System.out.println("찜 실패");
                                System.out.println(response.code()+"ggggggggggggggggggggg");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            System.out.println("찜 등록 네트워크 오류");
                        }
                    });
                } else {
                    delzzim = Server.getInstance().getApi().deleteZzim("Bearer "+jwt, shopNumber);
                    delzzim.enqueue(new Callback<ResponseBody>() {
                        @SneakyThrows
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Log.d("찜 삭제 성공", "찜 삭제 성공");
                                shop_detail_zzim_img.setImageResource(R.drawable.zzimblack);
                                zzim = true;
                            } else {
                                Log.d("찜 삭제 실패1", "찜 삭제 실패1"+response.errorBody().string());
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Log.d("찜 삭제 실패2", "찜 삭제 실패2"+t.getCause());
                        }
                    });
                }

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
                    intent.putExtra("shopNumber",shopNumber);
                    intent.putExtra("tableNumber",tableNumber);
                    startActivity(intent);
                }
            }
        });


        showDetail();

        showReviewGrade();
    }

    private void showReviewGrade() {
        reviewCall = Server.getInstance().getApi().reviewList("Bearer " + jwt, shopNumber);
        reviewCall.enqueue(new Callback<List<Review>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Review>> call, Response<List<Review>> response) {
                if (response.isSuccessful()) {
                    Log.d("reviewList 성공", "reviewList 성공");
                    List<Review> reviewList = response.body();
                    shop_detail_review.setText(" " + reviewList.size() + "개");

                    int list_rating[] = new int[reviewList.size()];

                    int index = 0;
                    int sum = 0;

                    for (Review list : reviewList) {
                        if (String.valueOf(list.getScore()) == null) {
                            list_rating[index] = 0;
                        } else {
                            list_rating[index] = list.getScore();
                            index++;
                        }
                    }

                    for (int i = 0; i < reviewList.size(); i++) {
                        sum = sum + list_rating[i];
                    }

                    if (reviewList.size() != 0) {
                        sum = sum / reviewList.size();

                        start_rating_tv.setText(String.valueOf(sum) + "점");
                        ratingBar.setRating(sum);
                    } else {
                        start_rating_tv.setText("0점");
                        ratingBar.setRating(0);
                    }

                } else {
                    Log.d("reviewList 실패1", "reviewList 실패1" + response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<Review>> call, Throwable t) {
                Log.d("reviewList 실패2", "reviewList 실패2" + t.getCause());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDetail() {
        System.out.println(shopNumber+"pppppppppppppppppppp");
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
                    ownerId = shop.getOwnerId();
                    phone = shop.getPhone();

                    Log.d("rawa@@@@@@@@w",shopIntro + "@" + shopOpen + "@"  + shopClose + "@" + shopIsOpen + "@"
                            + shopIsRsPos + "@" + shopAddress + "@" + shopDetailAddress +"@" + ownerId);

                    if (phone != null) {
                        shop_detail_phonecall.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:" + phone));
                                startActivity(intent);
                            }
                        });
                    }

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
