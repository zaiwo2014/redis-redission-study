package com.leung.redisredissionstudy.controller;


import com.leung.redisredissionstudy.middleware.redis.util.RedissonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RedisController {

    @Autowired
    private RedissonUtil redissonUtil;

    @RequestMapping("/test")
    public String testRedis(@RequestParam String key) {
        Object o = redissonUtil.get(key);
        return o.toString();
    }

    @RequestMapping("/testWrite")
    public void writeRedis(@RequestParam String key, @RequestParam String value) {
        redissonUtil.set(key,value);
    }
}
