package com.example.jmjapp.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.Adapter.MenuListRecyclerAdapter;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.network.Server;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MenuDetailActivity extends AppCompatActivity {
    TextView menu_register;
    public static String shopNumber;
    private MenuListRecyclerAdapter adapter;
    private RecyclerView menu_list;
    ArrayList<Menu> mItems = new ArrayList<>();

    private Call<List<Menu>> listMenuCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.menuList_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

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

        listMenuCall = Server.getInstance().getApi().menuList(shopNumber);
        listMenuCall.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                if(response.isSuccessful()) {
                    if(response.code() == 200) {
                        List<Menu> menuList = response.body();
                        mItems.clear();
                        for(Menu list : menuList) {
                            mItems.add(new Menu(list.getMenuId(), list.getName(), list.getIntro(),
                                    list.getIsSale(), list.getIsPopular(),list.getPrice(),
                                    list.getDuration(), list.getImgPath()));
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(listMenuCall!=null)
            listMenuCall.cancel();
    }

}