package site.teamo.biu.infinity.web.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import site.teamo.biu.infinity.web.service.HadoopService;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/7
 */
@Configuration
@EnableScheduling
public class OverviewSchedule {

    private static final Logger LOGGER = LoggerFactory.getLogger(OverviewSchedule.class);

    @Autowired
    private HadoopService overviewService;

    /**
     * 刷新hdfs的概览信息到数据库
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void hdfsSummary() {
        overviewService.refreshHdfsSummary();
    }

    /**
     * 刷新yarn的概览信息到数据库
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void yarnSummary() {
        overviewService.refreshYarnSummary();
    }

    /**
     * 刷新yarn queue的信息到数据库
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void queueMetrics() {
        overviewService.refreshQueueMetrics();
    }

}
