package com.example.jmjapplication2.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.Adapter.MenuRecyclerAdapter;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.user.ShopDetailActivity;
import com.example.jmjapplication2.dto.Menu;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends Fragment {
    private static String shopNumber;
    View view;
    private MenuRecyclerAdapter adapter;
    private RecyclerView rv_menu_list;
    private ArrayList<Menu> mItems = new ArrayList<>();

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_menu, container, false);
            rv_menu_list = (RecyclerView) view.findViewById(R.id.rv_menu_list);
            adapter = new MenuRecyclerAdapter(getContext());
            shopNumber = ShopDetailActivity.shopNumber;
            showMenuList();
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void showMenuList() {
        Retrofit retrofit =new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create())
                .baseUrl(ApiService.BASEURL)
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<Menu>> shopCall = apiService.menuList(shopNumber);
        shopCall.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        Log.d("성공","성공");
                        List<Menu> menuList = response.body();
                        for(Menu list : menuList) {
                            mItems.add(new Menu(list.getId(), list.getName(), list.getIntro(),
                                    list.getIs_sale(), list.getIs_popular(),list.getPrice(),
                                    list.getDuration(), list.getImg_url()));
                            rv_menu_list.setHasFixedSize(true);
                            adapter.setItems(mItems);
                            rv_menu_list.setLayoutManager(new LinearLayoutManager(getActivity()));
                            rv_menu_list.setAdapter(adapter);
                        }
                    } else {
                        Log.d("ㅅㅍ","ㅅㅍ");
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.d("성공ㅅㅍ","성공ㅅㅍ");
            }
        });
    }
}
