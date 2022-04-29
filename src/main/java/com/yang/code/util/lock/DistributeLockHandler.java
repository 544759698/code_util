package com.yang.code.util.lock;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/7/27.
 */
@Component
public class DistributeLockHandler {
    private static final Logger logger = LoggerFactory.getLogger(DistributeLockHandler.class);
    /**
     * 单个业务持有锁的时间10s，防止死锁
     **/
    private final static long LOCK_EXPIRE = 10 * 1000L;
    /**
     * 默认20ms尝试一次
     **/
    private final static long LOCK_TRY_INTERVAL = 20L;
    /**
     * 默认尝试20s
     **/
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;

    @Autowired
    private StringRedisTemplate template;

    /**
     * 尝试获取全局锁
     *
     * @param lock 锁的名称
     *
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockDTO lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock    锁的名称
     * @param timeout 获取超时时间 单位ms
     *
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockDTO lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock        锁的名称
     * @param timeout     获取锁的超时时间
     * @param tryInterval 多少毫秒尝试获取一次
     *
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockDTO lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    /**
     * 尝试获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取锁的超时时间
     * @param tryInterval    多少毫秒尝试获取一次
     * @param lockExpireTime 锁的过期
     *
     * @return true 获取成功，false获取失败
     */
    public boolean tryLock(LockDTO lock, long timeout, long tryInterval, long lockExpireTime) {
        return getLock(lock, timeout, tryInterval, lockExpireTime);
    }

    /**
     * 操作redis获取全局锁
     *
     * @param lock           锁的名称
     * @param timeout        获取的超时时间
     * @param tryInterval    多少ms尝试一次
     * @param lockExpireTime 获取成功后锁的过期时间
     *
     * @return true 获取成功，false获取失败
     */
    public boolean getLock(LockDTO lock, long timeout, long tryInterval, long lockExpireTime) {
        try {
            if (StringUtils.isEmpty(lock.getName()) || StringUtils.isEmpty(lock.getValue())) {
                return false;
            }
            long startTime = System.currentTimeMillis();
            boolean hasLock = false;
            while (!hasLock) {
                hasLock = template.opsForValue().setIfAbsent(lock.getName(), lock.getValue(),
                        lockExpireTime, TimeUnit.MILLISECONDS);
                if (hasLock) {
                    return true;
                } else {
                    //存在锁
                    logger.debug("lock is exist!！！");
                }
                //尝试超过了设定值之后直接跳出循环
                if (System.currentTimeMillis() - startTime > timeout) {
                    return false;
                }
                Thread.sleep(tryInterval);
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
            return false;
        }
        return false;
    }

    /**
     * 释放锁
     */
    public void releaseLock(LockDTO lock) {
        if (!StringUtils.isEmpty(lock.getName())) {
            template.delete(lock.getName());
        }
    }
}