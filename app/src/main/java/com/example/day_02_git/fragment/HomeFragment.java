package com.example.day_02_git.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.day_02_git.R;
import com.example.day_02_git.adapter.HomeRlvAdapter;
import com.example.day_02_git.bean.InfoDataBean;
import com.example.day_02_git.mvp.MvpInterface;
import com.example.day_02_git.mvp.Presenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements MvpInterface.IView {
    private com.example.day_02_git.databinding.FragmentHomeBinding root;
    private HomeRlvAdapter mAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = com.example.day_02_git.databinding.FragmentHomeBinding.inflate(inflater, container, false);
        return root.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Presenter(this).getInfoData();
        initView();
    }

    private void initView() {
        root.RecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        root.RecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new HomeRlvAdapter(getContext());
        root.RecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSucceed(InfoDataBean infoDataBean) {
        mAdapter.setList(infoDataBean.getData().getDatas());
    }

    private static final String TAG = "HomeFragment";

    @Override
    public void onFiled(String error) {
        Log.d(TAG, "onFiled: " + error);
    }
}
