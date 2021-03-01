package site.teamo.biu.infinity.sql.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import site.teamo.biu.infinity.common.util.Tuple2;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResult;
import site.teamo.biu.infinity.sql.entity.bo.SqlBO;
import site.teamo.biu.infinity.sql.entity.vo.SqlVO;
import site.teamo.biu.infinity.sql.service.QueryService;

import java.sql.SQLException;

@Api("query")
@RestController
@RequestMapping("/query")
public class QueryController {

    @Autowired
    private QueryService queryService;

    @GetMapping("/database")
    public BiuJSONResult database(@RequestParam String engine) {
        return BiuJSONResult.ok();
    }

    @GetMapping("/table")
    public BiuJSONResult table(@RequestParam String engine, @RequestParam String database) {
        return BiuJSONResult.ok();
    }

    @GetMapping("/schema")
    public BiuJSONResult schema(@RequestParam String engine, @RequestParam String database, @RequestParam String table) {
        return BiuJSONResult.ok();
    }

    @ApiOperation("sql query")
    @PostMapping(value = "/sql"
            , consumes = MediaType.APPLICATION_JSON_VALUE
            , produces = MediaType.APPLICATION_JSON_VALUE)
    public BiuJSONResult sql(
            @RequestBody SqlBO sqlBo) throws SQLException {
        Tuple2<String, SqlVO> sql = queryService.sql(sqlBo.getSql(), sqlBo.getType(), sqlBo.getPageSize(), sqlBo.getPageNo());
        return BiuJSONResult.ok(sql._2);
    }
}
