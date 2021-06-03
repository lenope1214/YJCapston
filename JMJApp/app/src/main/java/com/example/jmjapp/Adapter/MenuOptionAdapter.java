package com.example.jmjapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.dto.Options;

import java.util.ArrayList;

public class MenuOptionAdapter extends RecyclerView.Adapter<MenuOptionAdapter.ItemViewHolder>{
    Context context;
    ArrayList<OptionGroups> mItems;
    ArrayList<Options> optionsItems;
    private String optionGroupId;

    public MenuOptionAdapter(Context context, ArrayList<OptionGroups> optionGroups, String optionGroupId1) {
        this.context = context;
        mItems = optionGroups;
        optionGroupId = optionGroupId1;
    }

//    public basketRecyclerAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setItems(ArrayList<Menu> menus){
//        this.mItems = menus;
//    }

    // 새로운 뷰 홀더 생성
    @Override
    public MenuOptionAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_item, parent, false);
        return new MenuOptionAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MenuOptionAdapter.ItemViewHolder holder, final int position) {
        holder.option_option_group_name.setText(mItems.get(position).getOgName());

        holder.layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.menu_option_option_list.setLayoutManager(holder.layoutManager);
        holder.adapter = new MenuOptionOptionAdapter(optionGroupId);
        holder.menu_option_option_list.setAdapter(holder.adapter);

        holder.option_option_group_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("add실행","add실행");
                holder.adapter.addItem(new Options());
                holder.adapter.notifyDataSetChanged();
            }
        });

    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems==null? 0:mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView option_option_group_name, option_option_group_add;
        RecyclerView menu_option_option_list;
        private LinearLayoutManager layoutManager;
        private MenuOptionOptionAdapter adapter;

        public ItemViewHolder(View itemView) {
            super(itemView);
            option_option_group_name = itemView.findViewById(R.id.option_option_group_name);
            option_option_group_add = itemView.findViewById(R.id.option_option_group_add);
            menu_option_option_list = itemView.findViewById(R.id.menu_option_option_list);
        }
    }
}
