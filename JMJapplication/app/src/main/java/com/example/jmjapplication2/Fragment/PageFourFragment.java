package com.example.jmjapplication2.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import com.example.jmjapplication2.R;

public class PageFourFragment extends Fragment {
    View view;

    public static PageFourFragment newInstance() {
        // Required empty public constructor
        Bundle args = new Bundle();
        PageFourFragment fragment = new PageFourFragment();
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
