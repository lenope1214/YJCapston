package com.example.jmjapp.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.OptionGroups;
import java.util.ArrayList;

public class MenuOptionGroupAdapter extends RecyclerView.Adapter<MenuOptionGroupAdapter.ItemViewHolder>  {
    ArrayList<OptionGroups> items = new ArrayList<>();

    // 새로운 뷰 홀더 생성
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_option_group_item, parent, false);
        return new ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MenuOptionGroupAdapter.ItemViewHolder holder, final int position) {
        OptionGroups item = items.get(position);
        holder.setItem(item);
    }

    public void addItem(OptionGroups item) {
        items.add(item);
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView menu_option_group_name, menu_option_group_min_tv, menu_option_group_max_tv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            menu_option_group_name = itemView.findViewById(R.id.menu_option_group_name);
            menu_option_group_min_tv = itemView.findViewById(R.id.menu_option_group_min_tv);
            menu_option_group_max_tv = itemView.findViewById(R.id.menu_option_group_max_tv);
        }

        @SuppressLint("SetTextI18n")
        public void setItem(OptionGroups item) {
            menu_option_group_name.setText(item.getOgName());
            menu_option_group_min_tv.setText("최소 선택 개수 " + item.getOgMin()+"개");
            menu_option_group_max_tv.setText("최대 선택 개수 " + item.getOgMax()+"개");
        }
    }

}
