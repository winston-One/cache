package com.winston.cache.config;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class XMemcacheConfig {

    @Autowired
    private XMemcachedProperties xMemcachedProperties;

    @Bean
    public MemcachedClient getMemcachedClient() throws Exception{
        MemcachedClientBuilder memcachedClientBuilder = new XMemcachedClientBuilder(xMemcachedProperties.getServers());
        memcachedClientBuilder.setConnectionPoolSize(xMemcachedProperties.getPoolSize());
        memcachedClientBuilder.setOpTimeout(xMemcachedProperties.getOpTimeout());

        MemcachedClient memcachedClient = memcachedClientBuilder.build();
        return memcachedClient;
    }
}
