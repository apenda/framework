package com.apenda.framework.web.controller;

import com.apenda.framework.common.data.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 加解密控制器
 *
 * @author rui.zhou
 * @date 2021/08/11 17:49
 **/
@Api(tags = "加解密管理")
@RequestMapping("/field")
public interface IEncryptController {

    /**
    * 加密
    *
    * @param data 待加密数据
    * @return String
    */
    @ApiOperation("加密")
    @RequestMapping(value = "/encrypt", method = RequestMethod.GET)
    ResponseData<String> encrypt(String data);

    /**
     * 解密
     *
     * @param data 待解密数据
     * @return String
     */
    @ApiOperation("解密")
    @RequestMapping(value = "/decrypt", method = RequestMethod.GET)
    ResponseData<String> decrypt(String data);

}
