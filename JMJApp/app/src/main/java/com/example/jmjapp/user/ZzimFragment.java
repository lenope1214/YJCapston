package com.example.jmjapp.user;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ZzimFragment extends Fragment {
    private MyOrderListAdapter adapter;
    private RecyclerView rs_order_list;
    private ArrayList<Order> mItems = new ArrayList();

    private String user_id;
    private Call<List<Order>> listOrderCall;

    public ZzimFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        user_id = pref.getString("token", "");

        rs_order_list = rootView.findViewById(R.id.rs_order_list);

        listOrderCall = Server.getInstance().getApi().myOrder("Bearer " + user_id);
        listOrderCall.enqueue(new Callback<List<Order>>() {
            @Override
            public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                if (response.isSuccessful()) {
                    Log.d("myOrder 성공", "myOrder 성공");
                    List<Order> orderList = response.body();
                    for(Order list : orderList) {
                        mItems.add(new Order(list.getOrderId(), list.getStatus(), list.getOrderRequest(),
                                list.getPeople(), list.getUsePoint(), list.getAmount(),
                                list.getArriveTime(), list.getPayTime(), list.getPg(),
                                list.getPayMethod(), list.getShopId(), list.getShopName(), list.getUserName(),
                                list.getReason(), list.getReviewed(), list.getUserId(), list.getAccept()));
                        Log.d("accept", String.valueOf(list.getAccept()));
                        rs_order_list.setHasFixedSize(true);
                        adapter = new MyOrderListAdapter(getActivity(), mItems);
                        rs_order_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                        rs_order_list.setAdapter(adapter);
                    }
                } else {
                    Log.d("myOrder 실패1", "myOrder 실패1");
                }
            }

            @Override
            public void onFailure(Call<List<Order>> call, Throwable t) {
                Log.d("myOrder 실패2", "myOrder 실패2");
            }
        });

        return rootView;
    }

}
