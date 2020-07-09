package com.example.exam_two;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.exam_two.adapter.HomeRlvAdapter;
import com.example.exam_two.bean.InfoListBean;
import com.example.exam_two.databinding.ActivityMainBinding;
import com.example.exam_two.mvp.MvpInterface;
import com.example.exam_two.mvp.Presenter;
import com.example.exam_two.util.EventMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MvpInterface.IView {
    private ActivityMainBinding root;
    private HomeRlvAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        new Presenter(this).getInfoData();
        initView();
    }

    private void initView() {
        root.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        root.RecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new HomeRlvAdapter(this);
        root.RecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSucceed(InfoListBean infoDataBean) {
        List<InfoListBean.DataBean.DatasBean> datas = infoDataBean.getData().getDatas();
        mAdapter.setList(datas);
        List<String> mImage = new ArrayList();
        for (int i = 0; i < datas.size(); i++) {
            mImage.add(datas.get(i).getEnvelopePic());
        }
        EventMessage eventMessage = new EventMessage();
        eventMessage.list = mImage;
        EventBus.getDefault().postSticky(eventMessage);
    }

    @Override
    public void onFiled(String error) {
        Log.d(TAG, "onFiled: " + error);
    }

    private static final String TAG = "HomeFragment";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.unRegisterReceiver();
    }
}
