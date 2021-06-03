package com.example.jmjapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Order;

import java.sql.Timestamp;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class ReservationManagementAdapter extends RecyclerView.Adapter<ReservationManagementAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Order> mItems;

    private Call<ResponseBody> responseBodyCall;
    private String device_token, userId, orderId, jwt, orderRequest;
    private Long arriveTime;

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
        holder.rs_mg_userId.setText(mItems.get(position).getUserName()+"님 외"+mItems.get(position).getPeople()+"명");

        orderId = mItems.get(position).getOrderId();
        arriveTime = mItems.get(position).getArriveTime();
        orderRequest = mItems.get(position).getOrderRequest();

        String resTime = String.valueOf(new Timestamp(arriveTime));
        String year = resTime.substring(0,4);
        String month = resTime.substring(5,7);
        String day = resTime.substring(8,10);

        String hour = resTime.substring(11,13);
        String min = resTime.substring(14,16);

        holder.rs_mg_resTime.setText(year+"년"+month+"월"+day+"일 " +hour+"시"+min+"분");

        if (orderRequest.equals("")) {
            holder.rs_mg_request.setText("요청사항이 없습니다.");
        } else {
            holder.rs_mg_request.setText(orderRequest);
        }
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
