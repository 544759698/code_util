package com.yang.code.util.lock;

import java.util.concurrent.TimeUnit;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangguojun01 on 2020/3/11.
 */
@RestController
public class ClientController {

    @Autowired
    private Redisson redisson;

    @RequestMapping("/lock")
    public void lockDemo() {
        LockDTO lockDTO = new LockDTO("lock", "value");
        RLock lock = redisson.getLock(lockDTO.getName());
        // 设置redis锁，超时时间30秒，没拿到锁会阻塞
        lock.lock(30L, TimeUnit.SECONDS);
        try {
            System.out.println("get lock");
        } finally {
            lock.unlock();
        }
    }

}
