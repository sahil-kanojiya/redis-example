package org.example;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockApplication {

    private static final Logger log = LoggerFactory.getLogger(LockApplication.class);

    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        RedissonClient redisson = Redisson.create(config);

        Runnable runnable = () -> {
            RLock lock = redisson.getLock("order-lock");
            lock.lock();
            try {
                log.info("lock acquired by thread: {}", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        };

        new Thread(runnable, "Thread 1").start();
        new Thread(runnable, "Thread 2").start();
        new Thread(runnable, "Thread 3").start();
        new Thread(runnable, "Thread 4").start();
        new Thread(runnable, "Thread 5").start();
    }
}
