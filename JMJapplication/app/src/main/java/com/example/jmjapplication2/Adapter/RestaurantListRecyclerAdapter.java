package com.example.jmjapplication2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.ShopDetailActivity;
import com.example.jmjapplication2.dto.Shop;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RestaurantListRecyclerAdapter extends RecyclerView.Adapter<RestaurantListRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Shop> shops;

    public RestaurantListRecyclerAdapter(Context context, ArrayList<Shop> shopss) {
        this.context = context;
        shops = shopss;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list, parent, false);
        return new ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_restaurant_name.setText(shops.get(position).getName());

        holder.layout_restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShopDetailActivity.class);
                intent.putExtra("shopNumber", shops.get(position).getId());
                //Log.d("result : ", shops.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return shops.size();
    }

    // 커스텀 뷰홀더
    // item layout에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout layout_restaurant;
        private ImageView riv_restaurant_img;
        private TextView tv_restaurant_name;
        private TextView tv_restaurant_menu;
        private TextView tv_status;
        public ItemViewHolder(View itemView) {
            super(itemView);
            layout_restaurant = (ConstraintLayout) itemView.findViewById(R.id.layout_restaurant_list);
            riv_restaurant_img = (ImageView) itemView.findViewById (R.id.res_list_image);
            tv_restaurant_name = (TextView) itemView.findViewById(R.id.res_list_name);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
        }
    }
}
