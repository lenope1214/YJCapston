package com.example.jmjapp.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.Adapter.MenuRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.user.ShopDetailActivity;
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

    private Call<List<Menu>> listMenuCall;

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
        listMenuCall = Server.getInstance().getApi().menuList(shopNumber);
        listMenuCall.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        Log.d("성공","성공");
                        List<Menu> menuList = response.body();
                        for(Menu list : menuList) {
                            mItems.add(new Menu(list.getMenuId(), list.getName(), list.getIntro(),
                                    list.getIsSale(), list.getIsPopular(),list.getPrice(),
                                    list.getDuration(), list.getImgPath()));
                            Log.d("list", response.body().toString());
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

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(listMenuCall!=null)
            listMenuCall.cancel();
    }
}
