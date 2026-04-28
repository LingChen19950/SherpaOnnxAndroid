Sherpa Voice Toolbox 开发指南（v1.0）
Sherpa Voice Toolbox
[DEVELOPMENT.md](DEVELOPMENT.md) — 开发环境与构建指南
- 版本：v1.0
- 适配：Android 7.0+ (API 24+)
- 架构：单Activity + MVVM + Navigation + Room + sherpa-onnx
- 协议：Apache-2.0

---
📌 1. 项目概述
Sherpa Voice Toolbox 是一款完全离线的 Android 端语音工具箱，基于 sherpa-onnx 引擎实现：
- 实时语音识别（ASR）
- 本地音频文件转写
- 离线语音合成（TTS）
- 模型下载 / 导入 / 管理
- 全本地处理，不上传数据
本文档用于环境搭建、编译构建、调试、打包发布，面向所有开发者与贡献者。

---
🧱 2. 最低开发环境要求
工具 / 组件
版本
- Android Studio Panda 3 | 2025.3.3 Patch 1
- JDK 17
- Android Gradle Plugin 9.1.1
- Gradle 9.3.1
- Android SDK (Compile)API 36 (Android 16)
- Android SDK (minSdk)API 24 (Android 7.0)
- NDK 25.2.9519653
- 运行设备 真机 处理器 骁龙8至尊版

---
🚀 3. 开发环境搭建
- 安装 Android Studio 下载：https://developer.android.com/studio

- 配置 JDK 17
Android Studio Hedgehog 及以上自带 JDK 17，无需手动安装。

---
📥 4. 项目拉取与初始化
- git clone https://github.com/LingChen19950/SherpaOnnxAndroid.git

---
📦 5. 项目依赖说明

5.1 核心依赖
- AndroidX Core / Fragment / Navigation
- Material Design 3
- Room (SQLite) 本地存储
- OkHttp 模型下载
- Foreground Service 后台任务

5.2 sherpa-onnx 引擎
- 预编译 AAR 存放位置：
- app/libs/sherpa-onnx.aar
- 升级引擎只需替换该 AAR 并同步 Gradle。

---
🐛 6. 调试与日志

6.1 开启调试日志
local.properties 添加：
debug.logging=true

6.2 常用 ADB 命令
#过滤日志
adb logcat -s Sherpa

#清除应用数据
adb shell pm clear com.sherpa.voice.toolbox

#停止应用
adb shell am force-stop com.sherpa.voice.toolbox

6.3 调试注意事项
- 录音、识别、转写必须真机运行
- 首次启动必须授权：录音、存储权限
- 模拟器音频采集不稳定，可能导致功能异常

---
📂 7. 项目目录结构（开发必读）
SherpaVoiceToolbox/
├─ app/
│  ├─ src/main/java/com/lc/sherpa/
│  │  ├─ ui/                  # Fragment + ViewModel
│  │  └─ utils/                # 工具类
│  ├─ src/main/res/            # 布局、图标、字符串
│  └─ libs/                    # 本地 AAR（sherpa-onnx）
├─ gradle/
├─ local.properties
├─ signing.properties
└─ README.md / DEVELOPMENT.md

---
🧪 8. 常见问题与解决方案

8.1 录音权限导致崩溃
解决：
- 系统设置手动开启录音权限
- 按 App 内授权引导操作

8.2 sherpa-onnx 加载失败
解决：
- 确认 app/libs/sherpa-onnx.aar 存在
- Clean → Rebuild Project

8.3 模型无法下载 / 导入
解决：
- 开启存储权限
- 导入模型必须包含：.onnx + tokens.txt和其他必要文件

---
🧾 9. 分支规范
- main：稳定发布版
- develop：开发主干
- feature/*：新功能分支
- bugfix/*：修复分支

---
📏 10. 构建质量要求
- debug 基础包体积 ≤ 100 MB
- 带模型基础包 ≤ 300 MB
- 冷启动时间 ≤ 2 秒
- 核心功能崩溃率 0
- 整体崩溃率< 0.1%

---
📄 11. 开源许可证
项目基于 Apache-2.0 协议开源，可自由使用、修改、分发。
内置模型遵循模型自身协议，详见应用内 “关于” 页面。