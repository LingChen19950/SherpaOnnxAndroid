package com.lc.sherpa.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadUtils {

    private static final String TAG = "DownloadUtils";

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    // CDN 国内节点
    private static final String MODELS_JSON_URL = "https://cdn.jsdelivr.net/gh/LingChen19950/SherpaOnnxAndroid@main/models.json";

    public static void updateModelsJson(DownloadCallback callback) {
        Request request = new Request.Builder()
                .url(MODELS_JSON_URL)
                .get()
                .build();

        OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                String errorMsg = "网络请求失败：" + e.getMessage();
                Log.e(TAG, errorMsg, e);
                if (callback != null) {
                    callback.onFailure(errorMsg);
                }
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody responseBody = response.body();
                if (!response.isSuccessful() || responseBody == null) {
                    String errorMsg = "服务器响应失败，状态码：" + response.code();
                    Log.e(TAG, errorMsg);
                    if (callback != null) {
                        callback.onFailure(errorMsg);
                    }
                    response.close();
                    return;
                }

                try (response) {
                    String content = responseBody.string();
                    Log.d(TAG, "models.json 下载成功：" + content.length() + " 字节");
                    if (callback != null) {
                        callback.onSuccess(content);
                    }
                } catch (Exception e) {
                    String errorMsg = "解析数据失败：" + e.getMessage();
                    Log.e(TAG, errorMsg, e);
                    if (callback != null) {
                        callback.onFailure(errorMsg);
                    }
                }
            }
        });
    }

    public interface DownloadCallback {
        void onSuccess(String content);
        void onFailure(String error);
    }

}