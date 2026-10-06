package e8.carpet;

import carpet.CarpetServer;
import net.fabricmc.api.ModInitializer;

/**
 * e8地毯附属（E8 Carpet Addition）模组入口。
 * 在 Fabric ModInitializer 阶段注册 Carpet 扩展。
 */
public class E8CarpetAddition implements ModInitializer {

    public static final String MOD_ID = "e8-carpet-addition";

    @Override
    public void onInitialize() {
        // Carpet 官方注册方式：普通 ModInitializer 里调用 manageExtension，
        // 必须早于 Carpet 启动。不要用 mixin 注入 CarpetServer 注册（Carpet 官方明确警告会崩）。
        CarpetServer.manageExtension(new E8Extension());
    }
}
