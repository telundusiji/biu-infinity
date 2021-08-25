package site.teamo.biu.infinity.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.biu.infinity.fapi.common.BiuJSONResult;
import site.teamo.biu.infinity.fweb.common.entity.monitor.HdfsSummary;
import site.teamo.biu.infinity.fweb.common.entity.monitor.QueueMetrics;
import site.teamo.biu.infinity.fweb.common.entity.monitor.YarnSummary;
import site.teamo.biu.infinity.fweb.common.entity.vo.BaseChartVO;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResultUtil;
import site.teamo.biu.infinity.web.service.HadoopService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/4
 */
@Api(value = "集群信息概览", tags = "集群信息概览")
@RestController
@RequestMapping("/api/cluster/information")
@CrossOrigin
public class ClusterInformationController {

    @Autowired
    private HadoopService hadoopService;

    @ApiOperation(value = "存储概览", notes = "存储概览")
    @GetMapping("/storage")
    public BiuJSONResult storage() {
        return BiuJSONResultUtil.ok(hadoopService.realTimeHdfsSummary());
    }

    @ApiOperation(value = "存储图表", notes = "存储图表")
    @GetMapping("/storage/chart")
    public BiuJSONResult storageChart() {
        List<HdfsSummary> hdfsSummaryList = hadoopService.findAllHdfsSummary();
        List<String> columns = Arrays.stream(FieldUtils.getAllFields(HdfsSummary.class))
                .map(Field::getName).collect(Collectors.toList());
        BaseChartVO<HdfsSummary> hdfsSummaryBaseChartVO = BaseChartVO.<HdfsSummary>builder().columns(columns).rows(hdfsSummaryList).build();
        return BiuJSONResultUtil.ok(hdfsSummaryBaseChartVO);
    }

    @ApiOperation(value = "计算概览", notes = "计算概览")
    @GetMapping("/calculation")
    public BiuJSONResult calculation() {
        return BiuJSONResultUtil.ok(hadoopService.realTimeYarnSummary());
    }

    @ApiOperation(value = "计算图表", notes = "计算图表")
    @GetMapping("/calculation/chart")
    public BiuJSONResult calculationChart() {
        List<YarnSummary> yarnSummaryList = hadoopService.findAllYarnSummary();
        List<String> columns = Arrays.stream(FieldUtils.getAllFields(YarnSummary.class))
                .map(Field::getName).collect(Collectors.toList());
        BaseChartVO<YarnSummary> yarnSummaryBaseChartVO = BaseChartVO.<YarnSummary>builder().columns(columns).rows(yarnSummaryList).build();
        return BiuJSONResultUtil.ok(yarnSummaryBaseChartVO);
    }

    @ApiOperation(value = "监控概览", notes = "监控概览")
    @GetMapping("/queueMetrics")
    public BiuJSONResult monitoring() {
        List<QueueMetrics> queueMetrics = hadoopService.realTimeQueueMetrics();
        List<String> columns = Arrays.stream(FieldUtils.getAllFields(QueueMetrics.class))
                .map(Field::getName).collect(Collectors.toList());
        BaseChartVO<QueueMetrics> queueMetricsBaseChartVO = BaseChartVO.<QueueMetrics>builder().columns(columns).rows(queueMetrics).build();
        return BiuJSONResultUtil.ok(queueMetricsBaseChartVO);
    }

}
