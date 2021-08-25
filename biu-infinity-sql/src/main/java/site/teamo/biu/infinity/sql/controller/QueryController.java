package site.teamo.biu.infinity.sql.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.teamo.biu.infinity.common.util.Tuple2;
import site.teamo.biu.infinity.fapi.common.BiuJSONResult;
import site.teamo.biu.infinity.fweb.common.annoation.Validation;
import site.teamo.biu.infinity.fweb.common.entity.sql.bo.SqlBO;
import site.teamo.biu.infinity.fweb.common.entity.sql.vo.SchemaVO;
import site.teamo.biu.infinity.fweb.common.entity.sql.vo.SqlVO;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResultUtil;
import site.teamo.biu.infinity.sql.service.QueryService;
import site.teamo.biu.infinity.fweb.common.util.SqlEngineType;

import javax.validation.constraints.Min;
import java.sql.SQLException;
import java.util.Map;
import java.util.stream.Collectors;

@Api("query")
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @ApiOperation("查询数据库信息")
    @GetMapping("/database")
    @Validation
    public BiuJSONResult database(
            @ApiParam("源类型")
            @RequestParam String engine) throws SQLException {
        return BiuJSONResultUtil.ok(queryService.database(SqlEngineType.valueOf(engine)));
    }

    @ApiOperation("查询表信息")
    @GetMapping("/table")
    @Validation
    public BiuJSONResult table(
            @ApiParam("源类型")
            @RequestParam String engine,
            @ApiParam("数据库名称")
            @RequestParam String database) throws SQLException {
        return BiuJSONResultUtil.ok(queryService.table(SqlEngineType.valueOf(engine), database));
    }

    @ApiOperation("查询表结构")
    @GetMapping("/schema")
    @Validation
    public BiuJSONResult schema(
            @ApiParam("源类型")
            @RequestParam String engine,
            @ApiParam("数据库名称")
            @RequestParam String database,
            @ApiParam("表名称")
            @RequestParam String table) throws SQLException {
        Map<String, String> schema = queryService.schema(SqlEngineType.valueOf(engine), database, table);
        return BiuJSONResultUtil.ok(
                SchemaVO.builder()
                        .total(schema.entrySet().size())
                        .fields(
                                schema.entrySet()
                                        .stream()
                                        .map(entry -> SchemaVO.Field.builder()
                                                .name(entry.getKey())
                                                .type(entry.getValue())
                                                .build())
                                        .collect(Collectors.toList())
                        )
                        .build()
        );
    }

    @ApiOperation("Sql执行")
    @PostMapping(value = "/sql")
    @Validation
    public BiuJSONResult sql(
            @ApiParam(value = "页码", example = "1")
            @Min(value = 1, message = "PageNo must be greater than or equal to 1")
            @RequestParam(defaultValue = "1", required = false) Integer pageNo,
            @ApiParam(value = "页面大小", example = "10")
            @Range(min = 5, max = 1000, message = "PageSize must be greater than or equal to 5 and less than or equal to 1000")
            @RequestParam(defaultValue = "10", required = false) Integer pageSize,
            @ApiParam("Sql执行BO")
            @RequestBody SqlBO sqlBo) throws SQLException {
        Tuple2<String, SqlVO> sql = queryService.sql(sqlBo.getSql(), sqlBo.getType(), pageNo, pageSize);
        return BiuJSONResultUtil.ok(sql._2);
    }
}
