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

    HdfsSummary realTimeHdfsSummary(boolean isSave);

    YarnSummary realTimeYarnSummary(boolean isSave);

    List<QueueMetrics> realTimeQueueMetrics(boolean isSave);

    List<HdfsSummary> findAllHdfsSummary();

    List<YarnSummary> findAllYarnSummary();

}
