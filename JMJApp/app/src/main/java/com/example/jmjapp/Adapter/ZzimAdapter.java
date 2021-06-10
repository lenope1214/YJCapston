package com.example.jmjapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Mark;
import com.example.jmjapp.dto.Shop;

import java.util.ArrayList;
import java.util.List;

public class ZzimAdapter extends RecyclerView.Adapter<ZzimAdapter.ItemViewHolder> {

    Context context;
    ArrayList<Mark.MarkList> recordData;

    public ZzimAdapter(Context context, ArrayList<Mark.MarkList> recordData){
        this.context = context;
        this.recordData = recordData;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout cl_zzimItem;
        ImageView iv_zzimImg;
        TextView tv_zzimName;
        TextView tv_zzimCategory;
        TextView tv_zzimAddress;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cl_zzimItem = itemView.findViewById(R.id.cl_zzimItem);
            iv_zzimImg = itemView.findViewById(R.id.iv_zzimImg);
            tv_zzimName = itemView.findViewById(R.id.tv_zzimName);
            tv_zzimCategory = itemView.findViewById(R.id.tv_zzimCategory);
            tv_zzimAddress = itemView.findViewById(R.id.tv_zzimAddress);
        }
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zzim_item, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public int getItemCount() {
        return recordData.size();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
//        List<Shop> markList = getShopList();
//        holder.iv_zzimImg.setImageResource(recordData.get(position).getShopList());
    }



}
