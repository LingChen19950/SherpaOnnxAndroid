package com.lc.sherpa.utils;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.reflect.TypeToken;
import com.lc.sherpa.model.ModelInfo;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ModelsJsonManager {

    private static final String TAG = "ModelsJsonManager";

    private static volatile ModelsJsonManager instance;
    private final List<ModelInfo> cachedModels = new ArrayList<>();

    private ModelsJsonManager() {
    }

    public static ModelsJsonManager getInstance() {
        if (instance == null) {
            synchronized (ModelsJsonManager.class) {
                if (instance == null) {
                    instance = new ModelsJsonManager();
                }
            }
        }
        return instance;
    }

    public synchronized List<ModelInfo> getModels() {
        // 从内存缓存获取
        if (!ListUtils.isEmpty(cachedModels)) {
            Log.d(TAG, "从内存缓存获取模型列表，数量: " + cachedModels.size());
            return cachedModels;
        }

        // 从SP中获取
        String spContent = SpUtil.getInstance().getString(SpUtil.KEY_MODELS_JSON);
        List<ModelInfo> spList = getModelsFromJson(spContent);
        if (!ListUtils.isEmpty(spList)) {
            Log.d(TAG, "从SP缓存获取模型列表，数量: " + spList.size());
            cachedModels.clear();
            cachedModels.addAll(spList);
            return spList;
        }

        String assetsModels = AssetUtil.readTextFile("models.json");
        List<ModelInfo> assetsList = getModelsFromJson(assetsModels);
        if (!ListUtils.isEmpty(assetsList)) {
            Log.d(TAG, "从assets中获取模型列表，数量: " + assetsList.size());
            cachedModels.clear();
            cachedModels.addAll(assetsList);
            return assetsList;
        }

        Log.d(TAG, "全部无数据，异常状态");
        return new ArrayList<>();
    }

    public void updateFromNetwork(@NonNull DownloadUtils.DownloadCallback callback) {
        Log.d(TAG, "开始从网络更新模型数据");

        DownloadUtils.updateModelsJson(new DownloadUtils.DownloadCallback() {
            @Override
            public void onSuccess(String content) {
                Log.d(TAG, "网络数据获取成功 " + content);
                List<ModelInfo> models = getModelsFromJson(content);
                if (ListUtils.isEmpty(models)) {
                    Log.e(TAG, "数据验证失败");
                    callback.onFailure("数据格式错误");
                    return;
                }
                Log.d(TAG, "模型数据更新成功，数量: " + models.size());
                SpUtil.getInstance().putString(SpUtil.KEY_MODELS_JSON, content);
                cachedModels.clear();
                cachedModels.addAll(models);
                callback.onSuccess(content);
            }

            @Override
            public void onFailure(String error) {
                Log.e(TAG, "网络更新失败: " + error);
                callback.onFailure(error);
            }
        });
    }

    /**
     * JSON 转 LIST
     */
    private List<ModelInfo> getModelsFromJson(String json) {
        if (json == null || json.trim().isEmpty()) {
            return new ArrayList<>();
        }

        try {
            Type type = new TypeToken<List<ModelInfo>>() {
            }.getType();
            return GsonUtil.get().fromJson(json, type);
        } catch (Exception e) {
            Log.e(TAG, "JSON转换异常: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
