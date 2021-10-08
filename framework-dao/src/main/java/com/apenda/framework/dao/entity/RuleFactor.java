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
@TableName("rule_factor")
public class RuleFactor extends Model<RuleFactor> {

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
     * 因子名称
     */
    @TableField("factor_name")
    private String factorName;

    /**
     * 因子
     */
    @TableField("factor")
    private String factor;

    /**
     * 查询因子方法
     */
    @TableField("factor_method")
    private String factorMethod;

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
