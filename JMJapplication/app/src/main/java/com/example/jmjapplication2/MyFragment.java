package com.example.jmjapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment {
    ImageButton loginButton;
    Button login_btn;
    Button profile_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.my_fragment, container, false);


        login_btn = rootView.findViewById(R.id.login_btn);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        profile_btn = rootView.findViewById(R.id.profile_btn);
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

        String userid = ((JMJApplication)this.getActivity().getApplication()).getUserid();

        if(userid != null) {
            login_btn.setVisibility(View.GONE);
            profile_btn.setVisibility(View.VISIBLE);
            profile_btn.setText(userid);
        } else {
            login_btn.setVisibility(View.VISIBLE);
            profile_btn.setVisibility(View.GONE);
        }
    }
}
