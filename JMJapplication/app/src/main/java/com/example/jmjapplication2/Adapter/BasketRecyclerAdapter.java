package com.example.jmjapplication2.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Menu;

import java.util.ArrayList;

public class BasketRecyclerAdapter extends RecyclerView.Adapter<BasketRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Menu> mItems;
    private AlertDialog dialog;


    public BasketRecyclerAdapter(Context context, ArrayList<Menu> menus) {
        this.context = context;
        mItems = menus;
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
    public BasketRecyclerAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.basket_menu_list, parent, false);
        return new BasketRecyclerAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final BasketRecyclerAdapter.ItemViewHolder holder, final int position) {
        holder.tv_menu_name.setText(mItems.get(position).getName());
        //holder.tv_menu_price.setText(mItems.get(position).getPrice() + "원");
        //Glide.with(context).load(mItems.get(position).getImgUrl()).into(holder.iv_menu_img);

        holder.menu_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> menu_name_list = new ArrayList<String>();
                ArrayList<Integer> menu_price_list = new ArrayList<Integer>();
                ArrayList<Integer> menu_count_list = new ArrayList<Integer>();

                SharedPreferences pref = context.getSharedPreferences("basket", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                int list_size = pref.getInt("list_size",0);

                if (list_size - 1 == 0) {
                    SharedPreferences pref2 = context.getSharedPreferences("basket_shop", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor2 = pref2.edit();
                    editor2.clear();
                    editor2.apply();
                }

                //int menuCount = pref.getInt("list_" + holder.tv_menu_name + "_count", 1);

                for (int i = 0; i < list_size; i++) {
                    String[] list_name = new String[list_size];
                    int[] list_price = new int[list_size];
                    int[] list_count = new int[list_size];
                    list_name[i] = pref.getString("list_" + i + "_name", "nope");
                    list_price[i] = pref.getInt("list_" + i + "_price", 0);
                    list_count[i] = pref.getInt("list_" + i + "_count", 1);

//                    Log.d("list_size", String.valueOf(list_size));
//                    Log.d("list_name", list_name[i]);
//                    Log.d("list_price", String.valueOf(list_price[i]));
//                    Log.d("list_count", String.valueOf(list_count[i]));
//                    Log.d("cureent menu", holder.tv_menu_name.getText().toString());
//                    Log.d("current price", holder.tv_menu_price.getText().toString());

                    menu_name_list.add(list_name[i]);
                    menu_price_list.add(list_price[i]);
                    menu_count_list.add(list_count[i]);

//                    System.out.println(menu_name_list.toString());
//                    System.out.println(menu_price_list.toString());
//                    System.out.println(menu_count_list.toString());

                    if(list_name[i] == holder.tv_menu_name.getText().toString()) {
                        menu_name_list.remove(i);
                        menu_price_list.remove(i);
                        menu_count_list.remove(i);

//                        System.out.println("두번째 결과" + menu_name_list.toString());
//                        System.out.println("두번째 결과" + menu_price_list.toString());
//                        System.out.println("두번째 결과" + menu_count_list.toString());
                    }
                }
//                System.out.println("최종 리스트" + menu_name_list);
//                System.out.println("최종 리스트" + menu_price_list);
//                System.out.println("최종 리스트" + menu_count_list);

                editor.clear();
                editor.apply();

                if (menu_name_list.size() != 0) {
                    for (int j = 0; j < menu_name_list.size(); j++) {
                        editor.putString("list_" + j + "_name", menu_name_list.get(j));
                        editor.putInt("list_" + j + "_price", menu_price_list.get(j));
                        editor.putInt("list_" + j + "_count", menu_count_list.get(j));
                        editor.putInt("list_size", j + 1);
                    }
                    editor.apply();
                } else {
                    Toast.makeText(context, "비었습니다", Toast.LENGTH_SHORT).show();
                }
                mItems.remove(position);
                notifyDataSetChanged();
            }
        });

        SharedPreferences pref = context.getSharedPreferences("basket", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        int list_size = pref.getInt("list_size", 0);
        for (int i = 0; i < list_size; i++) {
            String[] list_name = new String[list_size];
            int[] list_price = new int[list_size];
            list_name[i] = pref.getString("list_" + i + "_name", null);
            list_price[i] = pref.getInt("list_" + i + "_price", 0);
            if(list_name[i].equals(holder.tv_menu_name.getText().toString())) {
                int menuCount1 = pref.getInt("list_" + i + "_count", 0);
                int menuPrice1 = pref.getInt("list_" + i + "_price", 0);
                holder.menu_count.setText(String.valueOf(menuCount1));
                holder.tv_menu_price.setText(String.valueOf(menuPrice1));
            }
        }

        holder.menu_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list_size; i++) {
                    String[] list_name = new String[list_size];
                    int[] list_price = new int[list_size];
                    list_name[i] = pref.getString("list_" + i + "_name", null);
                    list_price[i] = pref.getInt("list_" + i + "_price", 0);
                    if(list_name[i].equals(holder.tv_menu_name.getText().toString())) {
                        int menuCount1 = pref.getInt("list_" + i + "_count", 0);
                        if (menuCount1 > 1) {
                            editor.putInt("list_" + i + "_price", list_price[i] - list_price[i]/menuCount1);
                            editor.putInt("list_" + i + "_count", menuCount1 - 1);
                            editor.apply();
                            notifyDataSetChanged();
                        }
                    }
                }
            }
        });

        holder.menu_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < list_size; i++) {
                    String[] list_name = new String[list_size];
                    int[] list_price = new int[list_size];
                    list_name[i] = pref.getString("list_" + i + "_name", null);
                    list_price[i] = pref.getInt("list_" + i + "_price", 0);
                    if (list_name[i].equals(holder.tv_menu_name.getText().toString())) {
                        int menuCount1 = pref.getInt("list_" + i + "_count", 0);
                        if (menuCount1 < 10) {
                            editor.putInt("list_" + i + "_price", list_price[i] + list_price[i]/menuCount1);
                            editor.putInt("list_" + i + "_count", menuCount1 + 1);
                            editor.apply();
                            notifyDataSetChanged();
                        }
                    }
                }
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
        private ConstraintLayout layout_menu;
        private ImageView iv_menu_img, menu_delete, menu_plus, menu_minus;
        private TextView tv_menu_name, menu_count;
        private TextView tv_menu_price;
        public ItemViewHolder(View itemView) {
            super(itemView);
            layout_menu = (ConstraintLayout)itemView.findViewById(R.id.layout_menu2);
            iv_menu_img = (ImageView) itemView.findViewById(R.id.iv_menu_img2);
            tv_menu_name = (TextView)itemView.findViewById(R.id.tv_menu_name2);
            tv_menu_price = (TextView)itemView.findViewById(R.id.tv_menu_price2);
            menu_delete = (ImageView) itemView.findViewById(R.id.menu_delete);
            menu_count = (TextView) itemView.findViewById(R.id.menu_count);
            menu_plus = (ImageView) itemView.findViewById(R.id.menu_plus);
            menu_minus = (ImageView) itemView.findViewById(R.id.menu_minus);
        }
    }

}
