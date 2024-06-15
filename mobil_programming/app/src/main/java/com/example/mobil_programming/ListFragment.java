package com.example.mobil_programming;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListFragment extends Fragment {
    ListView lvVersions;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list, container, false);
        lvVersions = v.findViewById(R.id.lv_Version);
        String[] androidVersions = getResources().getStringArray(R.array.AndroidVersions);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, androidVersions);
        lvVersions.setAdapter(adapter);

        lvVersions.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get clicked item
                String selectedVersion = androidVersions[position];

                // Sending Data
                DetailFragment detailFragment = new DetailFragment();

                // Sending data with bundle
                Bundle bundle = new Bundle();
                bundle.putString("version", selectedVersion);
                detailFragment.setArguments(bundle);

                // Changing Fragment
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.container, detailFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return v;
    }
}