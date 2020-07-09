package com.example.exam_three;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.exam_three.bean.UserBean;
import com.example.exam_three.databinding.ActivityMainBinding;
import com.example.exam_three.util.DbUtil;

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
        root.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent, 100);
            }
        });

        root.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserBean item = DbUtil.getDbUtil().queryItem(root.etUser.getText().toString());
                if (item == null){
                    Toast.makeText(MainActivity.this, "您输入的信息有误", Toast.LENGTH_SHORT).show();
                }else {
                    startActivity(new Intent(MainActivity.this, ListActivity.class));
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == 200) {
            root.etUser.setText(data.getStringExtra("name"));
            root.etPwd.setText(data.getStringExtra("pwd"));
        }
    }
}
