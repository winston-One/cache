package com.winston.cache.service.impl;

import com.winston.cache.service.MsgService;
import com.winston.cache.util.CodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MsgServiceImpl implements MsgService {
    //private HashMap<String, String> cache = new HashMap<String, String>();
    @Autowired
    private CodeUtils codeUtils;

    @Override
    //@Cacheable(value = "smsCode",key = "#tele")
    @CachePut(value = "smsCode",key = "#tele")
    public String sendCodeToSMS(String tele) {
        String generator = codeUtils.generator(tele);
        return generator;
    }

    @Override

    public boolean check(String tele, String code) {


        return false;
    }

}
