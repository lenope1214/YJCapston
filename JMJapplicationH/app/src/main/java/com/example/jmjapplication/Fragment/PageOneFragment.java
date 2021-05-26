package com.example.jmjapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.jmjapplication.R;

public class PageOneFragment extends Fragment {
    View view;

    public static PageOneFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        PageOneFragment fragment = new PageOneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(view == null) {
            view = inflater.inflate(R.layout.fragment_page_one, container, false);
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}