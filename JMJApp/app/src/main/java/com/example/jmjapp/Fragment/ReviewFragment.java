package com.example.jmjapp.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class ReviewFragment extends Fragment {

    public static ReviewFragment newInstance() {
        Bundle args = new Bundle();
        ReviewFragment fragment = new ReviewFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
