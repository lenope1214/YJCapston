package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.user.ShopDetailActivity;

import java.util.ArrayList;

public class RestaurantRecyclerAdapter extends RecyclerView.Adapter<RestaurantRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Shop> mItems;

    public RestaurantRecyclerAdapter(Context context, ArrayList<Shop> shopss) {
        this.context = context;
        mItems = shopss;
    }

    public RestaurantRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Shop> shopss){
        this.mItems = shopss;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item_view, parent, false);
        return new ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_restaurant_name.setText(mItems.get(position).getName());
        holder.tv_restaurant_menu.setText(mItems.get(position).getAddress());
        Glide.with(context).load("http://3.34.55.186:8088/" + mItems.get(position).getImgPath()).override(500,500).into(holder.riv_restaurant_img);

        if(mItems.get(position).getIsOpen() == 'Y') {
            holder.tv_status.setText("영업 중");
        } else {
            holder.tv_status.setText("영업아님");
        }

        holder.layout_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopDetailActivity.class);
                intent.putExtra("shopNumber", mItems.get(position).getId());
                //Log.d("result : ", shops.get(position).getId());
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
    // item layout에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout layout_restaurant;
        private ImageView riv_restaurant_img;
        private TextView tv_restaurant_name;
        private TextView tv_restaurant_menu;
        private TextView tv_status;
        public ItemViewHolder(View itemView) {
            super(itemView);
            layout_restaurant = (LinearLayout) itemView.findViewById(R.id.layout_restaurant);
            riv_restaurant_img = (ImageView) itemView.findViewById(R.id.res_image);
            tv_restaurant_name = (TextView) itemView.findViewById(R.id.tv_restaurant_name);
            tv_restaurant_menu = (TextView) itemView.findViewById(R.id.tv_restaurant_menu);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }

}

