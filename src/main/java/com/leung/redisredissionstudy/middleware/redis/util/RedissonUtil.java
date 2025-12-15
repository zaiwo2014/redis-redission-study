package com.leung.redisredissionstudy.middleware.redis.util;

import lombok.RequiredArgsConstructor;
import org.redisson.api.*;
import org.redisson.api.listener.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Component
@RequiredArgsConstructor
public class RedissonUtil {


    private final RedissonClient redissonClient;


    // ==================== Key-Value 操作 ====================
    public void set(String key, Object value) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value);
    }

    public void set(String key, Object value, long time, TimeUnit timeUnit) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        bucket.set(value, time, timeUnit);
    }

    public Object get(String key) {
        RBucket<Object> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    public boolean delete(String key) {
        return redissonClient.getBucket(key).delete();
    }


    // ==================== List 操作 ====================
    public <V> void listAdd(String listName, V value) {
        RList<V> list = redissonClient.getList(listName);
        list.add(value);
    }

    public <V> V listGet(String listName, int index) {
        RList<V> list = redissonClient.getList(listName);
        return list.get(index);
    }

    public <V> boolean listRemove(String listName, V value) {
        RList<V> list = redissonClient.getList(listName);
        return list.remove(value);
    }

    public int listSize(String listName) {
        RList<?> list = redissonClient.getList(listName);
        return list.size();
    }


    // ==================== Map 操作 ====================
    public <K, V> void mapPut(String mapName, K key, V value) {
        RMap<K, V> map = redissonClient.getMap(mapName);
        map.put(key, value);
    }

    public <K, V> V mapGet(String mapName, K key) {
        RMap<K, V> map = redissonClient.getMap(mapName);
        return map.get(key);
    }

    public <K, V> boolean mapRemove(String mapName, K key) {
        RMap<K, V> map = redissonClient.getMap(mapName);
        return map.remove(key) != null;
    }

    // ==================== Set 操作 ====================
    public <V> void addToSet(String setName, V value) {
        RSet<V> set = redissonClient.getSet(setName);
        set.add(value);
    }

    public <V> boolean isMember(String setName, V value) {
        RSet<V> set = redissonClient.getSet(setName);
        return set.contains(value);
    }

    public <V> void removeFromSet(String setName, V value) {
        RSet<V> set = redissonClient.getSet(setName);
        set.remove(value);
    }

    // ==================== 分布式锁操作 ====================
    public RLock getLock(String lockName) {
        return redissonClient.getLock(lockName);
    }

    public boolean tryLock(String lockName, long waitTime, long leaseTime, TimeUnit unit) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockName);
        return lock.tryLock(waitTime, leaseTime, unit);
    }

    public void unlock(String lockName) {
        RLock lock = redissonClient.getLock(lockName);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    // ==================== 队列操作 ====================
    public <V> void addToQueue(String queueName, V value) {
        RQueue<V> queue = redissonClient.getQueue(queueName);
        queue.add(value);
    }

    public <V> V pollFromQueue(String queueName) {
        RQueue<V> queue = redissonClient.getQueue(queueName);
        return queue.poll();
    }


}
