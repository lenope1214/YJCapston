package com.example.jmjapplication2.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.user.ShopDetailActivity;

import java.util.ArrayList;

public class InfoFragment extends Fragment {
    private static String shopNumber;
    View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_restaurant_list;
    private RecyclerView rv_info_list;
    ArrayList mItems = new ArrayList<>();
    TextView shop_detail_intro, shop_detail_time, shop_detail_addr;

    public static InfoFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_info, container, false);

            //rv_restaurant_list = (RecyclerView)view.findViewById(R.id.rv_info_list);

            shop_detail_intro = view.findViewById(R.id.shop_detail_intro);
            shop_detail_time = view.findViewById(R.id.shop_detail_time);
            shop_detail_addr = view.findViewById(R.id.shop_detail_addr);

            shopNumber = ShopDetailActivity.shopNumber;
            Log.d("Daw",shopNumber);
            shop_detail_intro.setText(ShopDetailActivity.shopIntro);
            shop_detail_time.setText(ShopDetailActivity.shopOpen + " ~ " + ShopDetailActivity.shopClose);
            shop_detail_addr.setText(ShopDetailActivity.shopAddress + ShopDetailActivity.shopDetailAddress);
//            Shop shop = new Shop(ShopDetailActivity.shopIntro);
//
//            rv_info_list.setHasFixedSize(true);
//            adapter = new InfoRecyclerAdapter(getContext(), mItems);
//            rv_info_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//            rv_info_list.setAdapter(adapter);
         //   showInfo();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
//    private void showList(String category) {
//        Retrofit retrofit =new Retrofit.Builder().
//                addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(ApiService.BASEURL)
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<List<Shop>> shopCall = apiService.shopList(category);
//        shopCall.enqueue(new Callback<List<Shop>>() {
//            @Override
//            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
//                if(response.isSuccessful()) {
//                    if(response.code() == 200) {
//                        List<Shop> shopList = response.body();
//                        for(Shop list : shopList) {
//                            mItems.add(new Shop(list.getId(), list.getName(),
//                                    list.getIntro(), list.getCloseTime(), list.getOpenTime(),
//                                    list.getAddress(), list.getAddressDetail(), list.getIsRsPos(),
//                                    list.getCategory(), list.getIsOpen()));
//                            rv_restaurant_list.setHasFixedSize(true);
//                            adapter = new RestaurantRecyclerAdapter(getContext(), mItems);
//                            rv_restaurant_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            rv_restaurant_list.setAdapter(adapter);
//                        }
//                    } else {
//                        Toast.makeText(getContext(), "조회 실패", Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Shop>> call, Throwable t) {
//
//            }
//        });
//
//    }
//    private void showInfo() {
//        Retrofit retrofit =new Retrofit.Builder().
//                addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(ApiService.BASEURL)
//                .build();
//        ApiService apiService = retrofit.create(ApiService.class);
//        Call<Shop> shopCall = apiService.shop(shopNumber);
//        shopCall.enqueue(new Callback<Shop>() {
//            @Override
//            public void onResponse(Call<Shop> call, Response<Shop> response) {
//                if(response.isSuccessful()) {
//                    if(response.code() == 200) {
//                        Log.d("성공","ㅅㄱ");
//                        Shop shop = response.body();
//                        Log.d(".성공2", shop.toString());
////                        Shop shop2 = new Shop();
//                        //rv_info_list.setHasFixedSize(true);
//                        adapter = new InfoRecyclerAdapter(getContext(), shop);
//                        //rv_info_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//                        //rv_info_list.setAdapter(adapter);
//                    } else {
//                        Log.d("실패","ㅅㅍ");
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Shop> call, Throwable t) {
//                Log.d("실패","ㅅㅍ");
//            }
//        });
//    }
}
