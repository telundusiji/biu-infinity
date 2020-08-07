package site.teamo.biu.infinity.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.biu.infinity.common.util.BiuJSONResult;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/4
 */
@Api(value = "集群信息概览", tags = "集群信息概览")
@RestController
@RequestMapping("/cluster/information")
public class ClusterInformationController {

    @ApiOperation(value = "存储概览",notes = "存储概览")
    @GetMapping("/storage")
    public BiuJSONResult storage() {
        return BiuJSONResult.ok();
    }

    @ApiOperation(value = "计算概览",notes = "计算概览")
    @GetMapping("/calculation")
    public BiuJSONResult calculation() {
        return BiuJSONResult.ok();
    }

    @ApiOperation(value = "监控概览",notes = "监控概览")
    @GetMapping("/monitoring")
    public BiuJSONResult monitoring() {
        return BiuJSONResult.ok();
    }

}
