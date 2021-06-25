package com.example.jmjapp.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.user.ChatbotListActivity;
import com.example.jmjapp.user.MapActivity;
import com.example.jmjapp.user.ShopDetailActivity;

import java.util.ArrayList;

public class InfoFragment extends Fragment {
    private static String shopNumber;
    View view;
    private RecyclerView.Adapter adapter;
    private RecyclerView rv_restaurant_list;
    private RecyclerView rv_info_list;
    ArrayList mItems = new ArrayList<>();
    TextView shop_detail_intro, shop_detail_time, shop_detail_addr, tv_gomap, tv_gochatbot;

    public static InfoFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_info, container, false);

            shop_detail_intro = view.findViewById(R.id.shop_detail_intro);
            shop_detail_time = view.findViewById(R.id.shop_detail_time);
            shop_detail_addr = view.findViewById(R.id.shop_detail_addr);
            tv_gochatbot = view.findViewById(R.id.tv_gochatbot);

            //지도
            tv_gomap = view.findViewById(R.id.tv_gomap);

            //지도
            tv_gomap = view.findViewById(R.id.tv_gomap);

            shopNumber = ShopDetailActivity.shopNumber;
            Log.d("Daw", shopNumber);
            shop_detail_intro.setText(ShopDetailActivity.shopIntro);
            shop_detail_time.setText(ShopDetailActivity.shopOpen + " ~ " + ShopDetailActivity.shopClose);
            shop_detail_addr.setText(ShopDetailActivity.shopAddress + "\n" + ShopDetailActivity.shopDetailAddress);

            tv_gomap.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), MapActivity.class);
                startActivity(intent);
            });

            tv_gochatbot.setOnClickListener(v -> {
                Intent intent = new Intent(getContext(), ChatbotListActivity.class);
                startActivity(intent);
            });
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
