package com.example.jmjapp.user;

import android.animation.ValueAnimator;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapp.R;
import com.example.jmjapp.dto.CustomerService;


public class CustomerServiceViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_servicemenu;
    ImageView iv_centerdown;
    TextView tv_service_name;
    TextView tv_expand_service;
    LinearLayout service_linearItem;

    OnViewHolderItemClickListener1 onViewHolderItemClickListener1;

    public CustomerServiceViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_servicemenu = itemView.findViewById(R.id.iv_servicemenu);
        iv_centerdown = itemView.findViewById(R.id.iv_centerdown);
        tv_service_name = itemView.findViewById(R.id.tv_service_name);
        tv_expand_service = itemView.findViewById(R.id.tv_expand_service);
        service_linearItem = itemView.findViewById(R.id.service_linearItem);

        service_linearItem.setOnClickListener(v -> onViewHolderItemClickListener1.onViewHolderItemClick1());
    }

    public void onBind(CustomerService customerService, int position, SparseBooleanArray selectedItems) {
        iv_servicemenu.setImageResource(customerService.getIv_servicemenu());
        iv_centerdown.setImageResource(customerService.getIv_centerdown());
        tv_service_name.setText(customerService.getTv_service_name());
        tv_expand_service.setText(customerService.getTv_expand_service());

        changeVisibility(selectedItems.get(position));
    }

    /**
     * 클릭된 Item의 상태 변경
     *
     * @param isExpanded Item을 펼칠 것인지 여부
     */
    private void changeVisibility(final boolean isExpanded) {

        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 750) : ValueAnimator.ofInt(750, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(500);
        va.addUpdateListener(animation -> {
            // imageView의 높이 변경
            tv_expand_service.getLayoutParams().height = (int) animation.getAnimatedValue();
            tv_expand_service.requestLayout();
            // imageView가 실제로 사라지게하는 부분
            tv_expand_service.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        });
        // Animation start
        va.start();

    }

    public void setOnViewHolderItemClickListener1(OnViewHolderItemClickListener1 onViewHolderItemClickListener1) {
        this.onViewHolderItemClickListener1 = onViewHolderItemClickListener1;
    }
}

