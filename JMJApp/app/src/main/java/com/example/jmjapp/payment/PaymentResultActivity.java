package com.example.jmjapp.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.jmjapp.R;
import com.example.jmjapp.databinding.ActivityOrderBinding;
import com.example.jmjapp.databinding.ActivityPaymentResultBinding;

public class PaymentResultActivity extends AppCompatActivity {
    ActivityPaymentResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}