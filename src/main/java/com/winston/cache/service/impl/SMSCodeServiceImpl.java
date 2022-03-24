package com.winston.cache.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.winston.cache.domin.SMSCode;
import com.winston.cache.service.SMSCodeService;
import com.winston.cache.util.CodeUtils;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class SMSCodeServiceImpl implements SMSCodeService {

    @Autowired
    private CodeUtils codeUtils;

    //以下是默认缓存
//    @Override
//    //每次运行完往缓存里放东西 不会取，
//    @CachePut(value = "smsCode",key = "#tele")
//    public String sendCodeToSMS(String tele) {
//        String generator = codeUtils.generator(tele);
//        return generator;
//    }
//
//    @Override
//    public boolean checkCode(SMSCode smsCode) {
//        //取出内存中的验证码和传递过来进行匹配
//        String code = smsCode.getCode();
//        String cacheCode = codeUtils.get(smsCode.getTele());
//
//
//        return code.equals(cacheCode);
//    }
    //以下是使用memcached来完成的

    @Autowired
    private MemcachedClient memcachedClient;

//    @Override
//    public String sendCodeToSMS(String tele) {
//        String code = codeUtils.generator(tele);
//        try {
//            memcachedClient.set(tele,0,code);//0秒失效 可以改成十秒失效
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return code;
//    }
//
//    @Override
//    public boolean checkCode(SMSCode smsCode) {
//        String code = null;
//        try {
//            code = memcachedClient.get(smsCode.getTele().toString());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return smsCode.getCode().equals(code);
//    }
    //以下是jetcache缓存
    //expire设定过期时间
    //创建一个缓存空间  area就是来找使用哪一套配置 不写就是default
   //remote的方案 @CreateCache(area = "sms",name = "jetcache",expire = 3600,timeUnit = TimeUnit.SECONDS)

    @CreateCache(name = "jetCache",expire = 1000,timeUnit = TimeUnit.SECONDS,cacheType = CacheType.LOCAL)
    private Cache<String, String> jetcache;

    @Override
    public String sendCodeToSMS(String tele) {
        String generator = codeUtils.generator(tele);
        jetcache.put(tele,generator);
        return generator;
    }

    @Override
    public boolean checkCode(SMSCode smsCode) {
        //取出内存中的验证码和传递过来进行匹配
        String code = smsCode.getCode();
        String cacheCode = jetcache.get(smsCode.getTele());


        return code.equals(cacheCode);
    }
}
