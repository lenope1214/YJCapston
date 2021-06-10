package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.Order;
import com.example.jmjapp.user.LoginActivity;
import com.example.jmjapp.user.ReservationOrderDetailActivity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class MyOrderListAdapter extends RecyclerView.Adapter<MyOrderListAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Order> mItems;
    
    private String payTime;
    private char my_order_list_isAccept;

    public MyOrderListAdapter(Context context, ArrayList<Order> myOrder) {
        this.context = context;
        mItems = myOrder;
    }

//    public MyOrderListAdapter(Context context) {
//        this.context = context;
//    }
//
//    public void setItems(ArrayList<Order> myOrder) {
//        this.mItems = myOrder;
//    }

    // 새로운 뷰 홀더 생성
    @Override
    public MyOrderListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_order_list, parent, false);
        return new MyOrderListAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final MyOrderListAdapter.ItemViewHolder holder, final int position) {
        payTime = mItems.get(position).getPayTime();
        my_order_list_isAccept = mItems.get(position).getAccept();

//        String year = resTime.substring(0,4);
        String month = payTime.substring(5,7);
        String day = payTime.substring(8,10);
//
//        String hour = resTime.substring(11,13);
//        String min = resTime.substring(14,16);
//
        holder.my_order_list_date.setText(month+"월"+day+"일");
        holder.my_order_list_shopName.setText(mItems.get(position).getShopName());
        holder.my_order_list_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReservationOrderDetailActivity.class);
                intent.putExtra("orderId", mItems.get(position).getOrderId());
                context.startActivity(intent);
            }
        });

        if (my_order_list_isAccept == 'N') {
            holder.my_order_list_isAccept.setText("주문 대기 중");
        } else if (my_order_list_isAccept == 'Y') {
            holder.my_order_list_isAccept.setText("주문완료");
            holder.my_order_list_isAccept.setTextColor(Color.parseColor("#33FA01"));
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
        private TextView my_order_list_date, my_order_list_shopName, my_order_list_isAccept;
        private ConstraintLayout my_order_list_detail;
        
        public ItemViewHolder(View itemView) {
            super(itemView);
            my_order_list_date = (TextView) itemView.findViewById(R.id.my_order_list_date);
            my_order_list_shopName = (TextView) itemView.findViewById(R.id.my_order_list_shopName);
            my_order_list_detail = (ConstraintLayout) itemView.findViewById(R.id.my_order_list_detail);
            my_order_list_isAccept = (TextView) itemView.findViewById(R.id.my_order_list_isAccept);
        }
    }
}
