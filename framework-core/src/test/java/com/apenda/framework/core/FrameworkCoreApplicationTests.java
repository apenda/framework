package com.apenda.framework.core;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Configuration
@SpringBootTest(classes = { FrameworkCoreApplication.class })
class FrameworkCoreApplicationTests {

    @Autowired
    private StringEncryptor encryptor;

    @Test
    void contextLoads() {
        System.err.println("root --> " + encryptor.encrypt("root"));
        System.err.println("123456 --> " + encryptor.encrypt("123456"));
    }

}
