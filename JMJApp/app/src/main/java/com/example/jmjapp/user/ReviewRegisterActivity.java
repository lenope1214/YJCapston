package com.example.jmjapp.user;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityReviewRegisterBinding;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.owner.AddEmployeesActivity;
import com.example.jmjapp.owner.EmployeesManagementActivity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewRegisterActivity extends AppCompatActivity {
    private ActivityReviewRegisterBinding binding;
    private Call<Review> reviewCall;
    private String jwt, shopId, orderId, path;
    private final int GET_GALLERY_IMAGE = 200;
    private Uri selectedImageUri;
    private AlertDialog dialog;
    private RatingBar ratingBarr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReviewRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = (Toolbar) findViewById(R.id.review_register_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.arrowback);

        SharedPreferences pref = getSharedPreferences("auth", MODE_PRIVATE);
        jwt = pref.getString("token", "");

        Intent intent = getIntent();
        shopId = intent.getStringExtra("shopId");
        orderId = intent.getStringExtra("orderId");

        ratingBarr = findViewById(R.id.review_ratingBar);

        ratingBarr.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (ratingBarr.getRating() < 1.0f) {
                ratingBarr.setRating(1);
            }
        });

        binding.reviewRegisterImg.setOnClickListener(v -> {
            Intent intent1 = new Intent(Intent.ACTION_PICK);
            intent1.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(intent1, GET_GALLERY_IMAGE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {

            selectedImageUri = data.getData();
            binding.reviewRegisterImg.setImageURI(selectedImageUri);

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
        binding.reviewRegisterBtn.setOnClickListener(v -> {
            int rating = Math.round(ratingBarr.getRating());
            Log.d("RA", String.valueOf(rating));

            RequestBody shopIdBody = RequestBody.create(MediaType.parse("text/plain"), shopId);
            RequestBody orderIdBody = RequestBody.create(MediaType.parse("text/plain"), orderId);
            RequestBody contentBody = RequestBody.create(MediaType.parse("text/plain"), binding.reviewContent.getText().toString());
            RequestBody scoreBody = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(rating));
            RequestBody imgBody = RequestBody.create(MediaType.parse("image/jpeg"), file);

            MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), imgBody);

            Map<String, RequestBody> map = new HashMap();
            map.put("shopId", shopIdBody);
            map.put("orderId", orderIdBody);
            map.put("content", contentBody);
            map.put("score", scoreBody);

            Log.d("register", shopId + orderId + binding.reviewContent.getText().toString() + ratingBarr.getRating());

            reviewCall = Server.getInstance().getApi().review("Bearer " + jwt, map, body);
            reviewCall.enqueue(new Callback<Review>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<Review> call, Response<Review> response) {
                    if (response.isSuccessful()) {
                        Log.d("review 등록성공", "review 등록성공");
                        AlertDialog.Builder builder = new AlertDialog.Builder(ReviewRegisterActivity.this);
                        dialog = builder.setMessage("리뷰가 등록되었습니다.").setPositiveButton("확인", (dialog, which) -> finish()).create();
                        builder.setCancelable(false);
                        dialog.show();
                    } else {
                        Log.d("review 등록실패1", "review 등록실패1" + response.errorBody().string());
                    }
                }

                @Override
                public void onFailure(Call<Review> call, Throwable t) {
                    Log.d("review 등록실패2", "review 등록실패2" + t.getCause());
                }
            });
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}