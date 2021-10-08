package com.apenda.framework.web.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/10/04 15:28
 **/
@Data
public class RuleBusinessDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 绑定规则集合
     */
    private List<RuleDTO> ruleList;

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

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

}
