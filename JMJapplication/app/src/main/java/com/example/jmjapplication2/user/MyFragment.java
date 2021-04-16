package com.example.jmjapplication2.user;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.example.jmjapplication2.JMJApplication;
import com.example.jmjapplication2.R;

public class MyFragment extends Fragment {
    ImageButton loginButton;
    ConstraintLayout login_btn;
    ConstraintLayout profile_btn;
    TextView profile_text;
    private String user_id;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_fragment, container, false);

        Bundle bundle = getArguments();
        user_id = bundle.getString("user_id","nothing");

        profile_text = rootView.findViewById(R.id.profile_text);
        login_btn = rootView.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        profile_btn = rootView.findViewById(R.id.linear_btn);
        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileUpdateActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(user_id.equals("nothing")) {
            login_btn.setVisibility(View.VISIBLE);
            profile_btn.setVisibility(View.GONE);
        } else {
            login_btn.setVisibility(View.GONE);
            profile_btn.setVisibility(View.VISIBLE);
            profile_text.setText(user_id + "님 안녕하세요");
        }
    }
}
