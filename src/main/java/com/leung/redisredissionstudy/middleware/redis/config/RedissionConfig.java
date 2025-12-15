package com.leung.redisredissionstudy.middleware.redis.config;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;
import org.redisson.config.ReadMode;
import org.redisson.config.SubscriptionMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedissionConfig {

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();

        // 使用 JsonJacksonCodec，使 Redis 存储 JSON 字符串
        config.setCodec(new JsonJacksonCodec());


        config.useReplicatedServers()
                // 添加所有节点地址
                .addNodeAddress("redis://127.0.0.1:6379")
                .addNodeAddress("redis://127.0.0.1:6380")
                .addNodeAddress("redis://127.0.0.1:6381")
                // 读取模式：优先从从节点读取
                .setReadMode(ReadMode.SLAVE)
                // 订阅模式：从主节点订阅
                .setSubscriptionMode(SubscriptionMode.MASTER)
                // 设置密码（如有）
                 .setPassword("redis123")
                // 连接池大小
                .setMasterConnectionPoolSize(64)
                .setSlaveConnectionPoolSize(64)
                // 连接超时时间
                .setConnectTimeout(10000)
                // 重试次数
                .setRetryAttempts(3)
                // 重试间隔
                .setRetryInterval(1500);

        return Redisson.create(config);
    }


}
