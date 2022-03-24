package com.winston.cache.util;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CodeUtils {
    private String[] patch = {"000000","00000","0000","000","00","0",""};


    public String generator(String tele) {
//验证码加密过程
        int  hash = tele.hashCode();
        int encryption = 202010;
        long result = hash^encryption;
        long nowTime = System.currentTimeMillis();
        result = result ^ nowTime;
        long code = result % 1000000;
        //避免验证码为负数
        code = code < 0 ? -code : code;
        //位数不够就补0
        String codeStr = code + "";
        int length = codeStr.length();
        return patch[length] + codeStr;
    }

    @Cacheable(value = "smsCode",key = "#tele")
    public String get(String tele) {
        return null;
    }
}
