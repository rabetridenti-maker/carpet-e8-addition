# e8地毯附属（E8 Carpet Addition）

Carpet 扩展骨架，Minecraft **1.21.1**（多版本预处理结构，当前只装配 1.21.1 单节点）。

**中文** | [English](README_en.md)

## 依赖

| 名称 | 类型 |
| --- | --- |
| [Fabric Loader](https://fabricmc.net/use/installer/) `>=0.15.11` | 必需 |
| [Fabric API](https://modrinth.com/mod/fabric-api) | 必需 |
| [Carpet](https://modrinth.com/mod/carpet) `>=1.4.147` | 必需 |

## 版本支持

| 游戏版本 | 状态 |
| --- | --- |
| 1.21.1 | 开发中 |

## 构建

```powershell
.\gradlew.bat :1.21.1:build          # 构建 1.21.1
.\gradlew.bat :1.21.1:compileJava    # 只编译
.\gradlew.bat buildAllVersions       # 构建 settings.json.publishVersions 全部版本
```

开发要求 JDK 21（26.x 节点需要 JDK 25）。产物在 `versions/1.21.1/build/libs/`。

## 开发约定

见 [AGENTS.md](AGENTS.md)：固定项目标识、多版本预处理（`//#if` / `//$$`）规则、
规则/命令/翻译的同步清单、验证命令。

## 许可证

[MIT License](LICENSE)
