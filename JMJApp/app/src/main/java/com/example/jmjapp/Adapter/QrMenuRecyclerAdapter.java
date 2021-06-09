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
import com.example.jmjapp.user.MainActivity;
import com.example.jmjapp.user.MenuChoosedActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class QrMenuRecyclerAdapter extends RecyclerView.Adapter<QrMenuRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Menu> mItems;

    public QrMenuRecyclerAdapter(Context context, ArrayList<Menu> menus) {
        this.context = context;
        mItems = menus;
    }

    public QrMenuRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Menu> menus){
        this.mItems = menus;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public QrMenuRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_qr_menu_list, parent, false);
        return new QrMenuRecyclerAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final QrMenuRecyclerAdapter.ItemViewHolder holder, final int position) {
        Log.d("Qr메뉴","Qr메뉴");
        holder.tv_qr_menu_name.setText(mItems.get(position).getName());
        holder.tv_qr_menu_price.setText(mItems.get(position).getPrice() + "원");
        String tv_menu_intro = mItems.get(position).getIntro();
        Log.d("awd","Awd");
        Glide.with(context).load("http://3.34.55.186:8088/" + mItems.get(position).getImgPath()).override(500,500).into(holder.tv_qr_menu_img);

        if (mItems.get(position).getIsSale() == 'Y') {
            holder.qr_layout_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, MenuChoosedActivity.class);
                    intent.putExtra("menuId", mItems.get(position).getMenuId());
                    intent.putExtra("menuName", holder.tv_qr_menu_name.getText().toString());
                    intent.putExtra("menuPrice", mItems.get(position).getPrice());
                    intent.putExtra("menuIntro", tv_menu_intro);
                    intent.putExtra("menuImage", mItems.get(position).getImgPath());
                    intent.putExtra("qr", "qr");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        } else {
            holder.qr_layout_menu.setOnClickListener(new View.OnClickListener() {
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
            holder.qr_menu_is_popular.setVisibility(View.VISIBLE);
        }

        if(mItems.get(position).getIsSale() == 'N') {
            holder.qr_menu_is_sale.setVisibility(View.VISIBLE);
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
        private ConstraintLayout qr_layout_menu, qr_menu_is_popular;
        private ImageView tv_qr_menu_img;
        private TextView tv_qr_menu_name, qr_menu_is_sale;
        private TextView tv_qr_menu_price;
        public ItemViewHolder(View itemView) {
            super(itemView);
            qr_layout_menu = (ConstraintLayout)itemView.findViewById(R.id.qr_layout_menu);
            tv_qr_menu_img = (ImageView) itemView.findViewById(R.id.tv_qr_menu_img);
            tv_qr_menu_name = (TextView)itemView.findViewById(R.id.tv_qr_menu_name);
            tv_qr_menu_price = (TextView)itemView.findViewById(R.id.tv_qr_menu_price);
            qr_menu_is_popular = (ConstraintLayout) itemView.findViewById(R.id.qr_menu_is_popular);
            qr_menu_is_sale = (TextView) itemView.findViewById(R.id.qr_menu_is_sale);
        }
    }
}
