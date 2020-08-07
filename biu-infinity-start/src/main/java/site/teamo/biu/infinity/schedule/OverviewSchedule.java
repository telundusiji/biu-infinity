package site.teamo.biu.infinity.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import site.teamo.biu.infinity.service.OverviewService;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/7
 */
@Configuration
@EnableScheduling
public class OverviewSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverviewSchedule.class);

    @Autowired
    private OverviewService overviewService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void hdfsSummary() {
        LOGGER.info("刷新hdfs信息");
        overviewService.refreshHdfsSummary();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void yarnSummary() {
        LOGGER.info("刷新yarn信息");
        overviewService.refreshYarnSummary();
    }

    @Scheduled(cron = "0/5 * * * * ?")
    public void queueMetrics() {
        LOGGER.info("刷新queue信息");
        overviewService.refreshQueueMetrics();
    }

}
