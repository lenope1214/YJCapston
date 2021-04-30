package com.example.jmjapp.owner;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import com.bumptech.glide.Glide;
import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ShopUpdateActivity extends AppCompatActivity {
    DataService dataService = new DataService();
    private final int GET_GALLERY_IMAGE = 200;
    ImageView shop_update_img;
    TextView shop_update_opentime, shop_update_closetime;
    EditText shop_update_intro;
    Button shop_update_btn, shop_update_cancle_btn;
    int t1Hour, t1Minute, t2Hour, t2Minute;

    private String jwt, shopNumber;
    private String path;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_update);

        Toolbar toolbar = (Toolbar) findViewById(R.id.shop_update_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
        jwt = pref.getString("token", null);

        Intent intent = getIntent();
        shopNumber = intent.getStringExtra("shopNumber");

        shop_update_img = findViewById(R.id.shop_update_img);
        shop_update_opentime = findViewById(R.id.shop_update_opentime);
        shop_update_closetime = findViewById(R.id.shop_update_closetime);
        shop_update_intro = findViewById(R.id.shop_update_intro);
        shop_update_btn = findViewById(R.id.shop_update_btn);
        shop_update_cancle_btn = findViewById(R.id.shop_update_cancle_btn);

        shop_update_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_GALLERY_IMAGE);
            }
        });

        dataService.read.shop(HomeFragment_O.shopNumber).enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.code() == 200) {
                    shop_update_intro.setText(response.body().getIntro());
                    shop_update_opentime.setText(response.body().getOpenTime());
                    shop_update_closetime.setText(response.body().getCloseTime());
                    Glide.with(ShopUpdateActivity.this).load("http://3.34.55.186:8088/" + response.body().getImgPath()).into(shop_update_img);
                } else {
                    Log.d("tv", "연결실패");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.d("tv", "연결실패2");
            }
        });

        shop_update_opentime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ShopUpdateActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @SneakyThrows
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t1Hour = hourOfDay;
                                t1Minute = minute;

                                String time = t1Hour + ":" + t1Minute;
                                Log.d("daw", String.valueOf(t1Minute));


                                time(time, t1Minute, t1Hour, shop_update_opentime);
                            }
                        }, 12, 0, true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t1Hour, t1Minute);
                timePickerDialog.show();
            }
        });

        shop_update_closetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        ShopUpdateActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @SneakyThrows
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                t2Hour = hourOfDay;
                                t2Minute = minute;

                                String time = t2Hour + ":" + t2Minute;
                                time(time, t2Minute, t2Hour, shop_update_closetime);
                            }
                        }, 12, 0, true
                );
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hour, t2Minute);
                timePickerDialog.show();
            }
        });

        shop_update_cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShopUpdateActivity.this);
                builder.setTitle("알림");
                builder.setMessage("변경사항이 저장되지 않습니다. 나가시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

        shop_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("알림");
                builder.setMessage("수정하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> map = new HashMap();
                        map.put("shopId", shopNumber);
                        map.put("intro", shop_update_intro.getText().toString());
                        map.put("openTime", shop_update_opentime.getText().toString());
                        map.put("closeTime", shop_update_closetime.getText().toString());

                        Log.d("adw",shop_update_opentime.getText().toString()+"$"+shop_update_closetime.getText().toString());
                        Log.d("token", shopNumber+jwt);

                        dataService.update.updateShop("Bearer " + jwt, map).enqueue(new Callback<ResponseBody>() {
                            @SneakyThrows
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                Log.d("re","re");
                                if (response.code() == 200) {
                                    Toast.makeText(ShopUpdateActivity.this, "수정되었습니다.", Toast.LENGTH_SHORT).show();
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(ShopUpdateActivity.this);
//                                    builder.setMessage("수정되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            finish();
//                                        }
//                                    }).create();
//                                    builder.setCancelable(false);
//                                    builder.show();
                                } else {
                                    Log.d("tv", "연결실패" + response.errorBody().string());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("tv", "연결실패2");
                            }
                        });
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });
    }

    private void time(String time, int t2Minute, int t2Hour, TextView shop_update_closetime) {
        if (String.valueOf(t2Minute).toString().length() == 1 && String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0"+ t2Hour + ":" + "0"+ t2Minute);
        } else if(String.valueOf(t2Hour).toString().length() == 1) {
            shop_update_closetime.setText("0"+ t2Hour + ":" + t2Minute);
        } else if(String.valueOf(t2Minute).toString().length() == 1) {
            shop_update_closetime.setText(t2Hour + ":" + "0"+ t2Minute);
        } else {
            shop_update_closetime.setText(time);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            shop_update_img.setImageURI(selectedImageUri);

            getPathFromUri(selectedImageUri);
        }
    }

    private void getPathFromUri(Uri selectedImageUri) {
        Cursor cursor = getContentResolver().query(selectedImageUri, null, null, null, null);

        cursor.moveToNext();

        path = cursor.getString(cursor.getColumnIndex("_data"));
        cursor.close();

        updateShopImg(path);
    }

    private void updateShopImg(String path) {
        File file = new File(path);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}