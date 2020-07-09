package com.example.exam_two;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.exam_two.databinding.ActivityImagePageBinding;
import com.example.exam_two.fragment.AngelFragment;
import com.example.exam_two.util.EventMessage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class ImagePageActivity extends AppCompatActivity {
    private ActivityImagePageBinding root;
    private List<String> mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityImagePageBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        EventBus.getDefault().register(this);
        initView();
    }

    private void initView() {
        final List<Fragment> list = new ArrayList();
        for (int i = 0; i < mImage.size(); i++) {
            list.add(new AngelFragment(mImage.get(i), i + 1, mImage.size()));
        }
        root.ViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }
        });
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onSucceed(EventMessage msg) {
        mImage = new ArrayList();
        mImage.addAll(msg.list);
    }

    private static final String TAG = "ImagePageActivity";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
