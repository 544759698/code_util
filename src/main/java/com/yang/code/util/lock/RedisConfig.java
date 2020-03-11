package com.yang.code.util.lock;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by yangguojun01 on 2020/3/11.
 */
@Component
public class RedisConfig {

    @Bean
    public Redisson redisson() {
        // 单机模式
        Config confg = new Config();
        confg.useSingleServer().setAddress("").setDatabase(0);
        return (Redisson) Redisson.create(confg);
    }

}
