package com.example.jmjapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Options;

import java.util.ArrayList;

public class MenuChooseOptionAdapter extends RecyclerView.Adapter<MenuChooseOptionAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Options> mItems;

    public MenuChooseOptionAdapter(Context context, ArrayList<Options> options) {
        this.context = context;
        mItems = options;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public MenuChooseOptionAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_choose_option_item, parent, false);
        return new MenuChooseOptionAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MenuChooseOptionAdapter.ItemViewHolder holder, final int position) {

    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        public ItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
