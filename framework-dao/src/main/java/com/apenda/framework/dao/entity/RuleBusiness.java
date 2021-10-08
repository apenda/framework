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
@TableName("rule_business")
public class RuleBusiness extends Model<RuleBusiness> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 外键ID
     */
    @TableField("rid")
    private Long rid;

    /**
     * 业务规则名
     */
    @TableField("business_name")
    private String businessName;

    /**
     * 业务
     */
    @TableField("business")
    private String business;

    /**
     * 规则组
     */
    @TableField("rule_group")
    private String ruleGroup;

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
