package com.leung.redisredissionstudy;

import com.leung.redisredissionstudy.middleware.redis.util.RedissonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisRedissionStudyApplicationTests {

    @Autowired
    private RedissonUtil redissonUtil;


    @Test
    void contextLoads() {
        redissonUtil.set("test","测试数据");
    }

    @Test
    public void testRedisGet() {
        Object result = redissonUtil.get("test");
        System.out.println(result);
    }

}
