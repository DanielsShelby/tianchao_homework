package com.example.day_02_git.util;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UpdateService extends Service {
    UpdateBinder mBinder = new UpdateBinder();

    public class UpdateBinder extends Binder {
        public UpdateService getService() {
            return UpdateService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    private static final String TAG = "UpdateService";

    public void downLoadFile() {
        new OkHttpClient.Builder()
                .build()
                .newCall(new Request.Builder().url(ApiService.dlFile).build())
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.d(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        ResponseBody body = response.body();
                        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "a.apk");
                        saveFile(body.byteStream(), file, body.contentLength());
                    }
                });
    }

    private void saveFile(InputStream inputStream, File file, long contentLength) {
        try {
            int count = 0;
            int length = -1;
            byte[] by = new byte[1024 * 1024 * 10];
            FileOutputStream outputStream = new FileOutputStream(file);
            EventMessage msg = new EventMessage();
            while ((length = inputStream.read(by)) != -1) {
                outputStream.write(by, 0, length);
                count += length;
                msg.count = count;
                msg.contentLength = contentLength;
                EventBus.getDefault().postSticky(msg);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
