package com.example.jmjapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.OptionGroups;

import java.util.ArrayList;

public class MenuChooseOptionGroupsAdapter extends RecyclerView.Adapter<MenuChooseOptionGroupsAdapter.ItemViewHolder> {
    private Context context;
    ArrayList<OptionGroups> mItems;

    public MenuChooseOptionGroupsAdapter(Context context, ArrayList<OptionGroups> optionGroups) {
        this.context = context;
        mItems = optionGroups;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public MenuChooseOptionGroupsAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_choose_option_group_item, parent, false);
        context = parent.getContext();
        return new MenuChooseOptionGroupsAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MenuChooseOptionGroupsAdapter.ItemViewHolder holder, final int position) {
        holder.menu_choose_option_group_name.setText(mItems.get(position).getOgName());
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView menu_choose_option_group_name;
        RecyclerView menu_choose_option_group_list;

        public ItemViewHolder(View itemView) {
            super(itemView);
            menu_choose_option_group_name = itemView.findViewById(R.id.menu_choose_option_group_name);
            menu_choose_option_group_list = itemView.findViewById(R.id.menu_choose_option_group_list);
        }
    }
}
