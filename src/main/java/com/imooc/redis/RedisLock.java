package com.imooc.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

/**
 * redis锁（用于集群分布式）
 *
 * @author yangxin
 * 2019/12/31 10:51
 */
@Component
@Slf4j
public class RedisLock {
    private final StringRedisTemplate redisTemplate;

    @Autowired
    public RedisLock(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 加锁
     *
     * @param key key值
     * @param value 当前时间+超时时间
     * @return 是否加锁成功
     */
    public boolean lock(String key, String value) {
        // 如果没有这个键，则直接上锁成功
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }

        // 存取该键的旧值
        String currentValue = redisTemplate.opsForValue().get(key);

        // 如果锁过期
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            // 获取上一个锁的时间
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);

            // 说明在当前线程抢锁的过程，有另外一个线程拿到锁
            return !StringUtils.isEmpty(oldValue) && Objects.equals(currentValue, oldValue);
        }

        return false;
    }

    /**
     * 解锁
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && Objects.equals(currentValue, value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常", e);
        }
    }
}
