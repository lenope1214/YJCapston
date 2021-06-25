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
    Button btn_order, btn_payment, btn_chatbot;
    TextView tv_tablenum, tv_tableName, qr_menu_price;
    private String shopId, jwt, shopName;
    RecyclerView pos_menu_list;
    private PosMenuListAdapter adapter;
    private ArrayList<OrderMenu> mItems = new ArrayList();
    private Call<List<OrderMenu>> listOrderMenuCall;
    private Call<Order.OrderMenuList> getOrderMenus;
    private Call<Shop> shopCall;
    private Call<Menu> menuCall;
    static public int sum = 0;
    static public String orderId;
    private ConstraintLayout constraintLayout10, constraintLayout20;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pos_fragment, container, false);

        constraintLayout10 = rootView.findViewById(R.id.constraintLayout10);
        constraintLayout20 = rootView.findViewById(R.id.constraintLayout20);

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
                    shopName = response.body().getName();
                } else {
                    Log.d("shop 조회 실패1", "shop 조회 실패1");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.d("shop 조회 실패2", "shop 조회 실패2");
            }
        });

        tv_tablenum = rootView.findViewById(R.id.tv_tablenum);
        tv_tablenum.setText("테이블 번호");

        try {
            if (QrReaderActivity.tablenum.equals("null")) {
                tv_tablenum.setText("테이블 번호");
            } else {
                tv_tablenum.setText(QrReaderActivity.tablenum + "번 테이블");
            }
        } catch (Exception e) {
            tv_tablenum.setText("테이블 번호");
        }

        btn_order = rootView.findViewById(R.id.btn_order);
        btn_order.setOnClickListener(v -> {
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

        btn_chatbot = rootView.findViewById(R.id.btn_chatbot);
        btn_chatbot.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChatbotListActivity.class);
            intent.putExtra("shopId", shopId);
            intent.putExtra("shopName", shopName);
            intent.putExtra("qr", "qr");
            startActivity(intent);
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart", "onStart");

        if (orderId == null) {
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

                        for (OrderMenu list : orderMenuList) {
                            mItems.add(new OrderMenu(list.getMenuName(), list.getQuantity()));
                            Log.d("list", list.getMenuName() + list.getQuantity());
                            pos_menu_list.setHasFixedSize(true);
                            adapter = new PosMenuListAdapter(getContext(), mItems);
                            pos_menu_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            pos_menu_list.setAdapter(adapter);

                            sum = sum + (list.getMenuPrice() * Integer.parseInt(list.getQuantity()));
                        }
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
        }
    }
}
