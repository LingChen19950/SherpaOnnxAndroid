# SherpaOnnxAndroid
**完全离线的 Android 端语音工具箱(java项目) | 实时识别 · 文件转写 · 语音合成 · 模型管理**

---

## ✨ 核心特性
- 🔇 **全离线运行**：所有语音处理本地完成，不上传任何数据
- 🎙️ **实时语音识别**：低延迟流式 ASR，支持 VAD 自动断句
- 📄 **音频文件转写**：支持 WAV/MP3/OGG，后台断点续转
- 🔊 **离线语音合成**：多发音人、语速可调，导出 WAV
- 📦 **模型自由管理**：下载/切换/删除/自定义 ONNX 模型导入
- 🎨 **Material You 风格**：明暗双主题，动态颜色适配
- ♿ **无障碍友好**：支持 TalkBack、字体缩放
- 🧩 **开源模块化**：Apache-2.0 协议，可二次开发

---

## 📱 支持版本
- **最低支持**：Android 7.0+ (API 24+)
- **编译版本**：Android 14 (API 34)
- **架构**：armeabi-v7a / arm64-v8a

---

## 🚀 快速开始
1. 安装 APK 或从源码编译
2. 首次打开授权**麦克风**与**存储权限**
3. 进入「模型」下载所需离线模型
4. 开始使用实时识别、文件转写、语音合成

---

## 🛠️ 功能清单
### ✅ M1 已支持
- 实时语音识别（Live ASR）
- 本地音频文件转写
- 离线 TTS 语音合成
- 模型下载、切换、管理
- 历史记录、文本编辑、导出 TXT/SRT

### 🔄 规划中（M2/M3）
- 关键词唤醒（KWS）
- 离线语音命令
- 说话人声纹识别
- 多语言扩展

---

## 🧱 技术栈
- 语言：Java
- 架构：单 Activity + MVVM + Navigation
- 引擎：sherpa-onnx (JNI)
- 后台：Foreground Service
- 设计：Material Design 3

---

## 📦 构建与开发
详细开发环境、编译、签名、调试指南请查看：
**[DEVELOPMENT.md](DEVELOPMENT.md)**

### 快速编译
```bash
./gradlew assembleDebug
```

---

## 📂 输出目录
- 调试包：`app/build/outputs/apk/debug/`
- 正式包：`app/build/outputs/apk/release/`
- 导出文件：`/Documents/SherpaToolbox/`

---

## ⚠️ 权限说明
本应用仅申请**必要权限**，无多余获取：
- RECORD_AUDIO：语音识别、录音
- READ_EXTERNAL_STORAGE：读取音频文件
- WRITE_EXTERNAL_STORAGE：保存转写/合成结果
- INTERNET：网络访问，用于下载模型

---

## 📄 开源协议
**Apache-2.0**
可自由使用、修改、分发，商用需保留版权声明。
模型遵循各自开源协议，详见应用内关于页面。