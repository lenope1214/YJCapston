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
import com.example.jmjapp.R;
import com.example.jmjapp.dto.Chatbot;
import com.example.jmjapp.owner.ChatbotSettingActivity;
import com.example.jmjapp.user.ShopDetailActivity;

import java.util.ArrayList;

public class ChatbotListUserAdapter extends RecyclerView.Adapter<ChatbotListUserAdapter.ItemViewHolder> {
    Context context;
    ArrayList<Chatbot> mItems;
    int postion;

   // private String question;
   // private String answer;
    private String chatbot_answer2;


    public ChatbotListUserAdapter(Context context, ArrayList<Chatbot> chatbots) {
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
    public ChatbotListUserAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_chatbots_user_item, parent, false);
        context = parent.getContext();
        return new ChatbotListUserAdapter.ItemViewHolder(view);
    }

    // View 의 내용을 해당 포지션의 데이터로 바꿉니다.
    @Override
    public void onBindViewHolder(final ChatbotListUserAdapter.ItemViewHolder holder, int position) {
        Chatbot chatbot = mItems.get(position);
      //  answer = mItems.get(position).getAnswer();

//
//

        holder.question.setText(chatbot.getQuestion());
        holder.answer.setText(chatbot.getAnswer());

//        System.out.println(holder.chatbot_answer2.getText()+"yyyyyyyyyyyyyyyyyy");
//        holder.chatbot_answer2.setText(answer);

//        holder.question.setOnClickListener(v -> { // 챗봇 리스트를 누르면 수정 화면으로 넘어 가야겠네요?
//            System.out.println(holder.question.getText()+"qqqqqqqqqqqqqqqqqqqqq");
//            System.out.println(holder.answer.getText().toString()+"xxxxxxxxxxxxxxxx");
//
////            try{
////                holder.chatbot_answer2.setText(holder.answer.getText().toString());
////            } catch (Exception e) {
////                System.out.println(e+"gggggggggg");
////            }
//
////            holder.chatbot_answer2.setVisibility(View.VISIBLE);
////            holder.imageView25.setVisibility(View.VISIBLE);
////            System.out.println(holder.chatbot_answer2+"vvvvvvvvvvvvvvvvvvv");
//
//
//        });
        holder.question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.click(holder.clChatbot, position);
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
        private TextView question, answer, chatbot_answer2, tv_shop_name;
        private ConstraintLayout clChatbot, user_answer;
        private ImageView imageView26, imageView25;
        private View v_emp;

        public ItemViewHolder(View itemView) {
            super(itemView);
            clChatbot = (ConstraintLayout) itemView.findViewById(R.id.cl_chatbot_item);
            question = (TextView) itemView.findViewById(R.id.chatbot_question);
            answer = (TextView) itemView.findViewById(R.id.chatbot_answer);
            imageView26 = (ImageView) itemView.findViewById(R.id.imageView26);
            v_emp = (View) itemView.findViewById(R.id.v_emp);
            chatbot_answer2 = (TextView) itemView.findViewById(R.id.chatbot_answer2);
            imageView25 = (ImageView) itemView.findViewById(R.id.imageView25);
            tv_shop_name = (TextView) itemView.findViewById(R.id.tv_shop_name);
            user_answer = (ConstraintLayout) itemView.findViewById(R.id.user_answer);

        }
    }

    public void addOnClick(OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    public OnItemClick onItemClick;

    public interface OnItemClick{
        void click(View view, int position);
    }

    public Chatbot getItem(int position){
        return mItems.get(position);
    }
}
