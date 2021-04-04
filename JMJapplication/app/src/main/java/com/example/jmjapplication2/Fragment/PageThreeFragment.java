package com.example.jmjapplication2.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.Adapter.RestaurantRecyclerAdapter;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Shop;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class PageThreeFragment extends Fragment {
    View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_restaurant_list;
    ArrayList<Shop> mItems = new ArrayList<>();

    public static PageThreeFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        PageThreeFragment fragment = new PageThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_page_one, container, false);

            rv_restaurant_list = (RecyclerView)view.findViewById(R.id.rv_restaurant_list);
            showList("중식");
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void showList(String category) {
        Log.d("result1" , "시발 왜안돼1");
        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Shop>> shopCall = apiService.shopList(category);
        shopCall.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                System.out.println("showList 성복 사랑 "+response.code());
                if(response.isSuccessful()) {
                    Log.d("result2" , "시발 왜안돼2");
                    if(response.code() == 200) {
                        List<Shop> shopList = response.body();
                        Log.d("result3" , "시발 왜안돼3");
                        for(Shop list : shopList) {
                            Log.e("result : ", response.body().toString());
//                            mItems.add(new Shop(list.getId(), list.getName(),
//                                    list.getIntro(), list.getCloseTime(),
//                                    list.getOpenTime(), list.getAddress(), list.getAddressDetail(), list.getIsRsPos(),
//                                    list.getCategory(), list.getIsOpen()));
//                            rv_restaurant_list.setHasFixedSize(true);
//                            adapter = new RestaurantRecyclerAdapter(getContext(), mItems);
//                            rv_restaurant_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//                            rv_restaurant_list.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getContext(), "조회 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Toast.makeText(getContext(), "네트워크 오류", Toast.LENGTH_LONG).show();
            }
        });

    }
}
