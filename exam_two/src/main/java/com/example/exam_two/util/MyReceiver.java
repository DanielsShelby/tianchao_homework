package com.example.exam_two.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.exam_two.ImagePageActivity;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, intent.getStringExtra("title"), Toast.LENGTH_SHORT).show();
        Intent intent1 = new Intent(context, ImagePageActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent1);
    }
}
