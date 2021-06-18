package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Mark;
import com.example.jmjapp.dto.Shop;
import com.example.jmjapp.network.Server;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class ZzimAdapter extends RecyclerView.Adapter<ZzimAdapter.ItemViewHolder> {

    Context context;
    ArrayList<Shop> recordData;

    private String jwt;

    public ZzimAdapter(Context context, ArrayList<Shop> recordData){
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
        Glide.with(context).load("http://3.34.55.186:8088/" + recordData.get(position).getImgPath()).override(100,100).into(holder.iv_zzimImg);
        holder.tv_zzimName.setText(recordData.get(position).getName());
        holder.tv_zzimAddress.setText(recordData.get(position).getAddress());
        holder.tv_zzimCategory.setText(recordData.get(position).getCategory());

        SharedPreferences pref = context.getSharedPreferences("auth", Context.MODE_PRIVATE);
        jwt = pref.getString("token", null);

        System.out.println(recordData.get(position).getShopId()+"gggggggggggggggggg");

        holder.cl_zzimItem.setOnClickListener(v -> {
            Toast.makeText(context, "잘 눌립니다", Toast.LENGTH_SHORT).show();
        });
    }



}