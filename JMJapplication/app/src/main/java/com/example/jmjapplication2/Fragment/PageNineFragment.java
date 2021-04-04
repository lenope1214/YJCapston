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

public class PageNineFragment extends Fragment {
    View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_restaurant_list;
    ArrayList<Shop> mItems = new ArrayList<>();

    public static PageNineFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        PageNineFragment fragment = new PageNineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_page_one, container, false);

            rv_restaurant_list = (RecyclerView)view.findViewById(R.id.rv_restaurant_list);
            showList("카페.디저트");

        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void showList(String category) {
        Retrofit retrofit =new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Shop>> shopCall = apiService.shopList(category);
        shopCall.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        List<Shop> shopList = response.body();
                        for(Shop list : shopList) {
                            Log.e("result : ", list.getCategory());
                            mItems.add(new Shop(list.getId(), list.getName(), list.getIntro(), list.getCloseTime(), list.getOpenTime(), list.getAddressDetail(), list.getAddress(), list.getIsRsPos(), list.getCategory(), list.getIsOpen()));
                            rv_restaurant_list.setHasFixedSize(true);
                            adapter = new RestaurantRecyclerAdapter(getContext(), mItems);
                            rv_restaurant_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_restaurant_list.setAdapter(adapter);
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
