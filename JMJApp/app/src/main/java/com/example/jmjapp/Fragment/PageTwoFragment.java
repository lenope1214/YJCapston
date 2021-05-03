package com.example.jmjapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.RestaurantRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class PageTwoFragment extends Fragment {
    View view;
    private RestaurantRecyclerAdapter adapter;
    private RecyclerView rv_restaurant_list;
    ArrayList<Shop> mItems = new ArrayList<>();

    private Call<List<Shop>> listShopCall;

    public static PageTwoFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        PageTwoFragment fragment = new PageTwoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_page_one, container, false);

            rv_restaurant_list = (RecyclerView) view.findViewById(R.id.rv_restaurant_list);
            adapter = new RestaurantRecyclerAdapter(getContext());
            showList("일식");
        }


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void showList(String category) {
        listShopCall = Server.getInstance().getApi().shopList2(category);
        listShopCall.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if (response.isSuccessful()) {
                    if (response.code() == 200) {
                        List<Shop> shopList = response.body();
                        for (Shop list : shopList) {
                            Log.e("result : ", list.getCategory());
                            mItems.add(new Shop(list.getShopId(), list.getName(), list.getIntro(),
                                    list.getCloseTime(), list.getOpenTime(),
                                    list.getAddress(), list.getAddressDetail(), list.getIsRsPos(),
                                    list.getCategory(), list.getIsOpen(), list.getImgPath()));
                            rv_restaurant_list.setHasFixedSize(true);
                            adapter.setItems(mItems);
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

            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (listShopCall != null)
            listShopCall.cancel();
    }
}
