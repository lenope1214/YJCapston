package com.example.jmjapp.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.FilterAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MapFragment extends Fragment {
    private FilterAdapter filterAdapter;
    ArrayList<String> mItems = new ArrayList<>();
    private Call<List<Shop>> listShopCall;
    Context context;

    /**********
     * Map이 아니라 검색창Fragment임
     */
    /**********
     * Map이 아니라 검색창Fragment임
     */
    /**********
     * Map이 아니라 검색창Fragment임
     */

    /**********
     * Map이 아니라 검색창Fragment임
     */


    public MapFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.map_fragment, container, false);

        EditText searchBar = rootView.findViewById(R.id.search_bar2);
        RecyclerView search_view_list = rootView.findViewById(R.id.search_list);

        listShopCall = Server.getInstance().getApi().shopList1();
        listShopCall.enqueue(new Callback<List<Shop>>() {
            @Override
            public void onResponse(Call<List<Shop>> call, Response<List<Shop>> response) {
                if (response.isSuccessful()) {
                    Log.d("shopList 성공", "shopList 성공");
                    List<Shop> shopList = response.body();

                    String[] shop_list = new String[shopList.size()];

                    for (int i = 0; i < shopList.size(); i++) {
                        shop_list[i] = shopList.get(i).getName();
                        Log.d("qwe", shop_list[i]);
                    }

                    filterAdapter = new FilterAdapter(context, Arrays.asList(shop_list));

                    search_view_list.setAdapter(filterAdapter);
                    search_view_list.setHasFixedSize(true);
                    search_view_list.setLayoutManager(new LinearLayoutManager(context));


                } else {
                    Log.d("shopList 실패1", "shopList 실패1");
                }
            }

            @Override
            public void onFailure(Call<List<Shop>> call, Throwable t) {
                Log.d("shopList 실패2", "shopList 실패2");
            }
        });

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterAdapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        searchBar.setOnKeyListener((v, i, keyEvent) -> {
            switch (i) {
                case KeyEvent.KEYCODE_ENTER:
                    Intent intent = new Intent(getActivity(), SearchShopList.class);
                    intent.putExtra("shopName", searchBar.getText().toString());
                    Log.d("shopName", searchBar.getText().toString());
                    startActivity(intent);
                    break;
            }
            return true;
        });
        return rootView;
    }
}
