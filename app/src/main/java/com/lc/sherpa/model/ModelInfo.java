package com.lc.sherpa.model;

public class ModelInfo {
    public String id;           // 唯一标识
    public String name;         // 名称
    public String type;         // ASR / TTS
    public String size;         // 文件大小
    public String fileName;     // 文件名（核心匹配用）
    public String url;          // 下载地址（线上才有）

    public boolean isDownloaded;    // 是否本地存在
    public boolean isDefault;       // 是否默认
    public boolean isLocalImport;   // 是否用户导入
}