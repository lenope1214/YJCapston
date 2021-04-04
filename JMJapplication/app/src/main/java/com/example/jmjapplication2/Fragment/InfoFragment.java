package com.example.jmjapplication2.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {

    public static Fragment newInstance() {
        Bundle args = new Bundle();
        InfoFragment fragment = new InfoFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
