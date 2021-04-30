package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.user.MenuChoosedActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MenuRecyclerAdapter extends RecyclerView.Adapter<MenuRecyclerAdapter.ItemViewHolder>{
    Context context;
    ArrayList<Menu> mItems;

    public MenuRecyclerAdapter(Context context, ArrayList<Menu> menus) {
        this.context = context;
        mItems = menus;
    }

    public MenuRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Menu> menus){
        this.mItems = menus;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_menu_list, parent, false);
        return new ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.tv_menu_name.setText(mItems.get(position).getName());
        holder.tv_menu_price.setText(mItems.get(position).getPrice() + "원");
        String tv_menu_intro = mItems.get(position).getIntro();
        Log.d("awd","Awd");
        Glide.with(context).load("http://3.34.55.186:8088/" + mItems.get(position).getImgPath()).override(500,500).into(holder.tv_menu_img);

        if (mItems.get(position).getIsSale() == 'Y') {
            holder.layout_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MenuChoosedActivity.class);
                    intent.putExtra("menuName", holder.tv_menu_name.getText().toString());
                    intent.putExtra("menuPrice", mItems.get(position).getPrice());
                    intent.putExtra("menuIntro", tv_menu_intro);
                    intent.putExtra("menuImage", mItems.get(position).getImgPath());
                    context.startActivity(intent);
                }
            });
        } else {
            holder.layout_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "품절된 메뉴입니다.", Snackbar.LENGTH_SHORT).setAction("확인", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            return;
                        }
                    }).show();
                }
            });
        }

        if(mItems.get(position).getIsPopular() == 'Y') {
            holder.menu_is_popular.setVisibility(View.VISIBLE);
        }

        if(mItems.get(position).getIsSale() == 'N') {
            holder.menu_is_sale.setVisibility(View.VISIBLE);
        }
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems==null? 0:mItems.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩합니다.
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private ConstraintLayout layout_menu, menu_is_popular;
        private ImageView tv_menu_img;
        private TextView tv_menu_name, menu_is_sale;
        private TextView tv_menu_price;
        public ItemViewHolder(View itemView) {
            super(itemView);
            layout_menu = (ConstraintLayout)itemView.findViewById(R.id.layout_menu);
            tv_menu_img = (ImageView) itemView.findViewById(R.id.tv_menu_img);
            tv_menu_name = (TextView)itemView.findViewById(R.id.tv_menu_name);
            tv_menu_price = (TextView)itemView.findViewById(R.id.tv_menu_price);
            menu_is_popular = (ConstraintLayout) itemView.findViewById(R.id.menu_is_popular);
            menu_is_sale = (TextView) itemView.findViewById(R.id.menu_is_sale);
        }
    }
}
