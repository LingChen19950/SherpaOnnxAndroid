package com.lc.sherpa.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {

    // 全局唯一单例
    private static Gson sGson;

    // 私有化构造
    private GsonUtil() {}

    // 获取单例
    public static Gson get() {
        if (sGson == null) {
            // 在这里统一配置 Gson
            sGson = new GsonBuilder()
                    .setPrettyPrinting() // 格式化输出（调试用），有缩进、换行，release 版本可以关闭，节省体积
                    .serializeNulls()    // 输出 null 字段
                    .setDateFormat("yyyy-MM-dd HH:mm:ss") // 日期格式
                    .create();
        }
        return sGson;
    }
}