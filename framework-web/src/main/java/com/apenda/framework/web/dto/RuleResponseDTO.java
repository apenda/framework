package com.apenda.framework.web.dto;

import lombok.Data;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/10/02 00:04
 **/
@Data
public class RuleResponseDTO {

    /**
    * 条件规则
    */
    private List<RuleDTO> ruleList;

}
