# E8 Carpet Addition 项目指令

e8 地毯附属是基于 Minecraft Carpet Mod 的 Carpet Extension（多版本预处理骨架，当前只装配 1.21.1）。
本文件只写长期有效的工作规则；项目事实以当前源码、构建配置和 `docs/` 为准。

## 固定项目标识（除非明确要求重命名，否则不要改）

| 类型 | 固定值 |
| --- | --- |
| 项目名 | `e8地毯附属`（E8 Carpet Addition） |
| Mod ID | `e8-carpet-addition` |
| Java 主包 | `e8.carpet` |
| Maven Group | `e8` |
| Archives Base Name | `e8-carpet-addition` |
| Fabric 入口 | `e8.carpet.E8CarpetAddition` |
| Carpet Extension | `e8.carpet.E8Extension` |
| 规则类 | `e8.carpet.E8Settings` |
| Carpet 分类 | `E8` |
| Mixin Package | `e8.carpet.mixin` |
| Resource Namespace | `e8-carpet-addition` |

相关标识改动必须同步检查 `gradle.properties`、`fabric.mod.json`、Java 常量、lang 资源与 mixins.json。

## Carpet 接入方式（唯一正确姿势）

- 入口只声明普通 `main` entrypoint，在 `onInitialize()` 里 `CarpetServer.manageExtension(new E8Extension())`。
- **不要**用 mixin 注入 `CarpetServer` 注册扩展（Carpet 官方明确警告会崩）。
- 规则注册：`CarpetServer.settingsManager.parseSettingsClass(E8Settings.class)`（在 `onGameStarted()`）。
- `extensionSettingsManager()` 返回 `null` = 规则统一进 `/carpet`，不建独立命令。
- 规则注解用 `carpet.api.settings.Rule`；描述翻译走 `canHasTranslations` + `assets/e8-carpet-addition/lang/`。

## 规则 / 命令 / 翻译

新增或修改规则时至少检查：

- `E8Settings.java`（字段、categories、options、validators）
- `E8Extension.java`（注册链路）
- `assets/e8-carpet-addition/lang/en_us.json` 与 `zh_cn.json`（`carpet.category.E8`、`carpet.rule.<名>.name` / `.desc`）
- 规则介绍正文不用句号；`false`/`ops`/`0`-`4` 之外还有两个以上非自定义选项时逐项换行说明

## 多版本 Preprocessor（Fallen-Breath）

以下不是普通注释，**严禁被格式化或清理**：

```java
//#if MC >= 1.21
...
//#else
//$$ ...
//#endif
```

- 源码以 `versions/mainProject`（当前 1.21.1）为方言根；`//$$` 行 = 其他版本激活的行。
- 版本相关 import 单独放，位于其他 import 之后。
- 新增版本：`settings.json` + `versions/<版本>/gradle.properties` + `build.gradle` 的 createNode/link + 逐处补 `//#if`；不要在 `versions/<版本>/` 复制整类绕过预处理。
- 26.x 起 `unobfuscated = mcVersion >= 26_00_00` 切换 Loom 插件/依赖写法/Java 版本（见 `common.gradle`）。

## Mixin

- `e8-carpet-addition.mixins.json` 是跨版本预处理资源；保持 `required: true` 与 `defaultRequire: 1`。
- 不要用 `required=false` / `require=0` / 删失败 mixin / 静默捕获来掩盖兼容问题；Mixin 失败优先查目标类签名、descriptor、注入点与预处理条件。
- 禁止 `@Overwrite`；优先 `@Inject`/`@Redirect`/`@ModifyVariable`，规则关闭时尽早返回。

## 构建与验证

```powershell
git diff --check                    # 仅文档改动
.\gradlew.bat :1.21.1:compileJava   # 改了 Java/Mixin/资源/构建配置
.\gradlew.bat :1.21.1:build         # 完整打包
```

- 跨版本共享代码必须验证所有受影响版本。
- **没有实际运行就不要写「测试通过」**；编译通过不等于游戏内测试通过。
- 不能验证的内容明确说明，不确定的信息标记为「待确认项」。

## 默认不要修改

`README.md`、`LICENSE`、`.gitignore`、`.github/workflows/`、`gradle/wrapper/`，以及与任务无关的 Gradle 配置。

## 不提交

任何 Token、Secret 或个人环境配置。
