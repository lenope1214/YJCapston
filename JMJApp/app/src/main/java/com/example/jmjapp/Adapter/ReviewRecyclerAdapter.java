package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Review;
import com.example.jmjapp.user.ReviewDetailActivity;

import java.util.ArrayList;

public class ReviewRecyclerAdapter extends RecyclerView.Adapter<ReviewRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Review> mItems;

    public ReviewRecyclerAdapter(Context context, ArrayList<Review> reviews) {
        this.context = context;
        mItems = reviews;
    }

    public ReviewRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Review> reviews){
        this.mItems = reviews;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ReviewRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_review_list, parent, false);
        return new ReviewRecyclerAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ReviewRecyclerAdapter.ItemViewHolder holder, final int position) {
        holder.review_userId.setText(mItems.get(position).getUserId()+"님 >>");
        holder.review_content.setText(mItems.get(position).getContent());
        Glide.with(context).load("http://3.34.55.186:8088/" + mItems.get(position).getImgUrl()).override(700,500).into(holder.review_img);
        String regDate = mItems.get(position).getRegDate();
        String year = regDate.substring(0,4);
        String month = regDate.substring(5,7);
        String day = regDate.substring(8,10);
        //Log.d("imgurlqwe", mItems.get(position).getImgUrl());
        String hour = regDate.substring(11,13);
        String min = regDate.substring(14,16);
        holder.review_regDate.setText(month + "월" + day + "일 " + hour + "시" + min +"분");

        holder.review_userId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReviewDetailActivity.class);
                intent.putExtra("userId", mItems.get(position).getUserId());
                intent.putExtra("shopId", mItems.get(position).getShopId());
                intent.putExtra("score", mItems.get(position).getScore());
                intent.putExtra("content", mItems.get(position).getContent());
                intent.putExtra("imgUrl", mItems.get(position).getImgUrl());
                intent.putExtra("regDate", mItems.get(position).getRegDate());
                context.startActivity(intent);
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
        TextView review_userId, review_regDate, review_content;
        ImageView review_img;
        public ItemViewHolder(View itemView) {
            super(itemView);
            review_userId = itemView.findViewById(R.id.review_userId);
            review_regDate = itemView.findViewById(R.id.review_regDate);
            review_content = itemView.findViewById(R.id.review_content);
            review_img = itemView.findViewById(R.id.review_img);
        }
    }
}
