package com.example.exam_five.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.exam_five.R;
import com.example.exam_five.databinding.FragmentAngelBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class AngelFragment extends Fragment {
    private FragmentAngelBinding root;

    public AngelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = FragmentAngelBinding.inflate(inflater, container, false);
        return root.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

    }
}
