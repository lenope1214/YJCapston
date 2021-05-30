package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuMoveOptionAdapter extends RecyclerView.Adapter<MenuMoveOptionAdapter.ItemViewHolder> {
    private Context context;
    ArrayList<OptionGroups> mItems;

    public MenuMoveOptionAdapter(Context context, ArrayList<OptionGroups> optionGroups) {
        this.context = context;
        mItems = optionGroups;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public MenuMoveOptionAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_move_option_item, parent, false);
        context = parent.getContext();
        return new MenuMoveOptionAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MenuMoveOptionAdapter.ItemViewHolder holder, final int position) {
        SharedPreferences pref = context.getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        holder.jwt = pref.getString("token", "");

        holder.menu_move_option_name.setText(mItems.get(position).getOgName());

        holder.optionGroupId = mItems.get(position).getOptionGroupId();
        Log.d("qwe", holder.optionGroupId+holder.jwt);

        holder.optionsCall = Server.getInstance().getApi().optionsList("Bearer " + holder.jwt, holder.optionGroupId);
        holder.optionsCall.enqueue(new Callback<List<Options>>() {
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Options>> call, Response<List<Options>> response) {
                if (response.isSuccessful()) {
                    Log.d("OptionsList 성공", "OptionsList 성공");
                    List<Options> optionsList = response.body();
                    for (Options list : optionsList) {
                        holder.items.add(new Options(list.getOptionId(), list.getName(),
                                list.getPrice(), list.getMax(), list.getOptionGroupId()));
                        holder.menu_move_option_option_list.setHasFixedSize(true);
                        holder.adapter = new MenuMoveOptionOptionAdapter(context, holder.items);
                        holder.menu_move_option_option_list.setLayoutManager(new LinearLayoutManager(context));
                        holder.menu_move_option_option_list.setAdapter(holder.adapter);
                    }
                } else {
                    Log.d("OptionsList 실패1", "OptionsList 실패1"+response.errorBody().string());
                }
            }

            @Override
            public void onFailure(Call<List<Options>> call, Throwable t) {
                Log.d("OptionsList 실패2", "OptionsList 실패2");
            }
        });
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView menu_move_option_name;
        RecyclerView menu_move_option_option_list;
        private LinearLayoutManager layoutManager;
        private MenuMoveOptionOptionAdapter adapter;
        private String optionGroupId, jwt;
        private ArrayList<Options> items = new ArrayList();

        private Call<List<Options>> optionsCall;

        public ItemViewHolder(View itemView) {
            super(itemView);
            menu_move_option_name = itemView.findViewById(R.id.menu_move_option_name);
            menu_move_option_option_list = itemView.findViewById(R.id.menu_move_option_option_list);
        }
    }
}
