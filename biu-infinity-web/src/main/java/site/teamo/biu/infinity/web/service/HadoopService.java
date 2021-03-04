package site.teamo.biu.infinity.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import site.teamo.biu.infinity.fweb.common.entity.monitor.HdfsSummary;
import site.teamo.biu.infinity.fweb.common.entity.monitor.QueueMetrics;
import site.teamo.biu.infinity.fweb.common.entity.monitor.YarnSummary;

import java.util.List;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/5
 */
@FeignClient(name = "infinity-monitor")
public interface HadoopService {
    @RequestMapping(method = RequestMethod.GET, value = "/query/database")
    HdfsSummary realTimeHdfsSummary();
    @RequestMapping(method = RequestMethod.GET, value = "/query/database")
    YarnSummary realTimeYarnSummary();
    @RequestMapping(method = RequestMethod.GET, value = "/query/database")
    List<QueueMetrics> realTimeQueueMetrics();
    @RequestMapping(method = RequestMethod.GET, value = "/query/database")
    List<HdfsSummary> findAllHdfsSummary();
    @RequestMapping(method = RequestMethod.GET, value = "/query/database")
    List<YarnSummary> findAllYarnSummary();

}
