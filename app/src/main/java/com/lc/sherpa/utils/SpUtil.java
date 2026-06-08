package com.lc.sherpa.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Map;
import java.util.Set;

/**
 * SharedPreferences 工具类
 * 提供简洁的 SP 读写操作
 */
public class SpUtil {

    private static final String TAG = "SpUtil";
    private static final String DEFAULT_SP_NAME = "sherpa_onnx_prefs";

    // 模型数据
    public static final String KEY_MODELS_JSON = "models_json_data";

    private static volatile SpUtil instance;
    private final SharedPreferences sharedPreferences;

    private SpUtil(@NonNull String spName) {
        this.sharedPreferences = Utils.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
    }

    /**
     * 获取单例实例（使用默认 SP 名称）
     */
    public static SpUtil getInstance() {
        return getInstance(DEFAULT_SP_NAME);
    }

    /**
     * 获取单例实例（自定义 SP 名称）
     *
     * @param spName SP 文件名
     */
    public static SpUtil getInstance(@NonNull String spName) {
        if (instance == null) {
            synchronized (SpUtil.class) {
                if (instance == null) {
                    instance = new SpUtil(spName);
                    Log.d(TAG, "SpUtil 已初始化，SP名称: " + spName);
                }
            }
        }
        return instance;
    }

    // ==================== 保存数据 ====================

    /**
     * 保存字符串
     */
    public void putString(@NonNull String key, @Nullable String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    /**
     * 保存整数
     */
    public void putInt(@NonNull String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    /**
     * 保存长整型
     */
    public void putLong(@NonNull String key, long value) {
        sharedPreferences.edit().putLong(key, value).apply();
    }

    /**
     * 保存浮点型
     */
    public void putFloat(@NonNull String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    /**
     * 保存布尔型
     */
    public void putBoolean(@NonNull String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    /**
     * 保存字符串集合
     */
    public void putStringSet(@NonNull String key, @Nullable Set<String> values) {
        sharedPreferences.edit().putStringSet(key, values).apply();
    }

    // ==================== 读取数据 ====================

    /**
     * 获取字符串
     */
    @Nullable
    public String getString(@NonNull String key) {
        return getString(key, null);
    }

    /**
     * 获取字符串（带默认值）
     */
    @Nullable
    public String getString(@NonNull String key, @Nullable String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    /**
     * 获取整数
     */
    public int getInt(@NonNull String key) {
        return getInt(key, 0);
    }

    /**
     * 获取整数（带默认值）
     */
    public int getInt(@NonNull String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }

    /**
     * 获取长整型
     */
    public long getLong(@NonNull String key) {
        return getLong(key, 0L);
    }

    /**
     * 获取长整型（带默认值）
     */
    public long getLong(@NonNull String key, long defaultValue) {
        return sharedPreferences.getLong(key, defaultValue);
    }

    /**
     * 获取浮点型
     */
    public float getFloat(@NonNull String key) {
        return getFloat(key, 0f);
    }

    /**
     * 获取浮点型（带默认值）
     */
    public float getFloat(@NonNull String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    /**
     * 获取布尔型
     */
    public boolean getBoolean(@NonNull String key) {
        return getBoolean(key, false);
    }

    /**
     * 获取布尔型（带默认值）
     */
    public boolean getBoolean(@NonNull String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * 获取字符串集合
     */
    @Nullable
    public Set<String> getStringSet(@NonNull String key) {
        return getStringSet(key, null);
    }

    /**
     * 获取字符串集合（带默认值）
     */
    @Nullable
    public Set<String> getStringSet(@NonNull String key, @Nullable Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    // ==================== 删除和清空 ====================

    /**
     * 移除指定键值对
     */
    public void remove(@NonNull String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    /**
     * 清空所有数据
     */
    public void clear() {
        sharedPreferences.edit().clear().apply();
        Log.d(TAG, "SP 数据已清空");
    }

    // ==================== 查询操作 ====================

    /**
     * 判断是否包含某个键
     */
    public boolean contains(@NonNull String key) {
        return sharedPreferences.contains(key);
    }

    /**
     * 获取所有数据
     */
    @Nullable
    public Map<String, ?> getAll() {
        return sharedPreferences.getAll();
    }

    /**
     * 获取原始的 SharedPreferences 对象（用于高级操作）
     */
    @NonNull
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
