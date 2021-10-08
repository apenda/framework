package com.apenda.framework.web.dto;

import lombok.Data;
import java.util.Date;

/**
 * @author rui.zhou
 * @date 2021/10/02 01:34
 **/
@Data
public class RuleFactorDTO {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 外键ID
     */
    private Long rid;

    /**
     * 因子名称
     */
    private String factorName;

    /**
     * 因子
     */
    private String factor;

    /**
     * 查询因子方法
     */
    private String factorMethod;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 更新日期
     */
    private Date updateDate;

}
