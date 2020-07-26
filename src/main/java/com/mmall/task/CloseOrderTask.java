package com.mmall.task;

import com.mmall.common.Const;
import com.mmall.common.RedisShardedPool;
import com.mmall.util.RedisShardedPoolUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CloseOrderTask {
    @Scheduled(cron = "0 */1 * * * ?")
    public void task1() {
        log.info("关闭订单任务启动");
        long lockTimeout = 50000;
        Long setnxResult = RedisShardedPoolUtil.setNx(Const.RedisLock.CLOSE_ORDER_TASK_LOCK, String.valueOf(System.currentTimeMillis() + lockTimeout));
        if (setnxResult != null && setnxResult.intValue() == 1) {
            closeOrder(Const.RedisLock.CLOSE_ORDER_TASK_LOCK);
        } else {
            log.info("没有获得分布式锁：{}", Const.RedisLock.CLOSE_ORDER_TASK_LOCK);
        }
        log.info("关闭订单任务结束");

    }

    private void closeOrder(String lockName) {
        RedisShardedPoolUtil.expire(lockName, 50); // 防止死锁
        log.info("获取{}, ThreadName:{}", lockName, Thread.currentThread());
        log.info("订单关闭任务执行");
        RedisShardedPoolUtil.del(lockName);
        log.info("释放{}, ThreadName:{}", lockName, Thread.currentThread());
        log.info("====================================");

    }
}
