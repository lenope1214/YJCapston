package com.example.jmjapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Chatbot;
import com.example.jmjapp.user.ReservationOrderDetailActivity;

import java.util.ArrayList;

public class ChatbotListAdapter extends RecyclerView.Adapter<ChatbotListAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Chatbot> mItems;

    private String question;
    private String answer;

    public ChatbotListAdapter(Context context, ArrayList<Chatbot> chatbots) {
        this.context = context;
        mItems = chatbots;
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
    public ChatbotListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chatbots_item, parent, false);
        return new ChatbotListAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ChatbotListAdapter.ItemViewHolder holder, final int position) {
        question = mItems.get(position).getQuestion();
        answer = mItems.get(position).getAnswer();

//
        holder.question.setText(question);
        holder.answer.setText(answer);
        holder.chatbotList.setOnClickListener(v -> {
            //TODO 챗봇 수정화면으로
//            Intent intent = new Intent(context, ReservationOrderDetailActivity.class);
//            intent.putExtra("chatbotId", mItems.get(position).getId());
//            context.startActivity(intent);
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
        private TextView question, answer;
        private ConstraintLayout chatbotList;
        
        public ItemViewHolder(View itemView) {
            super(itemView);
            question = (TextView) itemView.findViewById(R.id.chatbot_question);
            answer = (TextView) itemView.findViewById(R.id.chatbot_answer);
        }
    }
}
