package com.example.jmjapp.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.jmjapp.R;

public class PosFragment extends Fragment {
    Button btn_order,btn_payment;
    TextView tv_tablenum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pos_fragment, container, false);

//        try {
//            if(!(shopNumber.equals(null))) shopNumber = getArguments().getString("shopNumber");
//            System.out.println(shopNumber+"nnnnnnnnnnnnn");
//        } catch (NullPointerException e) {
//            FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
//            HomeFragment mf = new HomeFragment();
//            transaction.replace(R.id.MainActivity, mf);
//            transaction.commit();
//        }


        tv_tablenum = rootView.findViewById(R.id.tv_tablenum);
        tv_tablenum.setText("테이블 번호");

        try {
            if (ShopDetailActivity.tableNumber.equals("null")){
                tv_tablenum.setText("테이블 번호");
            } else {
                tv_tablenum.setText(ShopDetailActivity.tableNumber+"번 테이블");
            }
        }catch (Exception e) {
            tv_tablenum.setText("테이블 번호");
        }


        btn_order = rootView.findViewById(R.id.btn_order);
        btn_order.setOnClickListener(v -> {
           Intent intent = new Intent(getActivity(), ShopDetailActivity.class);
           intent.putExtra("shopNumber", ShopDetailActivity.shopNumber);
           startActivity(intent);
        });

        btn_payment = rootView.findViewById(R.id.btn_payment);
        btn_payment.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), OrderActivity.class);
            startActivity(intent);
        });

        return rootView;
    }

}
