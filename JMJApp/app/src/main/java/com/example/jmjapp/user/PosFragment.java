package com.example.jmjapp.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.BasketRecyclerAdapter;
import com.example.jmjapp.Adapter.PosMenuListAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PosFragment extends Fragment {
    Button btn_order,btn_payment;
    TextView tv_tablenum, tv_tableName, qr_menu_price;
    private String shopId, orderId, jwt;
    RecyclerView pos_menu_list;
    private PosMenuListAdapter adapter;
    private ArrayList<OrderMenu> mItems = new ArrayList();
    private Call<List<OrderMenu>> listOrderMenuCall;
    private Call<Order.OrderMenuList> getOrderMenus;
    private Call<Shop> shopCall;
    private Call<Menu> menuCall;
    static public int sum = 0;

    private ConstraintLayout constraintLayout10, constraintLayout20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pos_fragment, container, false);

        constraintLayout10 = rootView.findViewById(R.id.constraintLayout10);
        constraintLayout20 = rootView.findViewById(R.id.constraintLayout20);

//        try {
//            if(!(shopNumber.equals(null))) shopNumber = getArguments().getString("shopNumber");
//            System.out.println(shopNumber+"nnnnnnnnnnnnn");
//        } catch (NullPointerException e) {
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            HomeFragment mf = new HomeFragment();
//            transaction.replace(R.id.MainActivity, mf);
//            transaction.commit();
//        }

        shopId = QrReaderActivity.shopnum2;
        orderId = QrReaderActivity.orderId;

        pos_menu_list = rootView.findViewById(R.id.pos_menu_list);
        tv_tableName = rootView.findViewById(R.id.tv_tableName);
        qr_menu_price = rootView.findViewById(R.id.qr_menu_price);

        SharedPreferences pref = getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        jwt = pref.getString("token", "");

        shopCall = Server.getInstance().getApi().shop(shopId);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.isSuccessful()) {
                    Log.d("shop 조회 성공", "shop 조회 성공");
                    tv_tableName.setText(response.body().getName());
                } else {
                    Log.d("shop 조회 실패1", "shop 조회 실패1");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.d("shop 조회 실패2", "shop 조회 실패2");
            }
        });

//        SharedPreferences pref2 = getActivity().getSharedPreferences("basket", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref2.edit();
//        int list_size = pref2.getInt("list_size",0);
//
//        for(int i = list_size - 1; i >= 0; i--) {
//            mItems.add(new Menu(i, pref2.getString("list_" + i + "_name", "메뉴이름"),
//                    pref2.getInt("list_" + i + "_price", 0)));
//        }
//        pos_menu_list.setHasFixedSize(true);
//        adapter = new PosMenuListAdapter(getContext(), mItems);
//        pos_menu_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//        pos_menu_list.setAdapter(adapter);

        tv_tablenum = rootView.findViewById(R.id.tv_tablenum);
        tv_tablenum.setText("테이블 번호");

        try {
            if (QrReaderActivity.tablenum.equals("null")){
                tv_tablenum.setText("테이블 번호");
            } else {
                tv_tablenum.setText(QrReaderActivity.tablenum+"번 테이블");
            }
        }catch (Exception e) {
            tv_tablenum.setText("테이블 번호");
        }

        btn_order = rootView.findViewById(R.id.btn_order);
        btn_order.setOnClickListener(v -> {
            Toast.makeText(this.getActivity(), "h2", Toast.LENGTH_SHORT).show();
           Intent intent = new Intent(getActivity(), QrMenuActivity.class);
           intent.putExtra("shopId", shopId);
           startActivity(intent);
        });

        btn_payment = rootView.findViewById(R.id.btn_payment);
        btn_payment.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), QrPaymentActivity.class);
            intent.putExtra("shopId", shopId);
            intent.putExtra("orderId", orderId);
            startActivity(intent);
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onStart", "onStart");

        if(orderId == null) {
            constraintLayout10.setVisibility(View.GONE);
            constraintLayout20.setVisibility(View.VISIBLE);
        } else {
            constraintLayout10.setVisibility(View.VISIBLE);
            constraintLayout20.setVisibility(View.GONE);

            mItems.clear();
            sum = 0;
            listOrderMenuCall = Server.getInstance().getApi().order_orderMenu("Bearer " + jwt, orderId);
            listOrderMenuCall.enqueue(new Callback<List<OrderMenu>>() {
                @Override
                public void onResponse(Call<List<OrderMenu>> call, Response<List<OrderMenu>> response) {
                    if (response.isSuccessful()) {
                        Log.d("order_orderMenu 성공3", "order_orderMenu 성공3");
                        List<OrderMenu> orderMenuList = response.body();
                        Log.d("ordermenuist", orderMenuList.toString());

                        for (OrderMenu list : orderMenuList) {
                            mItems.add(new OrderMenu(list.getMenuName(), list.getQuantity()));
                            Log.d("list", list.getMenuName() + list.getQuantity());
                            pos_menu_list.setHasFixedSize(true);
                            adapter = new PosMenuListAdapter(getContext(), mItems);
                            pos_menu_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            pos_menu_list.setAdapter(adapter);

                            sum = sum + list.getMenuPrice();
                        }

//                        for (OrderMenu list : orderMenuList) {
//                            list_menuId[index] = list.getMenuId();
//                            Log.d("listmeni", list_menuId[index]);
//                            index++;
//                        }


//                        if (index != 0) {
//                            for (int i = 0; i < orderMenuList.size(); i++) {
//                                menuCall = Server.getInstance().getApi().menuOne(list_menuId[i]);
//                                menuCall.enqueue(new Callback<Menu>() {
//                                    @SneakyThrows
//                                    @Override
//                                    public void onResponse(Call<Menu> call, Response<Menu> response) {
//                                        if (response.isSuccessful()) {
//                                            Log.d("menuOne 성공", "menuOne 성공");
//                                            sum = sum + response.body().getPrice();
//                                        } else {
//                                            Log.d("menuOne 실패1", "menuOne 실패1" + response.errorBody().string());
//                                        }
//                                    }
//
//                                    @Override
//                                    public void onFailure(Call<Menu> call, Throwable t) {
//                                        Log.d("menuOne 실패2", "menuOne 실패2" + t.getCause());
//                                    }
//                                });
//                            }
//                        }
                        qr_menu_price.setText(String.valueOf(sum) + "원");

                    } else {
                        Log.d("order_orderMenu 실패1", "order_orderMenu 실패1");
                    }
                }

                @Override
                public void onFailure(Call<List<OrderMenu>> call, Throwable t) {
                    Log.d("order_orderMenu 실패2", "order_orderMenu 실패2" + t.getCause());
                }
            });

//        getOrderMenus = Server.getInstance().getApi().orderOneMenu(orderId);
//        getOrderMenus.enqueue(new Callback<Order.OrderMenuList>() {
//            @SneakyThrows
//            @Override
//            public void onResponse(Call<Order.OrderMenuList> call, Response<Order.OrderMenuList> response) {
//                if (response.isSuccessful()) {
//                    Log.d("orderMenu 성공", "orderMenu 성공");
//                    List<OrderMenu> orderMenuList = response.body().getOrderMenuList();
//                    Log.d("listlist", orderMenuList.toString());
//
//                    String[] list_menuId = new String[orderMenuList.size()];
//
//                    int index = 0;
//
//
//
//                } else {
//                    Log.d("orderMenu 실패1", "orderMenu 실패1"+response.errorBody().string());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Order.OrderMenuList> call, Throwable t) {
//                Log.d("orderMenu 실패2", "orderMenu 실패2");
//            }
//        });
        }
    }
}
