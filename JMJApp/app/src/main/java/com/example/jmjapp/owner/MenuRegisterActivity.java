package com.example.jmjapp.owner;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.network.Server;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MenuRegisterActivity extends AppCompatActivity {
    Context context;

    EditText menu_name_et, menu_intro_et, menu_price_et, menu_time_et;
    Button menu_register_btn;
    private ImageView menu_register_img;
    private final int GET_GALLERY_IMAGE = 200;
    private AlertDialog dialog;
    private String shopNumber;

    private String path, jwt;
    private Uri selectedImageUri;
    private Call<Menu> menuCall;

    static public Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

        activity = MenuRegisterActivity.this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.menu_register_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", null);

        Intent intent = getIntent();
        shopNumber = intent.getStringExtra("shopNumber");
        Log.d("awd2222222",shopNumber);

        menu_name_et = findViewById(R.id.menu_name_et);
        menu_intro_et = findViewById(R.id.menu_intro_et);
        menu_price_et = findViewById(R.id.menu_price_et);
        menu_time_et = findViewById(R.id.menu_time_et);
        menu_register_btn = findViewById(R.id.menu_register_btn);
        menu_register_img = findViewById(R.id.menu_register_img);

        menu_register_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            menu_register_img.setImageURI(selectedImageUri);

            getPathFromUri(selectedImageUri);
        }
    }

    private void getPathFromUri(Uri selectedImageUri) {
        Cursor cursor = getContentResolver().query(selectedImageUri, null, null, null, null);

        cursor.moveToNext();

        path = cursor.getString(cursor.getColumnIndex("_data"));
        cursor.close();
        
        registerMenu(path);
    }

    private void registerMenu(String path) {
        Log.d("rr","rr");
        File file = new File(path);
        menu_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestBody idBody = RequestBody.create(MediaType.parse("text/plain"), shopNumber);
                RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), menu_name_et.getText().toString());
                RequestBody introBody = RequestBody.create(MediaType.parse("text/plain"), menu_intro_et.getText().toString());
                RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), menu_price_et.getText().toString());
                RequestBody timeBody = RequestBody.create(MediaType.parse("text/plain"), menu_time_et.getText().toString());
                RequestBody imgBody = RequestBody.create(MediaType.parse("image/jpeg"), file);

                MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), imgBody);

                Map<String, RequestBody> map = new HashMap();
                map.put("shopId", idBody);
                map.put("name", nameBody);
                map.put("intro", introBody);
                map.put("price", priceBody);
                map.put("duration", timeBody);

                Log.d("awd",String.valueOf(idBody)+nameBody+introBody+priceBody+timeBody);

                menuCall = Server.getInstance().getApi().insertMenu("Bearer " + jwt, map, body);
                menuCall.enqueue(new Callback<Menu>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<Menu> call, Response<Menu> response) {
                        if(response.code() == 201) {
                            finish();
//                            String menuId = response.body().getMenuId();
//
//                            Intent intent = new Intent(MenuRegisterActivity.this, MenuOptionGroupActivity.class);
//                            intent.putExtra("menuId", menuId);
//                            intent.putExtra("shopNumber", shopNumber);
//                            intent.putExtra("menuName", menu_name_et.getText().toString());
//                            intent.putExtra("menuIntro", menu_intro_et.getText().toString());
//                            intent.putExtra("menuPrice", menu_price_et.getText().toString());
//                            intent.putExtra("menuTime", menu_time_et.getText().toString());
//                            intent.putExtra("imgPath", file);
//                            startActivity(intent);

                            Log.d("result : " , "메뉴등록 성공");

                        } else if(response.code() == 400) {
                            Log.d("adw",response.errorBody().string());
                            Log.d("result : ", "메뉴등록 실패");
                        } else {
                            Log.d("adw",response.errorBody().string());
                            Log.d("result : " , "연결실패");
                        }
                    }

                    @SneakyThrows
                    @Override
                    public void onFailure(Call<Menu> call, Throwable t) { ;
                        Log.d("result : " , "연결실패2");
                    }
                });
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
        if(menuCall!=null)
            menuCall.cancel();
    }
}