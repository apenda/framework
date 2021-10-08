package com.apenda.framework.web.query.rule;

import lombok.Data;
import java.util.Set;

/**
 * @author rui.zhou
 * @date 2021/10/04 14:29
 **/
@Data
public class BindRuleQuery {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 外键ID
     */
    private Set<Long> rids;

    /**
     * 业务规则名
     */
    private String businessName;

    /**
     * 业务
     */
    private String business;

    /**
     * 规则组
     */
    private String ruleGroup;

}
