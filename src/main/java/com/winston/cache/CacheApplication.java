package com.winston.cache;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//开启缓存功能
@EnableCaching
@EnableCreateCacheAnnotation
//开启方法注解缓存
@EnableMethodCache(basePackages = "com.winston")
public class CacheApplication {

    public static void main(String[] args) {
        //增加信息 变成新的版本
        System.out.println("我是修改后的版本信息");
        SpringApplication.run(CacheApplication.class, args);
    }

}
