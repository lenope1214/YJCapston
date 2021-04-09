package com.example.jmjapplication2.owner;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.Adapter.MenuListRecyclerAdapter;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Menu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity {
    FloatingActionButton menu_register;
    private String shopNumber;
    DataService dataService = new DataService();
    private MenuListRecyclerAdapter adapter;
    private RecyclerView menu_list;
    ArrayList<Menu> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        Intent intent = getIntent();
        shopNumber = intent.getStringExtra("shopNumber");
        //Log.d("awd",shopNumber);

        menu_register = findViewById(R.id.menu_register);
        menu_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuDetailActivity.this, MenuRegisterActivity.class);
                intent.putExtra("shopNumber", shopNumber);
                startActivity(intent);
            }
        });

        menu_list = findViewById(R.id.menu_list);


        //adapter.notifyDataSetChanged();


    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new MenuListRecyclerAdapter(getApplicationContext());
        dataService.read.menuList(shopNumber).enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        List<Menu> menuList = response.body();
                        mItems.clear();
                        for(Menu list : menuList) {
                            mItems.add(new Menu(list.getId(), list.getName(), list.getIntro(),
                                    list.getIs_sale(), list.getIs_popular(),list.getPrice(),
                                    list.getDuration(), list.getImg_url()));
                            menu_list.setHasFixedSize(true);
                            adapter.setItems(mItems);
                            menu_list.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            menu_list.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "조회 실패", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {

            }
        });
    }

}