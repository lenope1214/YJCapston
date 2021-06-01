package com.example.jmjapp.owner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.example.jmjapp.Adapter.MenuOptionGroupAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityBellBinding;
import com.example.jmjapp.databinding.ActivityMenuOptionGroupBinding;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.network.Server;

import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuOptionGroupActivity extends AppCompatActivity {
    private ActivityMenuOptionGroupBinding binding;
    private Call<OptionGroups> optionGroupsCall;
    private String menuId;
    static public String optionGroupId, jwt;

    static public Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuOptionGroupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        activity = MenuOptionGroupActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_register_option_group_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        Intent intent = getIntent();
        menuId = intent.getStringExtra("menuId");

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Log.d("jwt",jwt);
        Log.d("menuId", menuId);
        RecyclerView recyclerView = binding.menuOptionGroupList;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        final MenuOptionGroupAdapter adapter = new MenuOptionGroupAdapter();
        recyclerView.setAdapter(adapter);

        binding.menuOptionGroupAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String optionGroupName = binding.menuOptionGroupName.getText().toString();
                String min = binding.menuOptionGroupMinChoose.getText().toString();
                String max = binding.menuOptionGroupMaxChoose.getText().toString();

                adapter.addItem(new OptionGroups(optionGroupName, Integer.parseInt(min), Integer.parseInt(max)));
                adapter.notifyDataSetChanged();
                binding.menuOptionGroupCount.setText(adapter.getItemCount() + "개");

                Map<String, String> map = new HashMap();
                map.put("shopId", MainActivity_O.shopNumber);
                map.put("menuId", menuId);
                map.put("name", binding.menuOptionGroupName.getText().toString());
                map.put("min", binding.menuOptionGroupMinChoose.getText().toString());
                map.put("max", binding.menuOptionGroupMaxChoose.getText().toString());

                optionGroupsCall = Server.getInstance().getApi().registerOptionGroups("Bearer " + jwt, map);
                optionGroupsCall.enqueue(new Callback<OptionGroups>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<OptionGroups> call, Response<OptionGroups> response) {
                        if (response.isSuccessful()) {
                            Log.d("optionGroup 성공", "optionGroup 성공");
                            optionGroupId = response.body().getOptionGroupId();
                            Log.d("optionGroupId", optionGroupId);
                        } else {
                            Log.d("optionGroup 실패1", "optionGroup 실패1"+response.errorBody().string());
                        }
                    }

                    @Override
                    public void onFailure(Call<OptionGroups> call, Throwable t) {
                        Log.d("optionGroup 실패2", "optionGroup 실패2"+t.getCause()+t.getMessage());
                    }
                });
            }
        });

        binding.menuOptionGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuOptionGroupActivity.this, MenuOptionActivity.class);
                intent.putExtra("menuId", menuId);
                intent.putExtra("optionGroupId", optionGroupId);
                startActivity(intent);
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