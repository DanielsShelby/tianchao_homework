package com.example.day_02_git.fragment;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.day_02_git.util.EventMessage;
import com.example.day_02_git.util.UpdateService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * A simple {@link Fragment} subclass.
 */
public class DownLoadFragment extends Fragment {
    private com.example.day_02_git.databinding.FragmentDownLoadBinding root;
    private UpdateService mService;

    public DownLoadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = com.example.day_02_git.databinding.FragmentDownLoadBinding.inflate(inflater, container, false);
        return root.getRoot();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        EventBus.getDefault().register(this);
        Intent intent = new Intent(getContext(), UpdateService.class);
        UpdateConnection conn = new UpdateConnection();
        getContext().bindService(intent, conn, Context.BIND_AUTO_CREATE);
        initView();
    }

    private void initView() {
        root.Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mService.downLoadFile();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void downLoad(EventMessage msg) {
        root.ProgressBar.setMax((int) msg.contentLength);
        root.ProgressBar.setProgress(msg.count);

        root.TextView.setText("下载进度: " + msg.count * 100 / msg.contentLength);
    }

    class UpdateConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            UpdateService.UpdateBinder updateBinder = (UpdateService.UpdateBinder) service;
            mService = updateBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
