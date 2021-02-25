package site.teamo.biu.infinity.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import site.teamo.biu.infinity.fweb.common.entity.HdfsSummary;
import site.teamo.biu.infinity.fweb.common.entity.QueueMetrics;
import site.teamo.biu.infinity.fweb.common.entity.YarnSummary;

import java.util.List;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/5
 */
@FeignClient
public interface HadoopService {
    HdfsSummary realTimeHdfsSummary();

    YarnSummary realTimeYarnSummary();

    List<QueueMetrics> realTimeQueueMetrics();

    List<HdfsSummary> findAllHdfsSummary();

    List<YarnSummary> findAllYarnSummary();

}
