package com.example.jmjapp.owner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.jmjapp.R;
import com.example.jmjapp.owner.push.BellActivity_O;


public class HomeFragment_O extends Fragment {
    ToggleButton toggle_Button;
    TextView text_myshop_name, profit_detail_tv;
    Button bell_button;

    static public String shopNumber;

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
        shopNumber = bundle.getString("shopNumber");
        String shopName = bundle.getString("shopName");

        text_myshop_name = rootView.findViewById(R.id.text_myshop_name);
        text_myshop_name.setText("현재 매장 : " + shopName);

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

        bell_button = rootView.findViewById(R.id.bell_button);
        bell_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BellActivity_O.class);
                startActivity(intent);
            }
        });

        profit_detail_tv = rootView.findViewById(R.id.profit_detail_tv);
        profit_detail_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfitDetailActivity.class);
                startActivity(intent);
            }
        });

        return rootView;
    }

    private void makeShopFragment() {
    }
}
