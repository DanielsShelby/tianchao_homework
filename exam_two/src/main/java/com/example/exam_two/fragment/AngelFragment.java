package com.example.exam_two.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.exam_two.R;
import com.example.exam_two.databinding.FragmentAngelBinding;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AngelFragment extends Fragment {
    private final int mPage;
    private final int mSize;
    private final String mImg;
    private FragmentAngelBinding root;

    public AngelFragment(String img, int page, int size) {
        // Required empty public constructor
        mImg = img;
        mPage = page;
        mSize = size;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = FragmentAngelBinding.inflate(inflater, container, false);
        return root.getRoot();
    }

    @Override
    public void onActivityCreated( Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        Glide.with(this).load(mImg).into(root.ImageView);
        root.TextView.setText( mPage + "/" + mSize);
    }
}
