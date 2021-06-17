package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.OrderMenu;

import java.util.ArrayList;

public class PosMenuListAdapter extends RecyclerView.Adapter<PosMenuListAdapter.ItemViewHolder> {
    Context context;
    ArrayList<OrderMenu> mItems;

    public PosMenuListAdapter(Context context, ArrayList<OrderMenu> orderMenus) {
        this.context = context;
        mItems = orderMenus;
    }

//    public PosMenuListAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setItems(ArrayList<Menu> menus){
//        this.mItems = menus;
//    }

    // 새로운 뷰 홀더 생성
    @Override
    public PosMenuListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pos_menu_item, parent, false);
        return new PosMenuListAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final PosMenuListAdapter.ItemViewHolder holder, final int position) {

        holder.post_menu_name.setText(mItems.get(position).getMenuName());
        holder.post_menu_count.setText(mItems.get(position).getQuantity() + "개");
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems==null? 0:mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView post_menu_name, post_menu_count;
        public ItemViewHolder(View itemView) {
            super(itemView);
            post_menu_name = itemView.findViewById(R.id.post_menu_name);
            post_menu_count = itemView.findViewById(R.id.post_menu_count);
        }
    }
}
