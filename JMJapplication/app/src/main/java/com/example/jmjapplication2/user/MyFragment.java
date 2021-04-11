package com.example.jmjapplication2.user;

import android.content.Intent;
import android.os.Bundle;
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
    ConstraintLayout notice_constraint;
    ConstraintLayout service_constraint;
    TextView profile_text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_fragment, container, false);

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

        notice_constraint = rootView.findViewById(R.id.notice_constraint);
        notice_constraint.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NoticeActivity.class);
                startActivity(intent);
            }
        });

        service_constraint = rootView.findViewById(R.id.service_constraint);
        service_constraint.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CustomerService.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        String id = ((JMJApplication)this.getActivity().getApplication()).getId();

        if(id != null) {
            login_btn.setVisibility(View.GONE);
            profile_btn.setVisibility(View.VISIBLE);
            profile_text.setText(id + "님 안녕하세요");
        } else {
            login_btn.setVisibility(View.VISIBLE);
            profile_btn.setVisibility(View.GONE);
        }
    }
}
