package site.teamo.biu.infinity.monitor.controller.hadoop;

import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.biu.infinity.fapi.common.BiuJSONResult;
import site.teamo.biu.infinity.fweb.common.entity.monitor.QueueMetrics;
import site.teamo.biu.infinity.fweb.common.entity.monitor.YarnSummary;
import site.teamo.biu.infinity.fweb.common.entity.vo.BaseChartVO;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResultUtil;
import site.teamo.biu.infinity.monitor.service.HadoopInformationService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class YarnInformationController {

    @Autowired
    private HadoopInformationService hadoopInformationService;

    @ApiOperation(value = "计算概览", notes = "计算概览")
    @GetMapping("/calculation")
    public BiuJSONResult calculation() {
        return BiuJSONResultUtil.ok(hadoopInformationService.realTimeYarnSummary(false));
    }

    @ApiOperation(value = "计算图表", notes = "计算图表")
    @GetMapping("/calculation/chart")
    public BiuJSONResult calculationChart() {
        List<YarnSummary> yarnSummaryList = hadoopInformationService.findAllYarnSummary();
        List<String> columns = Arrays.stream(FieldUtils.getAllFields(YarnSummary.class))
                .map(Field::getName).collect(Collectors.toList());
        BaseChartVO<YarnSummary> yarnSummaryBaseChartVO = BaseChartVO.<YarnSummary>builder().columns(columns).rows(yarnSummaryList).build();
        return BiuJSONResultUtil.ok(yarnSummaryBaseChartVO);
    }

    @ApiOperation(value = "监控概览", notes = "监控概览")
    @GetMapping("/queueMetrics")
    public BiuJSONResult monitoring() {
        List<QueueMetrics> queueMetrics = hadoopInformationService.realTimeQueueMetrics(false);
        List<String> columns = Arrays.stream(FieldUtils.getAllFields(QueueMetrics.class))
                .map(Field::getName).collect(Collectors.toList());
        BaseChartVO<QueueMetrics> queueMetricsBaseChartVO = BaseChartVO.<QueueMetrics>builder().columns(columns).rows(queueMetrics).build();
        return BiuJSONResultUtil.ok(queueMetricsBaseChartVO);
    }

}
