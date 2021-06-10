package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.jmjapp.Adapter.MenuListRecyclerAdapter;
import com.example.jmjapp.Adapter.MenuMoveOptionAdapter;
import com.example.jmjapp.Adapter.MenuOptionAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityBellBinding;
import com.example.jmjapp.databinding.ActivityMenuMoveDetailBinding;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuMoveDetailActivity extends AppCompatActivity {
    private ActivityMenuMoveDetailBinding binding;
    private RecyclerView menu_move_option_list;
    private MenuMoveOptionAdapter adapter;
    ArrayList<OptionGroups> mItems = new ArrayList<>();
    private String menuId, imgPath, menuName, jwt;
    private int menuPrice;

    private Call<List<OptionGroups>> optionGroupsCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuMoveDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_move_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Intent intent = getIntent();
        menuId = intent.getStringExtra("menuId");
        menuName = intent.getStringExtra("menuName");
        menuPrice = intent.getIntExtra("menuPrice", 0);
        imgPath = intent.getStringExtra("imgPath");

        ImageView img = findViewById(R.id.menu_move_img);

        binding.menuMoveName.setText(menuName);
        binding.menuMovePrice.setText(menuPrice+"원");
        Glide.with(this).load("http://3.34.55.186:8088/" + imgPath).override(500,500).into(img);
        //Glide.with(this).load("http://3.34.55.186:8088/" + menuImage).override(500,500).into(menu_choosed_img);

        menu_move_option_list = binding.menuMoveOptionList;

        optionGroupsCall = Server.getInstance().getApi().optionGroupsList("Bearer " + jwt, menuId);
        optionGroupsCall.enqueue(new Callback<List<OptionGroups>>() {
            @Override
            public void onResponse(Call<List<OptionGroups>> call, Response<List<OptionGroups>> response) {
                if (response.isSuccessful()) {
                    Log.d("OptionGroupsList 성공", "OptionGroupsList 성공");
                    List<OptionGroups> optionGroupsList = response.body();
                    for (OptionGroups list : optionGroupsList) {
                        mItems.add(new OptionGroups(list.getOptionGroupId(), list.getOgName(),
                                list.getOgMin(), list.getOgMax(), list.getMenuId()));
                        menu_move_option_list.setHasFixedSize(true);
                        adapter = new MenuMoveOptionAdapter(getApplicationContext(), mItems);
                        menu_move_option_list.setLayoutManager(new LinearLayoutManager(getApplication()));
                        menu_move_option_list.setAdapter(adapter);
                    }
                } else {
                    Log.d("OptionGroupsList 실패1", "OptionGroupsList 실패1");
                }
            }

            @Override
            public void onFailure(Call<List<OptionGroups>> call, Throwable t) {
                Log.d("OptionGroupsList 실패2", "OptionGroupsList 실패2");
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
}