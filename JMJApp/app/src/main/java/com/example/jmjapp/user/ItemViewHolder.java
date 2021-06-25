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
import com.example.jmjapp.dto.Notice;

//ViewHolderMovie = ItemViewHolder
public class ItemViewHolder extends RecyclerView.ViewHolder {
    ImageView iv_noticemenu;
    ImageView iv_noticedown;
    TextView tv_expand;
    TextView tv_notice_name;
    TextView tv_notice_intro;
    LinearLayout notice_linearItem1;

    OnViewHolderItemClickListener onViewHolderItemClickListener;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        iv_noticemenu = itemView.findViewById(R.id.iv_servicemenu);
        iv_noticedown = itemView.findViewById(R.id.iv_centerdown);
        tv_notice_name = itemView.findViewById(R.id.tv_service_name);
        tv_notice_intro = itemView.findViewById(R.id.tv_service_intro);
        tv_expand = itemView.findViewById(R.id.tv_expand_service);
        notice_linearItem1 = itemView.findViewById(R.id.service_linearItem);

        notice_linearItem1.setOnClickListener(v -> onViewHolderItemClickListener.onViewHolderItemClick());
    }

    public void onBind(Notice notice, int position, SparseBooleanArray selectedItems) {
        tv_notice_name.setText(notice.getTv_notice_name());
        tv_notice_intro.setText(notice.getTv_notice_intro());
        iv_noticemenu.setImageResource(notice.getIv_noticemenu());
        iv_noticedown.setImageResource(notice.getIv_noticedown());
        tv_expand.setText(notice.getTv_expand());


        changeVisibility(selectedItems.get(position));
    }

    /**
     * 클릭된 Item의 상태 변경
     *
     * @param isExpanded Item을 펼칠 것인지 여부
     */
    private void changeVisibility(final boolean isExpanded) {


        // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
        ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, 500) : ValueAnimator.ofInt(500, 0);
        // Animation이 실행되는 시간, n/1000초
        va.setDuration(500);
        va.addUpdateListener(animation -> {
            // imageView의 높이 변경
            tv_expand.getLayoutParams().height = (int) animation.getAnimatedValue();
            tv_expand.requestLayout();
            // imageView가 실제로 사라지게하는 부분
            tv_expand.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        });
        // Animation start
        va.start();

    }

    public void setOnViewHolderItemClickListener(OnViewHolderItemClickListener onViewHolderItemClickListener) {
        this.onViewHolderItemClickListener = onViewHolderItemClickListener;
    }
}
