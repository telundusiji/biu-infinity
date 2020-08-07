package site.teamo.biu.infinity.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.biu.infinity.common.util.BiuJSONResult;
import site.teamo.biu.infinity.service.OverviewService;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/4
 */
@Api(value = "集群信息概览", tags = "集群信息概览")
@RestController
@RequestMapping("/api/cluster/information")
public class ClusterInformationController {

    @Autowired
    private OverviewService overviewService;

    @ApiOperation(value = "存储概览",notes = "存储概览")
    @GetMapping("/storage")
    public BiuJSONResult storage() {
        return BiuJSONResult.ok(overviewService.realTimeHdfsSummary());
    }

    @ApiOperation(value = "计算概览",notes = "计算概览")
    @GetMapping("/calculation")
    public BiuJSONResult calculation() {
        return BiuJSONResult.ok(overviewService.realTimeYarnSummary());
    }

    @ApiOperation(value = "监控概览",notes = "监控概览")
    @GetMapping("/monitoring")
    public BiuJSONResult monitoring() {
        return BiuJSONResult.ok(overviewService.realTimeQueueMetrics());
    }

}
