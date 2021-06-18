package com.example.jmjapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;

import java.util.ArrayList;

public class TableDetailAdapter extends RecyclerView.Adapter<TableDetailAdapter.ItemViewHolder> {
    Context context;
    ArrayList<OrderMenu> mItems;

    public TableDetailAdapter(Context context, ArrayList<OrderMenu> menus) {
        this.context = context;
        mItems = menus;
    }

    public TableDetailAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<OrderMenu> menus){
        this.mItems = menus;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public TableDetailAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_detail_item, parent, false);
        return new TableDetailAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final TableDetailAdapter.ItemViewHolder holder, final int position) {
        holder.table_detail_menu_name.setText(mItems.get(position).getMenuName());
        holder.table_detail_menu_count.setText(mItems.get(position).getQuantity() + "개");


    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems==null? 0:mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView table_detail_menu_name, table_detail_menu_count;
        public ItemViewHolder(View itemView) {
            super(itemView);
            table_detail_menu_name = itemView.findViewById(R.id.table_detail_menu_name);
            table_detail_menu_count = itemView.findViewById(R.id.table_detail_menu_count);
        }
    }
}
