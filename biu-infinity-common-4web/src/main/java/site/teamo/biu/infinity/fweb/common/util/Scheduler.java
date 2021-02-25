package site.teamo.biu.infinity.fweb.common.util;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import site.teamo.biu.infinity.common.constant.InfinityConstant;
import site.teamo.biu.infinity.common.exception.ErrorCode;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;


/**
 * 定时任务调度器
 */
@Slf4j
public class Scheduler {

    private ThreadPoolTaskScheduler scheduler;

    public Scheduler(ThreadPoolTaskScheduler scheduler) {
        this.scheduler = scheduler;
    }


    private Map<String, CronJob> cronTaskMap = new ConcurrentHashMap<>();

    public CronJob createTask(Runnable task, String cron) {
        return createTask("CronTask-" + System.currentTimeMillis(), task, cron);
    }

    public CronJob createTask(String name, Runnable task, String cron) {
        /**
         * 校验参数，错误入参时则抛出异常
         */
        if (StringUtils.isEmpty(name)
                || task == null
                || StringUtils.isEmpty(cron)
                || !cron.matches(InfinityConstant.Expression.CRON_REGULAR_EXPRESSION)) {
            if (log.isDebugEnabled()) {
                log.error("Bad parameter[name:{},task:{},cron:{}] when create cron task in Scheduler", name, task, cron);
            }
            throw ErrorCode.PARAMETER.BAD_PARAMETER.createRuntimeException("Bad parameter when create cron task in Scheduler");
        }
        /**
         * 创建新任务
         */
        log.info("Create cron job[name:{},task:{},cron:{}]", name, task, cron);
        ScheduledFuture<?> future = scheduler.schedule(task, new CronTrigger(cron));
        CronJob cronJob = CronJob.builder()
                .id(UUID.randomUUID().toString())
                .name(name)
                .task(task)
                .cron(cron)
                .future(future)
                .scheduler(this)
                .build();
        cronTaskMap.put(cronJob.getId(), cronJob);
        return cronJob;
    }

    public CronJob update(String id, String name, Runnable task, String cron) {
        CronJob cronJob = cronTaskMap.get(id);
        if (cronJob == null) {
            throw ErrorCode.RESOURCE.RESOURCE_NOT_EXISTS.createRuntimeException("Cron task for id " + id + " is not exist");
        }
        /**
         * 错误以及空的内容不进行更新
         */
        if (StringUtils.isNotEmpty(name)) {
            cronJob.setName(name);
        }
        //标记是否需要重新调度任务
        boolean reschedule = false;
        if (StringUtils.isNotEmpty(cron) && cron.matches(InfinityConstant.Expression.CRON_REGULAR_EXPRESSION)) {
            cronJob.setCron(cron);
            reschedule = true;
        }
        if (task != null) {
            cronJob.setTask(task);
            reschedule = true;
        }
        if (reschedule) {
            cronJob.stop();
            ScheduledFuture<?> future = scheduler.schedule(cronJob.getTask(), new CronTrigger(cronJob.getCron()));
            cronJob.setFuture(future);
        }
        return cronJob;
    }

    public void destroy(String id) {
        CronJob cronJob = cronTaskMap.remove(id);
        if (cronJob != null) {
            cronJob.stop();
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    @Setter(AccessLevel.PRIVATE)
    public static class CronJob {
        private String id;
        private String name;
        private Runnable task;
        private String cron;
        private ScheduledFuture future;
        private Scheduler scheduler;

        private void start() {
            if (future.isDone() || future.isCancelled()) {
                scheduler.update(id, name, task, cron);
            }
        }

        private synchronized void stop() {
            if (future.isDone() || future.isCancelled()) {
                return;
            }
            future.cancel(true);
        }

        private synchronized void destroy() {
            if (scheduler != null) {
                scheduler.destroy(id);
            }
            this.scheduler = null;
        }

        private synchronized void changeName(String newName) {
            scheduler.update(id, newName, task, cron);
        }

        private synchronized void changeCron(String newCron) {
            scheduler.update(id, name, task, newCron);
        }

        private synchronized void changeTask(Runnable newTask) {
            scheduler.update(id, name, newTask, cron);
        }
    }

}
