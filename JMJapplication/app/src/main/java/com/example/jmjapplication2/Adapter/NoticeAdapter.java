package com.example.jmjapplication2.Adapter;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.jmjapplication2.R;
import com.example.jmjapplication2.dto.Notice;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.ItemViewHolder>{

    private ArrayList<Notice> arrayList = new ArrayList<>();

    // Item의 클릭 상태를 저장할 array 객체
    private SparseBooleanArray selectedItems = new SparseBooleanArray();
    // 직전에 클릭됐던 Item의 position
    private int prePosition = -1;
    private Context context;


    public NoticeAdapter(ArrayList<Notice> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    // listView 가 처음으로 생성될 때 생명주기
    public NoticeAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_notice_item, parent,false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    // listView 가 추가될 때 생명주기
    public void onBindViewHolder(@NonNull NoticeAdapter.ItemViewHolder holder, int position) {
        holder.iv_noticemenu.setImageResource(arrayList.get(position).getIv_noticemenu());
        holder.iv_noticedown.setImageResource(arrayList.get(position).getIv_noticedown());
        holder.tv_notice_name.setText(arrayList.get(position).getTv_notice_name());
        holder.tv_notice_intro.setText(arrayList.get(position).getTv_notice_intro());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {
                String curName = holder.tv_notice_name.getText().toString();
                Toast.makeText(v.getContext(), curName, Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != arrayList ? arrayList.size() : 0);
    }

    void addItem(Notice notice){
        arrayList.add(notice);
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        protected ImageView iv_noticemenu;
        protected ImageView iv_noticedown;
        protected TextView tv_notice_name;
        protected TextView tv_notice_intro;
        private Notice notice;
        private int position;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.iv_noticemenu = (ImageView) itemView.findViewById(R.id.iv_centermenu);
            this.iv_noticedown = (ImageView) itemView.findViewById(R.id.iv_centerdown);
            this.tv_notice_name = (TextView) itemView.findViewById(R.id.tv_center_name);
            this.tv_notice_intro = (TextView) itemView.findViewById(R.id.tv_notice_intro);
        }

        void onBind(Notice notice, int position) {
            this.notice = notice;
            this.position = position;

            tv_notice_name.setText(notice.getTv_notice_name());
            tv_notice_intro.setText(notice.getTv_notice_intro());
            iv_noticemenu.setImageResource(notice.getIv_noticemenu());
            iv_noticedown.setImageResource(notice.getIv_noticedown());

            changeVisibility(selectedItems.get(position));

            iv_noticedown.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.notice_linearItem:
                    if (selectedItems.get(position)) {
                        // 펼쳐진 Item을 클릭 시
                        selectedItems.delete(position);
                    } else {
                        // 직전의 클릭됐던 Item의 클릭상태를 지움
                        selectedItems.delete(prePosition);
                        // 클릭한 Item의 position을 저장
                        selectedItems.put(position, true);
                    }
                    // 해당 포지션의 변화를 알림
                    if (prePosition != -1) notifyItemChanged(prePosition);
                    notifyItemChanged(position);
                    // 클릭된 position 저장
                    prePosition = position;
                    break;

            }
        }
/**
 * 클릭된 Item의 상태 변경
 * @param isExpanded Item을 펼칠 것인지 여부
 */
                private void changeVisibility(final boolean isExpanded){
                    // height 값을 dp로 지정해서 넣고싶으면 아래 소스를 이용
                    int dpValue = 150;
                    float d = context.getResources().getDisplayMetrics().density;
                    int height = (int) (dpValue * d);

                    // ValueAnimator.ofInt(int... values)는 View가 변할 값을 지정, 인자는 int 배열
                    ValueAnimator va = isExpanded ? ValueAnimator.ofInt(0, height) : ValueAnimator.ofInt(height, 0);
                    // Animation이 실행되는 시간, n/1000초
                    va.setDuration(600);
                    va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animation) {
                            // value는 height 값
                            int value = (int) animation.getAnimatedValue();
                            // imageView의 높이 변경
                            iv_noticemenu.getLayoutParams().height = value;
                            iv_noticemenu.requestLayout();
                            // imageView가 실제로 사라지게하는 부분
                            iv_noticemenu.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
                        }
                    });
                    // Animation start
                    va.start();

        }
    }
}
