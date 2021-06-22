package com.example.jmjapp.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.AlarmListRecyclerAdapter;
import com.example.jmjapp.Adapter.MyOrderListAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ZzimFragmentBinding;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZzimFragment extends Fragment {
    private MyOrderListAdapter adapter;
    private RecyclerView rs_order_list;
    private ArrayList<Order> mItems = new ArrayList();

    private String jwt;
    private Call<List<Order>> listOrderCall;

    MapFragment mapFragment;

    FragmentManager manager;

    public ZzimFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.zzim_fragment, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        jwt = pref.getString("token", "");

        rs_order_list = rootView.findViewById(R.id.rs_order_list);

        mapFragment = new MapFragment();
        manager = getFragmentManager();

        ImageView home_search_bar = rootView.findViewById(R.id.zzim_search_bar);
        home_search_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.bottomNavigation.setSelectedItemId(R.id.tab2);
                manager.beginTransaction().replace(R.id.container, mapFragment).commit();
            }
        });
        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "onStart 실행");
        mItems.clear();
        listOrderCall = Server.getInstance().getApi().myOrder("Bearer " + jwt);
        listOrderCall.enqueue(new Callback<List<Order>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    Log.d("myOrder 성공", "myOrder 성공");
                    List<Order> orderList = response.body();
                    Log.d("or", String.valueOf(orderList));
                    for(Order list : orderList) {
                        if (list.getPayTime() != null && !(list.getStatus().equals("rf"))) {
                            mItems.add(new Order(list.getOrderId(), list.getStatus(), list.getOrderRequest(),
                                    list.getPeople(), list.getUsePoint(), list.getAmount(),
                                    list.getArriveTime(), list.getPayTime(), list.getPg(),
                                    list.getPayMethod(), list.getShopId(), list.getShopName(), list.getUserName(),
                                    list.getReason(), list.getReviewed(), list.getUserId(), list.getAccept(), list.getCompleAmount()));
                            rs_order_list.setHasFixedSize(true);
                            adapter = new MyOrderListAdapter(getActivity(), mItems, jwt);
                            rs_order_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rs_order_list.setAdapter(adapter);
                        }
                    }
                } else {
                    Log.d("myOrder 실패1", "myOrder 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("myOrder 실패2", "myOrder 실패2"+t.getCause());
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume", "onResume 실행");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("onDetach", "onDetach 실행");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy", "onDestroy 실행");
    }


    @Override
    public void onStop() {
        super.onStop();
        Log.d("onStop", "onStop 실행");
    }

}
