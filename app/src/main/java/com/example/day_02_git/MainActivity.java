package com.example.day_02_git;

import android.Manifest;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.day_02_git.databinding.ActivityMainBinding;
import com.example.day_02_git.fragment.DownLoadFragment;
import com.example.day_02_git.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        String[] arr = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this, arr, 100);
        initView();
    }

    private void initView() {
        final List<Fragment> list = new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new DownLoadFragment());
        root.ViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        root.TabLayout.setupWithViewPager(root.ViewPager);
        root.TabLayout.getTabAt(0).setText("首页");
        root.TabLayout.getTabAt(1).setText("下载");
    }
}
