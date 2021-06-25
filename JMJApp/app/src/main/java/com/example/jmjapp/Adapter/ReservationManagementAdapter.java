package com.example.jmjapp.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.dto.OrderMenu;
import com.example.jmjapp.network.Server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationManagementAdapter extends RecyclerView.Adapter<ReservationManagementAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Order> mItems;

    private Call<ResponseBody> responseBodyCall;
    private String device_token, userId, orderId, jwt, orderRequest;
    private Long arriveTime;
    private Call<Order.OrderMenuList> getOrderMenus;
    private String sum = "";

    public ReservationManagementAdapter(Context context, ArrayList<Order> resManage) {
        this.context = context;
        mItems = resManage;
    }

    //    public AlarmListRecyclerAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setItems(ArrayList<Order> alarm) {
//        this.mItems = alarm;
//    }

    // 새로운 뷰 홀더 생성
    @Override
    public ReservationManagementAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.res_manage_list, parent, false);
        return new ReservationManagementAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ReservationManagementAdapter.ItemViewHolder holder, final int position) {
        holder.rs_mg_userId.setText(mItems.get(position).getUserName() + "님 외" + mItems.get(position).getPeople() + "명");

        orderId = mItems.get(position).getOrderId();
        arriveTime = mItems.get(position).getArriveTime();
        orderRequest = mItems.get(position).getOrderRequest();

        String resTime = String.valueOf(new Timestamp(arriveTime));
        String year = resTime.substring(0, 4);
        String month = resTime.substring(5, 7);
        String day = resTime.substring(8, 10);

        String hour = resTime.substring(11, 13);
        String min = resTime.substring(14, 16);

        holder.rs_mg_resTime.setText(year + "년" + month + "월" + day + "일 " + hour + "시" + min + "분");

        if (orderRequest.equals("")) {
            holder.rs_mg_request.setText("요청사항이 없습니다.");
        } else {
            holder.rs_mg_request.setText(orderRequest);
        }

        holder.rs_mg_menuList.setOnClickListener(v -> {
            getOrderMenus = Server.getInstance().getApi().orderOneMenu(orderId);
            getOrderMenus.enqueue(new Callback<Order.OrderMenuList>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<Order.OrderMenuList> call, Response<Order.OrderMenuList> response) {
                    if (response.isSuccessful()) {
                        Log.d("orderMenu 성공", "orderMenu 성공");
                        List<OrderMenu> orderMenuList = response.body().getOrderMenuList();

                        String[] list_menuName = new String[orderMenuList.size()];
                        int[] list_menuCount = new int[orderMenuList.size()];

                        int index = 0;

                        for (OrderMenu list : orderMenuList) {
                            list_menuName[index] = list.getMenuName();
                            list_menuCount[index] = Integer.parseInt(list.getQuantity());
                            index++;
                        }

                        for (int i = 0; i < orderMenuList.size(); i++) {
                            String value = list_menuName[i] + "\t\t\t\t" + list_menuCount[i] + "개\n";
                            sum = sum + value;
                        }

                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("주문메뉴");
                        builder.setMessage(sum).setPositiveButton("확인", (dialog, which) -> {
                            sum = "";
                        }).create();
                        builder.show();
                    } else {
                        Log.d("orderMenu 실패1", "orderMenu 실패1" + response.errorBody().string());
                    }
                }

                @Override
                public void onFailure(Call<Order.OrderMenuList> call, Throwable t) {
                    Log.d("orderMenu 실패2", "orderMenu 실패2");
                }
            });
        });
    }

    // 데이터 셋의 크기
    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView rs_mg_userId, rs_mg_resTime, rs_mg_request, rs_mg_menuList;

        public ItemViewHolder(View itemView) {
            super(itemView);
            rs_mg_userId = (TextView) itemView.findViewById(R.id.rs_mg_userId);
            rs_mg_resTime = (TextView) itemView.findViewById(R.id.rs_mg_resTime);
            rs_mg_request = (TextView) itemView.findViewById(R.id.rs_mg_request);
            rs_mg_menuList = (TextView) itemView.findViewById(R.id.rs_mg_menuList);
        }
    }
}
