package site.teamo.biu.infinity.monitor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import site.teamo.biu.infinity.fweb.common.util.Scheduler;
import site.teamo.biu.infinity.monitor.service.HadoopInformationService;

@Configuration
public class SchedulerInitialization {

    @Autowired
    private HadoopInformationService hadoopInformationService;

    @Bean
    public Scheduler scheduler(@Autowired ThreadPoolTaskScheduler threadPoolTaskScheduler) {
        Scheduler scheduler = new Scheduler(threadPoolTaskScheduler);
        scheduler.createTask("hdfs-summary", () -> hadoopInformationService.realTimeHdfsSummary(true), "0 0/5 * * * ?");
        scheduler.createTask("yarn-summary", () -> hadoopInformationService.realTimeYarnSummary(true), "0 0/5 * * * ?");
        scheduler.createTask("yarn-queue-metrics", () -> hadoopInformationService.realTimeQueueMetrics(true), "0 0/5 * * * ?");
        return scheduler;
    }
}
