package com.lc.sherpa.utils;

import android.util.Log;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadUtils {

    private static final String TAG = "DownloadUtils";

    // 🔥 自定义 OkHttpClient：设置超短超时时间（2秒）
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(2, TimeUnit.SECONDS)   // 连接超时 2 秒
            .readTimeout(2, TimeUnit.SECONDS)      // 读取超时 2 秒
            .writeTimeout(2, TimeUnit.SECONDS)     // 写入超时 2 秒
            .build();

    // 国内加速地址池
    private static final String[] URL_POOL = {
            "https://raw.gitmirror.com/LingChen19950/SherpaOnnxAndroid/main/app/src/main/assets/models.json",
            "https://cdn.jsdelivr.net/gh/LingChen19950/SherpaOnnxAndroid@main/app/src/main/assets/models.json",
            "https://mirror.ghproxy.com/https://raw.githubusercontent.com/LingChen19950/SherpaOnnxAndroid/main/app/src/main/assets/models.json"
    };

    private static int currentUrlIndex = 0;

    public static void updateModelsJson(DownloadCallback callback) {
        currentUrlIndex = 0;
        startRequest(callback);
    }

    // 自动重试下一个地址
    private static void startRequest(DownloadCallback callback) {
        if (currentUrlIndex >= URL_POOL.length) {
            callback.onFailure("网络异常，所有地址均无法连接");
            return;
        }

        String url = URL_POOL[currentUrlIndex];
        Log.d(TAG, "尝试下载地址：" + url);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "地址超时/失败，自动切换：" + url);
                currentUrlIndex++;
                startRequest(callback); // 立刻换下一个，不等待
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) {
                ResponseBody body = response.body();
                if (!response.isSuccessful() || body == null) {
                    response.close();
                    currentUrlIndex++;
                    startRequest(callback);
                    return;
                }

                try {
                    String content = body.string();
                    Log.d(TAG, "✅ models.json 下载成功！");
                    callback.onSuccess(content);
                } catch (Exception e) {
                    callback.onFailure("数据解析失败");
                } finally {
                    response.close();
                }
            }
        });
    }

    public interface DownloadCallback {
        void onSuccess(String content);
        void onFailure(String error);
    }
}