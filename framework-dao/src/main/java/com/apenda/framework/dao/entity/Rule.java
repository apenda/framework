package com.apenda.framework.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author rui.zhou
 * @since 2021-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("rule")
public class Rule extends Model<Rule> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 条件规则名
     */
    @TableField("rule_name")
    private String ruleName;

    /**
     * 条件规则组
     */
    @TableField("rule_group")
    private String ruleGroup;

    /**
     * 条件表达式
     */
    @TableField("expression")
    private String expression;

    /**
     * 表达式语言
     */
    @TableField("expression_language")
    private String expressionLanguage;

    /**
     * 是否公共规则 0--普通规则, 1--公共规则
     */
    @TableField("is_common_rule")
    private Boolean commonRule;

    /**
     * 创建日期
     */
    @TableField("create_date")
    private Date createDate;

    /**
     * 更新日期
     */
    @TableField("update_date")
    private Date updateDate;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
