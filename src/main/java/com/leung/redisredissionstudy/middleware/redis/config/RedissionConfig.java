package com.leung.redisredissionstudy.middleware.redis.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissionConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        // 使用 JsonJacksonCodec，使 Redis 存储 JSON 字符串
        config.setCodec(new JsonJacksonCodec());


        config.useSentinelServers()
                .addSentinelAddress("redis://localhost:26379", "redis://localhost:26380","redis://localhost:26381") // 哨兵节点地址
                .setMasterName("mymaster") // 主节点名称
                .setPassword("redis123") // 密码（如有）
                .setMasterConnectionPoolSize(64)
                .setSlaveConnectionPoolSize(64)
                .setConnectTimeout(10000)
                .setRetryAttempts(3)
                .setRetryInterval(1500);

        return Redisson.create(config);
    }


}
