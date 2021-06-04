package com.example.jmjapp.owner;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivitySettingChatbotBinding;
import com.example.jmjapp.databinding.ActivityChatbotManagementBinding;
import com.example.jmjapp.network.Server;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class ChatbotSettingActivity extends AppCompatActivity {
    ActivitySettingChatbotBinding binding;
    EditText questionET, answerET;
    Button submitBtn, resetBtn;

    private Call<ResponseBody> postChatbot;
    SharedPreferences pref;

    private void submitSw(boolean isAdd){
        System.out.println("isAdd : " + isAdd);
        if(isAdd){
            submitBtn.setText("추가");
        }else{
            submitBtn.setText("수정");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Long chatbotId = getIntent().getLongExtra("chatbotId", 0);
        String question = getIntent().getStringExtra("question");
        String answer = getIntent().getStringExtra("answer");
        Boolean isAdd = getIntent().getBooleanExtra("isAdd", true);
        questionET.setText(question);
        answerET.setText(answer);

        submitSw(isAdd);

        submitBtn.setOnClickListener(v -> {
            pref = getSharedPreferences("auth_o", MODE_PRIVATE);
            String jwt = pref.getString("token", null);
            Map<String, Object> map = new HashMap();
            if(!isAdd)map.put("chatbotId" , chatbotId);
            map.put("shopId", MainActivity_O.shopNumber);
            map.put("question", questionET.getText().toString());
            map.put("answer", answerET.getText().toString());


            postChatbot = isAdd ?
                    Server.getInstance().getApi().insertChatbot("Bearer " + jwt, map)
                    : Server.getInstance().getApi().patchChatbot("Bearer " + jwt, map)  ;
            postChatbot.enqueue(new Callback<ResponseBody>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        Log.d("result : ", "챗봇 수정 성공");
                        finish();

                    } else if (response.code() == 400) {
                        Log.d("result 400 : ", "챗봇등록 실패");
                        System.out.println(response.errorBody().string());
                    } else {
                        Log.d("result : ", "챗봇 세팅 연결실패" + "@@@@@");
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingChatbotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        answerET = binding.chatbotAnswer;
        questionET = binding.chatbotQuestion;
        submitBtn = binding.submitBtn;
        resetBtn = binding.resetBtn;

        resetBtn.setOnClickListener(v -> {
            questionET.setText("");
            answerET.setText("");
        });
    }

}