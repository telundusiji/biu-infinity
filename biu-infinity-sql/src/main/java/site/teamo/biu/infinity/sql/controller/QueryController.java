package site.teamo.biu.infinity.sql.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import site.teamo.biu.infinity.common.util.Tuple2;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResult;
import site.teamo.biu.infinity.sql.entity.bo.SqlBO;
import site.teamo.biu.infinity.sql.entity.vo.SchemaVO;
import site.teamo.biu.infinity.sql.entity.vo.SqlVO;
import site.teamo.biu.infinity.sql.service.QueryService;
import site.teamo.biu.infinity.sql.util.SqlEngineType;

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

    @GetMapping("/database")
    public BiuJSONResult database(@RequestParam String engine) throws SQLException {
        return BiuJSONResult.ok(queryService.database(SqlEngineType.valueOf(engine)));
    }

    @GetMapping("/table")
    public BiuJSONResult table(@RequestParam String engine, @RequestParam String database) throws SQLException {
        return BiuJSONResult.ok(queryService.table(SqlEngineType.valueOf(engine), database));
    }

    @GetMapping("/schema")
    public BiuJSONResult schema(@RequestParam String engine, @RequestParam String database, @RequestParam String table) throws SQLException {
        Map<String, String> schema = queryService.schema(SqlEngineType.valueOf(engine), database, table);
        return BiuJSONResult.ok(
                SchemaVO.builder()
                        .total(schema.entrySet().size())
                        .fields(
                                schema.entrySet()
                                        .stream()
                                        .map(entry -> SchemaVO.Field.builder().build())
                                        .collect(Collectors.toList())
                        )
                        .build()
        );
    }

    @ApiOperation("sql query")
    @PostMapping(value = "/sql")
    public BiuJSONResult sql(
            @RequestParam(defaultValue = "1", required = false)
            @Min(value = 1, message = "PageNo must be greater than or equal to 1")
            @ApiParam(value = "页码", example = "1")
                    Integer pageNo,
            @RequestParam(defaultValue = "10", required = false)
            @Range(min = 5, max = 1000, message = "PageSize must be greater than or equal to 5 and less than or equal to 1000")
            @ApiParam(value = "页面大小", example = "10")
                    Integer pageSize,
            @RequestBody
                    SqlBO sqlBo) throws

            SQLException {
        Tuple2<String, SqlVO> sql = queryService.sql(sqlBo.getSql(), sqlBo.getType(), pageNo, pageSize);
        return BiuJSONResult.ok(sql._2);
    }
}
