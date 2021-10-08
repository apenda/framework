package com.apenda.framework.web.dto;

import lombok.Data;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/10/04 14:36
 **/
@Data
public class BindRuleResponseDTO {

    /**
     * 绑定规则集合
     */
    private List<RuleBusinessDTO> ruleBusinessList;

}
