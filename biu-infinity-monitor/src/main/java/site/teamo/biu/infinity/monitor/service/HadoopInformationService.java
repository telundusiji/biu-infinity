package site.teamo.biu.infinity.monitor.service;



import site.teamo.biu.infinity.fweb.common.entity.HdfsSummary;
import site.teamo.biu.infinity.fweb.common.entity.QueueMetrics;
import site.teamo.biu.infinity.fweb.common.entity.YarnSummary;

import java.util.List;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/5
 */
public interface HadoopInformationService {

    void refreshHdfsSummary();

    void refreshYarnSummary();

    void refreshQueueMetrics();

    HdfsSummary realTimeHdfsSummary();

    YarnSummary realTimeYarnSummary();

    List<QueueMetrics> realTimeQueueMetrics();

    List<HdfsSummary> findAllHdfsSummary();

    List<YarnSummary> findAllYarnSummary();

}
