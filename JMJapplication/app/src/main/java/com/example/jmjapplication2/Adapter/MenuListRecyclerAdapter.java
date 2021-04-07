package com.example.jmjapplication2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Menu;

import java.util.ArrayList;

public class MenuListRecyclerAdapter extends RecyclerView.Adapter<MenuListRecyclerAdapter.ItemViewHolder>{
    Context context;
    ArrayList<Menu> mItems;

    public MenuListRecyclerAdapter(Context context, ArrayList<Menu> menus) {
        this.context = context;
        mItems = menus;
    }

    public MenuListRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Menu> menus){
        this.mItems = menus;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_shop_menu_list, parent, false);
        return new ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.shop_menu_name.setText(mItems.get(position).getName());
        holder.shop_menu_price.setText(mItems.get(position).getPrice() + "원");
        holder.shop_menu_duration.setText(mItems.get(position).getDuration());
        holder.shop_menu_name.setText(mItems.get(position).getName());

    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems==null? 0:mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView shop_menu_img;
        private TextView shop_menu_name;
        private TextView shop_menu_price;
        private TextView shop_menu_duration;
        private CheckBox checkbox_soldout;
        private CheckBox checkbox_popular;
        public ItemViewHolder(View itemView) {
            super(itemView);
            shop_menu_img = (ImageView) itemView.findViewById(R.id.shop_menu_img);
            shop_menu_name = (TextView)itemView.findViewById(R.id.shop_menu_name);
            shop_menu_price = (TextView)itemView.findViewById(R.id.shop_menu_price);
            shop_menu_duration = (TextView) itemView.findViewById(R.id.shop_menu_duration);
            checkbox_soldout = (CheckBox)itemView.findViewById(R.id.checkbox_soldout);
            checkbox_popular = (CheckBox)itemView.findViewById(R.id.checkbox_popular);
        }
    }
}
