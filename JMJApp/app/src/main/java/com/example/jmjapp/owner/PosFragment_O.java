package com.example.jmjapp.owner;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.Adapter.MenuListRecyclerAdapter;
import com.example.jmjapp.Adapter.TableListAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.dto.Tables;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.user.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PosFragment_O extends Fragment {
    private TableListAdapter adapter;
    private RecyclerView table_list;
    ArrayList<Tables> mItems = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;

    private Call<List<Tables>> listTableCall;
    private Call<Tables> tablesCall;

    private String jwt;
    private FloatingActionButton table_floatingActionButton;

    public PosFragment_O() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.pos_fragment_o, container, false);
        table_floatingActionButton = rootView.findViewById(R.id.table_floatingActionButton);
        table_list = rootView.findViewById(R.id.table_list);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();

        int numberOfColumn = 3;

        table_list.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumn));

        adapter = new TableListAdapter(getContext());
        table_list.setAdapter(adapter);

        Log.d("onStart실행", "onStart 실행");
        mItems.clear();

        SharedPreferences pref = this.getActivity().getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        jwt = pref.getString("token", "");

        //adapter = new TableListAdapter(getContext());

        listTableCall = Server.getInstance().getApi().getTables("Bearer " + jwt, MainActivity_O.shopNumber);
        listTableCall.enqueue(new Callback<List<Tables>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Tables>> call, Response<List<Tables>> response) {
                if (response.isSuccessful()) {
                    Log.d("getTable 성공", "getTable 성공");
                    List<Tables> tablesList = response.body();
                    for(Tables list : tablesList) {
                        mItems.add(new Tables(list.getTabId(), list.getShopId(), list.getNo(),
                                list.getSeatQty(), list.getUsing(), list.getQrCode(), list.getOrderId()));
                        Log.d("list", response.body().toString());
                        table_list.setHasFixedSize(true);
                        adapter.setItems(mItems);
                        table_list.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumn));
                        table_list.setAdapter(adapter);
                    }
                } else {
                    Log.d("getTable 실패1", "getTable 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<Tables>> call, Throwable t) {
                Log.d("getTable 실패2", "getTable 실패2"+t.getCause());
            }
        });

        table_floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listTableCall = Server.getInstance().getApi().getTables("Bearer " + jwt, MainActivity_O.shopNumber);
                listTableCall.enqueue(new Callback<List<Tables>>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<List<Tables>> call, Response<List<Tables>> response) {
                        if (response.isSuccessful()) {
                            Log.d("getTable 성공1", "getTable 성공1");
                            List<Tables> tablesList = response.body();
                            Log.d("tableListsize", String.valueOf(tablesList.size()));
                            int tableNo = tablesList.size() + 1;

                            registerTable(tableNo);
                        } else {
                            Log.d("getTable 실패1", "getTable 실패1"+response.errorBody().string());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Tables>> call, Throwable t) {
                        Log.d("getTable 실패2", "getTable 실패2"+t.getCause());
                    }
                });
            }
        });
    }

    private void registerTable(int tableNo) {
        char isUse = 'N';
        Log.d("tableNo", String.valueOf(tableNo));
        Map<String, String> map = new HashMap<>();
        map.put("shopId", MainActivity_O.shopNumber);
        map.put("no", String.valueOf(tableNo));

        tablesCall = Server.getInstance().getApi().registerTable("Bearer " + jwt, map);
        tablesCall.enqueue(new Callback<Tables>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<Tables> call, Response<Tables> response) {
                if (response.isSuccessful()) {
                    Log.d("테이블 등록 성공", "테이블 등록 성공");
                    adapter.addItem(new Tables(String.valueOf(tableNo), isUse));
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("테이블 등록 실패1", "테이블 등록 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<Tables> call, Throwable t) {
                Log.d("테이블 등록 실패2", "테이블 등록 실패2");
            }
        });
    }
}