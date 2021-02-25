package site.teamo.biu.infinity.web.service;

import site.teamo.biu.infinity.web.entity.HdfsSummary;
import site.teamo.biu.infinity.web.entity.QueueMetrics;
import site.teamo.biu.infinity.web.entity.YarnSummary;

import java.util.List;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/5
 */
public interface OverviewService {

    void refreshHdfsSummary();

    void refreshYarnSummary();

    void refreshQueueMetrics();

    HdfsSummary realTimeHdfsSummary();

    YarnSummary realTimeYarnSummary();

    List<QueueMetrics> realTimeQueueMetrics();

    List<HdfsSummary> findAllHdfsSummary();

    List<YarnSummary> findAllYarnSummary();

}
