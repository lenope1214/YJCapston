package com.example.jmjapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.nhn.android.naverlogin.data.OAuthLoginData;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;


public class HomeFragment_O extends Fragment {
    ToggleButton toggle_Button;

    public HomeFragment_O() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.home_fragment_o, container, false);
        Bundle bundle = getArguments();
        String owner_number = bundle.getString("owner_number");
        Log.d("owner_number@@@@@@@@@@@@", owner_number);

        toggle_Button = rootView.findViewById(R.id.toggle_button);
        toggle_Button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String toastMessage;
                if(isChecked) {
                    toastMessage = "매장 On";
                } else {
                    toastMessage = "매장 Off";
                }
                Toast.makeText(getActivity().getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    private void makeShopFragment() {
    }
}
