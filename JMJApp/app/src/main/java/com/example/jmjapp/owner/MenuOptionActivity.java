package com.example.jmjapp.owner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.jmjapp.Adapter.MenuOptionAdapter;
import com.example.jmjapp.Adapter.MyOrderListAdapter;
import com.example.jmjapp.Adapter.RestaurantRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityMenuOptionBinding;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.dto.Options;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.user.OrderActivity;
import com.example.jmjapp.user.ShopDetailActivity;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuOptionActivity extends AppCompatActivity {
    private ActivityMenuOptionBinding binding;
    private MenuOptionAdapter adapter;
    private RecyclerView menu_option_list;
    ArrayList<OptionGroups> mItems = new ArrayList<>();

    private Call<List<OptionGroups>> optionGroupsCall;

    private String menuId, optionGroupId, jwt;
    private AlertDialog dialog;
    static public Activity activity;
    MenuRegisterActivity mr;
    MenuOptionGroupActivity mog;
    MenuOptionActivity mo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuOptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = MenuOptionActivity.this;

        mr = (MenuRegisterActivity) MenuRegisterActivity.activity;
        mog = (MenuOptionGroupActivity) MenuOptionGroupActivity.activity;
        mo = (MenuOptionActivity) MenuOptionActivity.activity;

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_register_option_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Intent intent = getIntent();
        menuId = intent.getStringExtra("menuId");
        optionGroupId = intent.getStringExtra("optionGroupId");
        Log.d("qweasdzxc", jwt+menuId+optionGroupId);

        menu_option_list = binding.menuOptionList;

        optionGroupsCall = Server.getInstance().getApi().optionGroupsList("Bearer " + jwt, menuId);
        optionGroupsCall.enqueue(new Callback <List<OptionGroups>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<OptionGroups>> call, Response<List<OptionGroups>> response) {
                if (response.isSuccessful()) {
                    Log.d("OptionGroupsList 성공", "OptionGroupsList 성공");
                    Log.d("qwe", response.body().toString());
                    List<OptionGroups> optionGroupsList = response.body();
                    for (OptionGroups list : optionGroupsList) {
                        mItems.add(new OptionGroups(list.getOptionGroupId(), list.getOgName(),
                                list.getOgMin(), list.getOgMax(), list.getMenuId()));
                        menu_option_list.setHasFixedSize(true);
                        adapter = new MenuOptionAdapter(getApplicationContext(), mItems);
                        menu_option_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                        menu_option_list.setAdapter(adapter);
                    }
                } else {
                    Log.d("OptionGroupsList 실패1", "OptionGroupsList 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<OptionGroups>> call, Throwable t) {
                Log.d("OptionGroupsList 실패2", "OptionGroupsList 실패2"+t.getCause()+t.getMessage());
            }
        });

        
        binding.menuOptionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MenuOptionActivity.this);
                dialog = builder.setMessage("메뉴가 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mo.finish();
                        mog.finish();
                        mr.finish();
                    }
                }).create();
                builder.setCancelable(false);
                dialog.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(optionGroupsCall!=null)
            optionGroupsCall.cancel();
    }
}