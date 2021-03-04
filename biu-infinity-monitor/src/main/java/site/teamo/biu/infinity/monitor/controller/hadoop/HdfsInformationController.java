package site.teamo.biu.infinity.monitor.controller.hadoop;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.biu.infinity.fweb.common.entity.monitor.HdfsSummary;
import site.teamo.biu.infinity.fweb.common.entity.vo.BaseChartVO;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResult;
import site.teamo.biu.infinity.monitor.service.HadoopInformationService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping("/api/hdfs")
public class HdfsInformationController {

    @Autowired
    private HadoopInformationService hadoopInformationService;

    @ApiOperation(value = "存储概览", notes = "存储概览")
    @GetMapping("/storage")
    public BiuJSONResult storage() {
        return BiuJSONResult.ok(hadoopInformationService.realTimeHdfsSummary(false));
    }

    @ApiOperation(value = "存储图表", notes = "存储图表")
    @GetMapping("/storage/chart")
    public BiuJSONResult storageChart() {
        List<HdfsSummary> hdfsSummaryList = hadoopInformationService.findAllHdfsSummary();
        List<String> columns = Arrays.stream(FieldUtils.getAllFields(HdfsSummary.class))
                .map(Field::getName).collect(Collectors.toList());
        BaseChartVO<HdfsSummary> hdfsSummaryBaseChartVO = BaseChartVO.<HdfsSummary>builder().columns(columns).rows(hdfsSummaryList).build();
        return BiuJSONResult.ok(hdfsSummaryBaseChartVO);
    }
}
