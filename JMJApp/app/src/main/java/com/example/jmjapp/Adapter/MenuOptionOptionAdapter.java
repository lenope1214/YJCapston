package com.example.jmjapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.OptionGroups;
import com.example.jmjapp.dto.Options;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.owner.MenuOptionActivity;
import com.example.jmjapp.owner.MenuOptionGroupActivity;
import com.example.jmjapp.owner.MenuRegisterActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuOptionOptionAdapter extends RecyclerView.Adapter<MenuOptionOptionAdapter.ItemViewHolder> {
    ArrayList<Options> items = new ArrayList<>();
    private Context context;

    // 새로운 뷰 홀더 생성
    @NonNull
    @Override
    public MenuOptionOptionAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.option_option_item, parent, false);
        context = parent.getContext();
        return new MenuOptionOptionAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MenuOptionOptionAdapter.ItemViewHolder holder, final int position) {
        Options item = items.get(position);
        holder.setItem(item);

        holder.option_option_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("클릭","클릭");

                holder.optionGroupId = MenuOptionGroupActivity.optionGroupId;
                holder.jwt = MenuOptionGroupActivity.jwt;

//                SharedPreferences pref = context.getSharedPreferences("auth_o", Context.MODE_PRIVATE);
//                holder.jwt = pref.getString("token", "");
                Log.d("jwt", holder.jwt);

                holder.map.put("name", holder.option_option_name.getText().toString());
                holder.map.put("price", holder.option_option_price.getText().toString());
                holder.map.put("max", holder.option_option_max.getText().toString());
                holder.map.put("no", holder.option_option_no.getText().toString());
                holder.map.put("optionGroupId", holder.optionGroupId);

                holder.optionsCall = Server.getInstance().getApi().registerOption("Bearer " + holder.jwt, holder.map);
                holder.optionsCall.enqueue(new Callback<Options>() {
                    @Override
                    public void onResponse(Call<Options> call, Response<Options> response) {
                        if (response.isSuccessful()) {
                            Log.d("Options 성공", "Options 성공");
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            holder.dialog = builder.setMessage("옵션이 추가되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            }).create();
                            builder.setCancelable(false);
                            holder.dialog.show();
                        } else {
                            Log.d("Options 실패1", "Options 실패1");
                        }
                    }

                    @Override
                    public void onFailure(Call<Options> call, Throwable t) {
                        Log.d("Options 실패2", "Options 실패2");
                    }
                });
            }
        });

    }

    public void addItem(Options item) {
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
        TextView option_option_save;
        EditText option_option_price, option_option_name, option_option_no, option_option_max;
        private Call<Options> optionsCall;
        private String jwt;
        private String optionGroupId;
        private AlertDialog dialog;
        private Map<String, String> map = new HashMap();

        public ItemViewHolder(View itemView) {
            super(itemView);
            option_option_name = (EditText)itemView.findViewById(R.id.option_option_name);
            option_option_save = (TextView)itemView.findViewById(R.id.option_option_save);
            option_option_price = (EditText)itemView.findViewById(R.id.option_option_price);
            option_option_no = (EditText)itemView.findViewById(R.id.option_option_no);
            option_option_max = (EditText)itemView.findViewById(R.id.option_option_max);
        }

        public void setItem(Options item) {
            option_option_name.setText(item.getOptionId());
            //option_option_save.setText(item.getOptionId());
            option_option_price.setText(item.getOptionId());
        }
    }
}
