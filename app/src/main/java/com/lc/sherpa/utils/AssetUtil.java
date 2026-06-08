package com.lc.sherpa.utils;

import android.content.res.AssetManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Assets 资源文件读取工具类
 */
public class AssetUtil {

    private static final String TAG = "AssetUtil";

    private AssetUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 读取 assets 中的文本文件
     *
     * @param fileName 文件名（如：models.json）
     * @return 文件内容字符串，失败返回 null
     */
    @Nullable
    public static String readTextFile(@NonNull String fileName) {
        try {
            AssetManager assetManager = Utils.getContext().getAssets();
            InputStream inputStream = assetManager.open(fileName);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8)
            );

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            reader.close();
            inputStream.close();

            Log.d(TAG, "成功读取 assets 文件: " + fileName);
            return stringBuilder.toString();

        } catch (IOException e) {
            Log.e(TAG, "读取 assets 文件失败: " + fileName + ", 错误: " + e.getMessage());
            return null;
        }
    }

}
