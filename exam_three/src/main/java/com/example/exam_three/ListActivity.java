package com.example.exam_three;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.exam_three.adapter.ListRlvAdapter;
import com.example.exam_three.bean.UserBean;
import com.example.exam_three.databinding.ActivityListBinding;
import com.example.exam_three.databinding.PopViewBinding;
import com.example.exam_three.util.DbUtil;

public class ListActivity extends AppCompatActivity {
    private ActivityListBinding root;
    private ListRlvAdapter mAdapter;
    private int mI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityListBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        initView();
        initData();
        initListener();
    }

    private void initView() {
        root.RecyclerView.setLayoutManager(new LinearLayoutManager(this));
        root.RecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new ListRlvAdapter(this);
        root.RecyclerView.setAdapter(mAdapter);
    }

    private void initData() {
        mAdapter.setList(DbUtil.getDbUtil().queryAll());
    }

    private void initListener() {
        mAdapter.setOnClick(new ListRlvAdapter.OnClick() {
            @Override
            public void onLongClickListener(int i) {
                mI = i;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,0,0,"删除");
        menu.add(0,1,0,"修改");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 0:
                mAdapter.deleteItem(mI);
                break;
            case 1:
                showPopwindows();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private void showPopwindows() {
        final PopViewBinding pwView = PopViewBinding.inflate(LayoutInflater.from(this));
        pwView.EditText.setText(mAdapter.list.get(mI).getName());
        final PopupWindow popupWindow = new PopupWindow(pwView.getRoot(), -1, -2);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAtLocation(root.RecyclerView, Gravity.CENTER,0,0);
        pwView.btnNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        pwView.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etName = pwView.EditText.getText().toString();
                UserBean userBean = mAdapter.list.get(mI);
                userBean.setName(etName);
                mAdapter.updateItem(mI,userBean);
            }
        });
    }
}
