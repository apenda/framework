package com.apenda.framework.core.controller;

import com.apenda.framework.common.data.CommonMessageCode;
import com.apenda.framework.common.data.ResponseData;
import com.apenda.framework.common.util.RsaUtil;
import com.apenda.framework.web.controller.IEncryptController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加解密控制器
 *
 * @author rui.zhou
 * @date 2021/08/11 18:00
 **/
@Slf4j
@RestController
public class EncryptController implements IEncryptController {

    @Override
    public ResponseData<String> encrypt(String data) {
        try {
            return new ResponseData<>(RsaUtil.encrypt(data));
        } catch (Exception e) {
            log.info("data encrypt error", e);
            return new ResponseData<>(CommonMessageCode.UNKNOWN_EXCEPTION);
        }
    }

    @Override
    public ResponseData<String> decrypt(String data) {
        try {
            return new ResponseData<>(RsaUtil.decrypt(data));
        } catch (Exception e) {
            log.info("data decrypt error", e);
            return new ResponseData<>(CommonMessageCode.UNKNOWN_EXCEPTION);
        }
    }
}
