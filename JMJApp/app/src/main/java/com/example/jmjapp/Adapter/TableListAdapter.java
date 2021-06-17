package com.example.jmjapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.dto.Tables;
import com.example.jmjapp.owner.MainActivity_O;
import com.example.jmjapp.owner.TableDetailActivity;

import java.util.ArrayList;

public class TableListAdapter extends RecyclerView.Adapter<TableListAdapter.ItemViewHolder> {
    Context context;
    //ArrayList<Tables> mItems;
    ArrayList<Tables> items = new ArrayList<>();

    public TableListAdapter(Context context, ArrayList<Tables> tables) {
        this.context = context;
        items = tables;
    }

    public TableListAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Tables> tables) {
        this.items = tables;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public TableListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item, parent, false);
        return new TableListAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final TableListAdapter.ItemViewHolder holder, final int position) {
        Tables item = items.get(position);
        holder.setItem(item);

        if (String.valueOf(items.get(position).getUsing()) != null) {
            if (items.get(position).getUsing() == 'Y') {
                holder.table_isUse.setText("사용중");
                holder.table_isUse.setTextColor(Color.parseColor("#33FA01"));
            } else if (items.get(position).getUsing() == 'N') {
                holder.table_isUse.setText("비었음");
            }
        }

        holder.table_constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TableDetailActivity.class);
                intent.putExtra("tabId", items.get(position).getTabId());
                intent.putExtra("shopId", MainActivity_O.shopNumber);
                intent.putExtra("no", items.get(position).getNo());
                intent.putExtra("using", items.get(position).getUsing());
                intent.putExtra("orderId", items.get(position).getOrderId());
                context.startActivity(intent);
            }
        });

    }

    public void addItem(Tables item) {
        items.add(item);
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return items.size();
        //return mItems == null ? 0 : mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView table_no, table_isUse;
        ConstraintLayout table_constraint;

        public ItemViewHolder(View itemView) {
            super(itemView);
            table_no = itemView.findViewById(R.id.table_no);
            table_isUse = itemView.findViewById(R.id.table_isUse);
            table_constraint = itemView.findViewById(R.id.table_constraint);
        }

        public void setItem(Tables item){
            table_no.setText(item.getNo() + "번 테이블");
//            table_isUse.setText(item.getUsing());
        }
    }
}
