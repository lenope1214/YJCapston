package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Menu;
import com.example.jmjapp.network.Server;
import com.example.jmjapp.owner.MainActivity_O;
import com.example.jmjapp.owner.MenuDetailActivity;
import com.example.jmjapp.owner.MenuMoveDetailActivity;
import com.example.jmjapp.owner.ShopDetailFragment_O;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MenuListRecyclerAdapter extends RecyclerView.Adapter<MenuListRecyclerAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Menu> mItems;

    private Call<ResponseBody> responseBodyCall;

    public MenuListRecyclerAdapter(Context context, ArrayList<Menu> menus) {
        this.context = context;
        mItems = menus;
    }

    public MenuListRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void setItems(ArrayList<Menu> menus) {
        this.mItems = menus;
    }

    // 새로운 뷰 홀더 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_shop_menu_list, parent, false);
        return new ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ItemViewHolder holder, final int position) {
        holder.shop_menu_name.setText(mItems.get(position).getName());
        holder.shop_menu_price.setText(mItems.get(position).getPrice() + "원");
        holder.shop_menu_intro.setText(mItems.get(position).getIntro());
        holder.shop_menu_duration.setText(String.valueOf(mItems.get(position).getDuration()) + "분");
        Glide.with(context).load("http://3.34.55.186:8088/" + mItems.get(position).getImgPath()).into(holder.shop_menu_img);

        if (mItems.get(position).getIsSale() == 'Y') {
            holder.checkbox_soldout.setChecked(true);
        } else {
            holder.checkbox_soldout.setChecked(false);
        }

        if (mItems.get(position).getIsPopular() == 'Y') {
            holder.checkbox_popular.setChecked(true);
        } else {
            holder.checkbox_popular.setChecked(false);
        }

        SharedPreferences pref = context.getSharedPreferences("auth_o", Context.MODE_PRIVATE);
        String jwt = pref.getString("token", null);
        String menuId = (MenuDetailActivity.shopNumber + holder.shop_menu_name.getText().toString());

        holder.menu_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.menu_detail_list.setVisibility(View.GONE);
                holder.menu_detail_update.setVisibility(View.VISIBLE);
                holder.shop_menu_name_update.setText(mItems.get(position).getName());
                holder.shop_menu_price_update.setText(String.valueOf(mItems.get(position).getPrice()));
                holder.shop_menu_intro_update.setText(mItems.get(position).getIntro());
                holder.shop_menu_duration_update.setText(String.valueOf(mItems.get(position).getDuration()));
            }
        });

        holder.menu_update_btn_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String intro = holder.shop_menu_intro_update.getText().toString();
                String price = holder.shop_menu_price_update.getText().toString();
                String duration = holder.shop_menu_duration_update.getText().toString();

//                RequestBody menuIdBody = RequestBody.create(MediaType.parse("text/plain"), menuId);
//                RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), name);
//                RequestBody introBody = RequestBody.create(MediaType.parse("text/plain"), intro);
//                RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), price);
//                RequestBody durationBody = RequestBody.create(MediaType.parse("text/plain"), duration);

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("알림");
                builder.setMessage("수정하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Map<String, String> map = new HashMap();
                        map.put("menuId", menuId);
                        map.put("intro", intro);
                        map.put("price", price);
                        map.put("duration", duration);

                        responseBodyCall = Server.getInstance().getApi().updateMenu("Bearer " + jwt, map);
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @SneakyThrows
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    if (response.code() == 200) {
                                        Log.d("메뉴수정", "성공");
                                        holder.shop_menu_intro.setText(intro);
                                        holder.shop_menu_price.setText(price + "원");
                                        holder.shop_menu_duration.setText(duration + "분");
                                    } else {
                                        Log.d("메뉴수정", "실패");
                                    }
                                } else {
                                    response.errorBody().toString();
                                    Log.d("연결실패", response.errorBody().string());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("연결2", "실패2");
                            }
                        });
                        holder.menu_detail_list.setVisibility(View.VISIBLE);
                        holder.menu_detail_update.setVisibility(View.GONE);
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("실행안함", "실행안함");
                    }
                });
                builder.show();
            }
        });

        holder.menu_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("알림");
                builder.setMessage("삭제하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        responseBodyCall = Server.getInstance().getApi().deleteMenu("Bearer " + jwt, menuId);
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @SneakyThrows
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    if (response.code() == 204) {
                                        Log.d("메뉴삭제", "성공");
                                        mItems.remove(position);
                                        notifyDataSetChanged();
                                    } else {
                                        Log.d("메뉴삭제", "실패");
                                    }
                                } else {
                                    response.errorBody().toString();
                                    Log.d("연결실패", response.errorBody().string());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d("연결2", "실패2");
                            }
                        });
                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("실행안함", "실행안함");
                    }
                });
                builder.show();
            }
        });

        holder.menu_update_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.menu_detail_list.setVisibility(View.VISIBLE);
                holder.menu_detail_update.setVisibility(View.GONE);
            }
        });

        holder.checkbox_soldout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkbox_soldout.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("알림");
                    builder.setMessage("품절 등록하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updateSale("Bearer " + jwt, MainActivity_O.shopNumber, menuId);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @SneakyThrows
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        if (response.code() == 200) {
                                            Log.d("품절", "성공");
                                        } else {
                                            Log.d("품절", "실패");
                                        }
                                    } else {
                                        response.errorBody().toString();
                                        Log.d("연결실패", response.errorBody().string());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("연결2", "실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            holder.checkbox_soldout.setChecked(false);
                        }
                    });
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("알림");
                    builder.setMessage("품절 해제하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updateSale("Bearer " + jwt, MainActivity_O.shopNumber, menuId);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @SneakyThrows
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        if (response.code() == 200) {
                                            Log.d("SS", "성공");
                                        } else {
                                            Log.d("SS", "실패");
                                        }
                                    } else {
                                        response.errorBody().toString();
                                        Log.d("연결실패", response.errorBody().string());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("연결2", "실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            holder.checkbox_soldout.setChecked(true);
                        }
                    });
                    builder.show();
                }
            }
        });

        holder.checkbox_popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.checkbox_popular.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("알림");
                    builder.setMessage("인기메뉴로 등록하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updatePopular("Bearer " + jwt, MainActivity_O.shopNumber, menuId);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @SneakyThrows
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        if (response.code() == 200) {
                                            Log.d("SS", "성공");
                                        } else {
                                            Log.d("SS", "실패");
                                        }
                                    } else {
                                        response.errorBody().toString();
                                        Log.d("연결실패", response.errorBody().string());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("연결2", "실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            holder.checkbox_popular.setChecked(false);
                        }
                    });
                    builder.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("알림");
                    builder.setMessage("인기메뉴를 해제하시겠습니까?");
                    builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            responseBodyCall = Server.getInstance().getApi().updatePopular("Bearer " + jwt, MainActivity_O.shopNumber, menuId);
                            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                                @SneakyThrows
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        if (response.code() == 200) {
                                            Log.d("SS", "성공");
                                        } else {
                                            Log.d("SS", "실패");
                                        }
                                    } else {
                                        response.errorBody().toString();
                                        Log.d("연결실패", response.errorBody().string());
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Log.d("연결2", "실패2");
                                }
                            });
                        }
                    });
                    builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            holder.checkbox_popular.setChecked(true);
                        }
                    });
                    builder.show();
                }
            }
        });

        holder.menu_move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MenuMoveDetailActivity.class);
                intent.putExtra("menuId", mItems.get(position).getMenuId());
                intent.putExtra("menuName", mItems.get(position).getName());
                intent.putExtra("menuPrice", mItems.get(position).getPrice());
                intent.putExtra("imgPath", mItems.get(position).getImgPath());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
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
        private ImageView shop_menu_img_update, shop_menu_img;
        private TextView shop_menu_name, menu_move;
        private TextView shop_menu_price, shop_menu_intro, shop_menu_name_update;
        private TextView shop_menu_duration;
        private CheckBox checkbox_soldout;
        private CheckBox checkbox_popular;
        private Button menu_update_btn, menu_update_btn_comp, menu_delete_btn, menu_update_btn_cancel;
        private ConstraintLayout menu_detail_list, menu_detail_update;
        private EditText shop_menu_intro_update, shop_menu_price_update, shop_menu_duration_update;

        public ItemViewHolder(View itemView) {
            super(itemView);
            // 리스트
            menu_detail_list = (ConstraintLayout) itemView.findViewById(R.id.menu_detail_list);
            shop_menu_img = (ImageView) itemView.findViewById(R.id.shop_menu_img);
            shop_menu_name = (TextView) itemView.findViewById(R.id.shop_menu_name);
            shop_menu_intro = (TextView) itemView.findViewById(R.id.shop_menu_intro);
            shop_menu_price = (TextView) itemView.findViewById(R.id.shop_menu_price);
            shop_menu_duration = (TextView) itemView.findViewById(R.id.shop_menu_duration);
            checkbox_soldout = (CheckBox) itemView.findViewById(R.id.checkbox_soldout);
            checkbox_popular = (CheckBox) itemView.findViewById(R.id.checkbox_popular);
            menu_update_btn = (Button) itemView.findViewById(R.id.menu_update_btn);
            menu_delete_btn = (Button) itemView.findViewById(R.id.menu_delete_btn);
            menu_move = (TextView) itemView.findViewById(R.id.menu_move);

            // 수정창
            menu_detail_update = (ConstraintLayout) itemView.findViewById(R.id.menu_detail_update);
            menu_update_btn_cancel = (Button) itemView.findViewById(R.id.menu_update_btn_cancel);
            menu_update_btn_comp = (Button) itemView.findViewById(R.id.menu_update_btn_comp);
            shop_menu_img_update = (ImageView) itemView.findViewById(R.id.shop_menu_img);
            shop_menu_name_update = (TextView) itemView.findViewById(R.id.shop_menu_name_update);
            shop_menu_intro_update = (EditText) itemView.findViewById(R.id.shop_menu_intro_update);
            shop_menu_price_update = (EditText) itemView.findViewById(R.id.shop_menu_price_update);
            shop_menu_duration_update = (EditText) itemView.findViewById(R.id.shop_menu_duration_update);
        }
    }

}
