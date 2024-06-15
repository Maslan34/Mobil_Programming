package com.example.mobil_programming;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_detail, container, false);

        // Get data from Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String version = bundle.getString("version");

            // Place data in TextView
            TextView tvVersionDetail = v.findViewById(R.id.tv_AndroidName);
            tvVersionDetail.setText(version);
        }

        return v;
    }
}
