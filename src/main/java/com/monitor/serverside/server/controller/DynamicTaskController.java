package com.monitor.serverside.server.controller;

import com.monitor.serverside.server.clientFeignService.IClientFeignService;
import com.monitor.serverside.server.runnableService.ServerReportRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ScheduledFuture;

/**
 * @author 七渣渣
 */
@RestController
@RequestMapping(value = "/api/task")
@Slf4j
public class DynamicTaskController {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;



    /**
     * 在ScheduledFuture中有一个cancel可以停止定时任务。
     */
    private ScheduledFuture<?> future;

    /**
     * ThreadPoolTaskScheduler：线程池任务调度类，能够开启线程池进行任务调度。
     * ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture，在这个方法需要添加两个参数，Runnable（线程接口类） 和CronTrigger（定时任务触发器）
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    /**
     * 启动任务
     **/
    @RequestMapping("/startTask")
    public String startCron() {
        future = threadPoolTaskScheduler.schedule(new ServerReportRunnable(), new CronTrigger("0/5 * * * * *"));
        log.info("DynamicTaskController.startCron()");
        return "startTask";
    }

    /**
     * 停止启此任务
     **/
    @RequestMapping("/stopTask")
    public String stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        log.info("DynamicTaskController.stopCron()");
        return "stopTask";
    }

    /**
     * 变更任务间隔，再次启动
     **/
    @RequestMapping("/changeCron")
    public String changeCron() {
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new ServerReportRunnable(), new CronTrigger("*/10 * * * * *"));
        log.info("DynamicTaskController.changeCron()");
        return "changeCron";
    }
}
