package com.example.jmjapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.dto.Options;
import com.example.jmjapp.network.Server;

import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuChooseOptionGroupsAdapter extends RecyclerView.Adapter<MenuChooseOptionGroupsAdapter.ItemViewHolder> {
    private Context context;
    ArrayList<OptionGroups> mItems;
    private List<Options> optionsList;
    private int ogMax;
    private String menuName;
    private boolean is_it_already;

    public MenuChooseOptionGroupsAdapter(Context context, ArrayList<OptionGroups> optionGroups, int ogMax, String menuName) {
        this.context = context;
        mItems = optionGroups;
        this.ogMax = ogMax;
        this.menuName = menuName;
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
        SharedPreferences pref = context.getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        holder.jwt = pref.getString("token", "");

        holder.optionGroupId = mItems.get(position).getOptionGroupId();
        Log.d("optionGroupId", holder.optionGroupId);
        Log.d("ogMax", String.valueOf(ogMax));

        holder.menu_choose_option_group_name.setText(mItems.get(position).getOgName());

        holder.optionsCall = Server.getInstance().getApi().optionsList("Bearer " + holder.jwt, holder.optionGroupId);
        holder.optionsCall.enqueue(new Callback<List<Options>>() {
            @SuppressLint("ResourceType")
            @SneakyThrows
            @Override
            public void onResponse(Call<List<Options>> call, Response<List<Options>> response) {
                if (response.isSuccessful()) {
                    Log.d("OptionsList 성공", "OptionsList 성공");
                    optionsList = response.body();
                    Log.d("size", String.valueOf(optionsList.size()));
                    String[] str = new String[optionsList.size()];
                    holder.listItem = new ArrayList<String>();
                    Log.d("optionlist", optionsList.toString());
                    for (Options list : optionsList) {
                        holder.listItem.add(list.getName() + "\t\t" + "+" + list.getPrice());
                        for (int i = 0; i < optionsList.size(); i++) {
                            str[i] =list.getOptionId();
                        }
                    }

                    if (ogMax == 1) {
                        holder.adapter1 = new ArrayAdapter<String>(context, R.layout.simple_list_item_single_choice, holder.listItem);
                        holder.listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
                        holder.listView.setAdapter(holder.adapter1);
                    } else if (ogMax > 1) {
                        holder.adapter1 = new ArrayAdapter<String>(context, R.layout.simple_list_item_multiple_choice, holder.listItem);
                        holder.listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
                        holder.listView.setAdapter(holder.adapter1);
                    }

                } else {
                    Log.d("OptionsList 실패1", "OptionsList 실패1"+response.errorBody().string()+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Options>> call, Throwable t) {
                Log.d("OptionsList 실패2", "OptionsList 실패2");
            }
        });

        if (ogMax == 1) { // radio 일때
            Log.d("radio실행","radio실행");

            SharedPreferences prefOptions = context.getSharedPreferences("options", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefOptions.edit();

            int list_size = prefOptions.getInt("list_size", 0);
//            // 옵션그룹아이디 + 옵션아이디

            holder.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    editor.putString("list_" + list_size + "_menuName", menuName);
                    editor.putString("list_" + list_size + "_optionGroupId", optionsList.get(i).getOptionGroupId());
                    editor.putString("list_" + list_size + "_optionId", optionsList.get(i).getOptionId());
                    editor.putInt("list_size", list_size + 1);
                    editor.apply();
                }
            });
        } else if (ogMax > 1) { // check 일때
            Log.d("check실행","check실행");
        }

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
        private LinearLayoutManager layoutManager;
        private MenuChooseOptionAdapter adapter;
        private String optionGroupId, jwt, getItem;
        private ArrayList<Options> items = new ArrayList();

        private Call<List<Options>> optionsCall;

        ListView listView;
        ArrayAdapter<String> adapter1;
        ArrayList<String> listItem;

        public ItemViewHolder(View itemView) {
            super(itemView);
            menu_choose_option_group_name = itemView.findViewById(R.id.menu_choose_option_group_name);
            //menu_choose_option_group_list = itemView.findViewById(R.id.menu_choose_option_group_list);
            listView = itemView.findViewById(R.id.menu_choose_option_group_listview);
        }
    }
}
