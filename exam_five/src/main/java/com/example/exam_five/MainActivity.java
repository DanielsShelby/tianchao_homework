package com.example.exam_five;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import android.os.Bundle;
import android.util.Log;

import com.example.exam_five.bean.InfoListBean;
import com.example.exam_five.databinding.ActivityMainBinding;
import com.example.exam_five.mvp.MvpInterface;
import com.example.exam_five.mvp.Presenter;

public class MainActivity extends AppCompatActivity implements MvpInterface.IView {
    private ActivityMainBinding root;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        new Presenter(this).getListData();
    }

    @Override
    public void onSucceed(InfoListBean infoListBean) {
        for (int i = 0; i < infoListBean.getData().size(); i++) {

        }
        root.ViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return null;
            }

            @Override
            public int getCount() {
                return 0;
            }
        });
    }

    @Override
    public void onFiled(String error) {
        Log.d(TAG, "onFiled: " + error);
    }
}
