package com.example.jmjapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    ImageButton loginButton;
    LinearLayout login_btn;
    LinearLayout profile_btn;
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
