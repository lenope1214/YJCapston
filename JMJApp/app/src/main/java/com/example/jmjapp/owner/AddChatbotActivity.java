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
import com.example.jmjapp.databinding.ActivityAddChatbotBinding;
import com.example.jmjapp.databinding.ActivityChatbotManagementBinding;
import com.example.jmjapp.network.Server;
import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class AddChatbotActivity extends AppCompatActivity {
    ActivityAddChatbotBinding binding;
    EditText question, answer;
    Button submitBtn, resetBtn;

    private Call<ResponseBody> insertChatbot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddChatbotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        answer = binding.chatbotAnswer;
        question = binding.chatbotQuestion;
        submitBtn = binding.submitBtn;
        resetBtn = binding.resetBtn;

        resetBtn.setOnClickListener(v -> {
            question.setText("");
            answer.setText("");
        });


        submitBtn.setOnClickListener(v -> {
            SharedPreferences pref = getSharedPreferences("auth_o", MODE_PRIVATE);
            String jwt = pref.getString("token", null);
            Map<String, Object> map = new HashMap();
            map.put("shopId", MainActivity_O.shopNumber);

            map.put("question", question.getText().toString());
            map.put("answer", answer.getText().toString());

            insertChatbot = Server.getInstance().getApi().insertChatbot("Bearer " + jwt, map);
            insertChatbot.enqueue(new Callback<ResponseBody>() {
                @SneakyThrows
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful()) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        Dialog dialog = builder.setMessage("직원이 등록되었습니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), ChatbotManagementActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).create();
                        builder.setCancelable(false);
                        dialog.show();
                        Log.d("result : ", "챗봇 등록 성공");

                    } else if (response.code() == 400) {
                        Log.d("result 400 : ", "챗봇등록 실패");
                        System.out.println(response.errorBody().string());
                    } else {
                        Log.d("result : ", "연결실패" + "@@@@@");
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        });
    }

}