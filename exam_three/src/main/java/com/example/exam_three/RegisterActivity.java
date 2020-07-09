package com.example.exam_three;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.exam_three.bean.UserBean;
import com.example.exam_three.databinding.ActivityRegisterBinding;
import com.example.exam_three.util.DbUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding root;
    private Intent mIntent;
    private String mPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        root = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(root.getRoot());
        mIntent = getIntent();
        initImage();
        initView();
    }

    private void initImage() {
        mPath = Environment.getExternalStorageDirectory() + File.separator + "Pictures/tager.jpg";
        try {
            FileInputStream inputStream = new FileInputStream(new File(mPath));
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            root.ImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        root.btnLogins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = root.etUser.getText().toString();
                String pwd = root.etPwd.getText().toString();
                String topwd = root.etToPwd.getText().toString();
                if (user.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    if (pwd.equals(topwd)) {
                        DbUtil.getDbUtil().inserts(new UserBean(null, user, pwd, mPath));
                        mIntent.putExtra("name", user);
                        mIntent.putExtra("pwd", pwd);
                        setResult(200, mIntent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "两次输入的密码不一致", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
