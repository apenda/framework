package com.apenda.framework.core.rule;

/**
 * @author rui.zhou
 * @date 2021/10/01 01:36
 **/
public enum RuleEnum {

    TEST_RULE("testGroup", "testRule");

    /**
     * 规则组
     */
    private String group;

    /**
     * 规则名称
     */
    private String name;

    /**
     * 规则名称
     */
    private String expression;

    RuleEnum(String group, String name) {
        this.name = name;
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }


}
