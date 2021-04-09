package com.example.jmjapplication2.owner;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jmjapplication2.R;
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
    DataService dataService = new DataService();
    EditText menu_name_et, menu_intro_et, menu_price_et, menu_time_et;
    Button menu_register_btn;
    private ImageView menu_register_img;
    private final int GET_GALLERY_IMAGE = 200;
    private AlertDialog dialog;
    private String shopNumber;

    private String path;
    private Uri selectedImageUri;

    boolean isPhotoCaptured;
    boolean isPhotoFileSaved;
    boolean isPhotoCanceled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_register);

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


//        menu_register_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                RequestBody idBody = RequestBody.create(MediaType.parse("text.plain"), shopNumber);
//                RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), menu_name_et.getText().toString());
//                RequestBody introBody = RequestBody.create(MediaType.parse("text/plain"), menu_intro_et.getText().toString());
//                RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), menu_price_et.getText().toString());
//                RequestBody timeBody = RequestBody.create(MediaType.parse("text/plain"), menu_time_et.getText().toString());
//                RequestBody imgBody = RequestBody.create(MediaType.parse("image/*"), menu_register_img);
//
//                Map<String, RequestBody> map = new HashMap();
//                map.put("shopId", idBody);
//                map.put("name", nameBody);
//                map.put("intro", introBody);
//                map.put("price", priceBody);
//                map.put("duration", timeBody);
//                map.put("img", imgBody);
//
//                dataService.create.insertMenu(map).enqueue(new Callback<ResponseBody>() {
//                    @SneakyThrows
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if(response.code() == 201) {
//                            Log.d("result : " , "메뉴등록 성공");
//                            AlertDialog.Builder builder = new AlertDialog.Builder(MenuRegisterActivity.this);
//                            dialog = builder.setMessage("메뉴가 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    finish();
//                                }
//                            }).create();
//                            builder.setCancelable(false);
//                            dialog.show();
//                        } else if(response.code() == 400) {
//                            Log.d("adw",response.errorBody().string());
//                            Log.d("result : ", "메뉴등록 실패");
//                        } else {
//                            Log.d("result : " , "연결실패");
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Log.d("result : " , "연결실패2");
//                    }
//                });
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            Log.d("uri@@@@", String.valueOf(selectedImageUri));
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
        File file = new File(path);

        menu_register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultipartBody.Part body;

                RequestBody idBody = RequestBody.create(MediaType.parse("text.plain"), shopNumber);
                RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), menu_name_et.getText().toString());
                RequestBody introBody = RequestBody.create(MediaType.parse("text/plain"), menu_intro_et.getText().toString());
                RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), menu_price_et.getText().toString());
                RequestBody timeBody = RequestBody.create(MediaType.parse("text/plain"), menu_time_et.getText().toString());
                RequestBody imgBody = RequestBody.create(MediaType.parse("image/*"), file);

                body = MultipartBody.Part.createFormData("fileName", file.getName(), imgBody);

                Map<String, RequestBody> map = new HashMap();
                map.put("shopId", idBody);
                map.put("name", nameBody);
                map.put("intro", introBody);
                map.put("price", priceBody);
                map.put("duration", timeBody);

                dataService.create.insertMenu(map, body).enqueue(new Callback<ResponseBody>() {
                    @SneakyThrows
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.code() == 201) {
                            Log.d("result : " , "메뉴등록 성공");
                            AlertDialog.Builder builder = new AlertDialog.Builder(MenuRegisterActivity.this);
                            dialog = builder.setMessage("메뉴가 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    finish();
                                }
                            }).create();
                            builder.setCancelable(false);
                            dialog.show();
                        } else if(response.code() == 400) {
                            Log.d("adw",response.errorBody().string());
                            Log.d("result : ", "메뉴등록 실패");
                        } else {
                            Log.d("result : " , "연결실패");
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("result : " , "연결실패2");
                    }
                });
            }
        });
    }


}