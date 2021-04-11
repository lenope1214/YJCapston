package com.example.jmjapplication2.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Notice;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ItemViewHolder>{

    private ArrayList<Notice> arrayList;

    public NoticeAdapter(ArrayList<Notice> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    // listView 가 처음으로 생성될 때 생명주기
    public NoticeAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notice_item, parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    // listView 가 추가될 때 생명주기
    public void onBindViewHolder(@NonNull NoticeAdapter.ItemViewHolder holder, int position) {
        holder.iv_noticemenu.setImageResource(arrayList.get(position).getIv_noticemenu());
        holder.iv_noticedown.setImageResource(arrayList.get(position).getIv_noticedown());
        holder.tv_notice_name.setText(arrayList.get(position).getTv_notice_name());
        holder.tv_notice_intro.setText(arrayList.get(position).getTv_notice_intro());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String curName = holder.tv_notice_name.getText().toString();
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition());
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        try {
            arrayList.remove(position);
            notifyItemRemoved(position);
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        protected ImageView iv_noticemenu;
        protected ImageView iv_noticedown;
        protected TextView tv_notice_name;
        protected TextView tv_notice_intro;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_noticemenu = (ImageView) itemView.findViewById(R.id.iv_centermenu);
            this.iv_noticedown = (ImageView) itemView.findViewById(R.id.iv_centerdown);
            this.tv_notice_name = (TextView) itemView.findViewById(R.id.tv_center_name);
            this.tv_notice_intro = (TextView) itemView.findViewById(R.id.tv_notice_intro);
        }
    }
}
