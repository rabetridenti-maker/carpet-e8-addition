package e8.carpet;

import carpet.api.settings.Rule;
import carpet.api.settings.RuleCategory;

/**
 * E8 规则集合（注册进 /carpet，分类 E8）。
 *
 * 约定（参考 Carpet FGA Addition 的做法）：
 * - 字段名 = 规则名，初值 = 默认值；字段类型限 boolean / int / double / String / long / float / 枚举。
 * - categories 必填；自定义分类常量（本类 E8）会被 Carpet 注册，并需要在 lang 里提供
 *   carpet.category.E8 翻译。
 * - 布尔开关默认 false；带可选值的规则配 options + strict=false + validators。
 * - 规则语义变化时同步 docs/ 与 lang（zh_cn / en_us）。
 */
public final class E8Settings {

    /** 自定义规则分类，出现在 /carpet 的分类列表里。 */
    public static final String E8 = "E8";

    private E8Settings() {
    }

    /**
     * 骨架示例规则：注册链路验证用，确认 /carpet e8ExampleRule 可用后可删除。
     */
    @Rule(categories = {RuleCategory.FEATURE, E8})
    public static boolean e8ExampleRule = false;
}
