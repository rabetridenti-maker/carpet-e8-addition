package e8.carpet;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;

import java.util.Map;

/**
 * E8 Carpet Extension 实现。
 * 规则直接注册到 carpet 主 SettingsManager（/carpet 命令），不创建独立命令。
 */
public class E8Extension implements CarpetExtension {

    @Override
    public void onGameStarted() {
        // 规则注册的唯一姿势：parseSettingsClass 扫描 @Rule 注解的静态字段。
        SettingsManager carpetManager = CarpetServer.settingsManager;
        if (carpetManager != null) {
            carpetManager.parseSettingsClass(E8Settings.class);
        }
    }

    /**
     * 返回 null 表示不创建独立命令，规则统一由 /carpet 管理。
     */
    @Override
    public SettingsManager extensionSettingsManager() {
        return null;
    }

    /**
     * 向 Carpet 提供本扩展规则的翻译（分类名、规则名、描述）。
     */
    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return E8Translations.getTranslations(lang);
    }

    @Override
    public String version() {
        return E8CarpetAddition.MOD_ID;
    }
}
