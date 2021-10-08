package com.apenda.framework.core.rule;

import lombok.Data;
import java.util.List;

/**
 * @author rui.zhou
 * @date 2021/10/03 21:51
 **/
@Data
public class Result {

    /**
    * 错误码
    */
    private Integer code;

    /**
    * 消息
    */
    private String message;

    /**
    * 没通过的规则
    */
    private List<Long> failIds;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess(){
        return this.getCode() == 0;
    }

}
