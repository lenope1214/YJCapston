package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jmjapp.IntroScreen;
import com.example.jmjapp.JMJApplication;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;
import com.google.android.gms.dynamic.IFragmentWrapper;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReviewListAdapter extends RecyclerView.Adapter<MyReviewListAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Review> mItems;
    private Call<Shop> shopCall;
    private Call<ResponseBody> responseBodyCall;

    public MyReviewListAdapter(Context context, ArrayList<Review> reviews) {
        this.context = context;
        mItems = reviews;
    }

    public MyReviewListAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Review> reviews){
        this.mItems = reviews;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public MyReviewListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_my_review_list, parent, false);
        return new MyReviewListAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MyReviewListAdapter.ItemViewHolder holder, final int position) {
        holder.review_my_content.setText(mItems.get(position).getContent());

        String regDate = mItems.get(position).getRegDate();
        String year = regDate.substring(0,4);
        String month = regDate.substring(5,7);
        String day = regDate.substring(8,10);
        String hour = regDate.substring(11,13);
        String min = regDate.substring(14,16);
        holder.review_my_regDate.setText(month + "월" + day + "일 " + hour + "시" + min +"분");

        Glide.with(context).load("http://3.34.55.186:8088/" + mItems.get(position).getImgUrl()).override(500,500).into(holder.review_my_img);

        holder.review_my_ratingBar.setRating(mItems.get(position).getScore());

        String shopId = mItems.get(position).getShopId();
        shopCall = Server.getInstance().getApi().shop(shopId);
        shopCall.enqueue(new Callback<Shop>() {
            @Override
            public void onResponse(Call<Shop> call, Response<Shop> response) {
                if (response.isSuccessful()) {
                    Log.d("shop 성공", "shop 성공");
                    holder.review_my_shop.setText(response.body().getName());
                } else {
                    Log.d("shop 실패1", "shop 실패1");
                }
            }

            @Override
            public void onFailure(Call<Shop> call, Throwable t) {
                Log.d("shop 실패1", "shop 실패1");
            }
        });

        String reviewId = mItems.get(position).getReviewId();
        SharedPreferences pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        String jwt = pref.getString("token", "");

        holder.review_my_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("알림");
                builder.setMessage("리뷰를 삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        responseBodyCall = Server.getInstance().getApi().deleteReview("Bearer " + jwt, reviewId);
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    Log.d("deleteReview 성공", "deleteReview 성공");
                                    mItems.remove(position);
                                    notifyDataSetChanged();
                                } else {
                                    Log.d("deleteReview 실패1", "deleteReview 실패1");
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("deleteReview 실패2", "deleteReview 실패2");
                            }
                        });
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("result", "아니오");
                    }
                });
                builder.show();
            }
        });

    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems==null? 0:mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView review_my_shop, review_my_regDate, review_my_content;
        ImageView review_my_img;
        RatingBar review_my_ratingBar;
        Button review_my_btn;
        public ItemViewHolder(View itemView) {
            super(itemView);
            review_my_shop = itemView.findViewById(R.id.review_my_shop);
            review_my_regDate = itemView.findViewById(R.id.review_my_regDate);
            review_my_content = itemView.findViewById(R.id.review_my_content);
            review_my_img = itemView.findViewById(R.id.review_my_img);
            review_my_ratingBar = itemView.findViewById(R.id.review_my_ratingBar);
            review_my_btn = itemView.findViewById(R.id.review_my_btn);
        }
    }
}
