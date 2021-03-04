package site.teamo.biu.infinity.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import site.teamo.biu.infinity.fweb.common.entity.sql.bo.SqlBO;
import site.teamo.biu.infinity.fweb.common.entity.sql.vo.SqlVO;
import site.teamo.biu.infinity.fweb.common.entity.web.bo.SqlQueryBO;
import site.teamo.biu.infinity.fweb.common.entity.web.vo.SqlQueryVO;
import site.teamo.biu.infinity.fweb.common.entity.web.vo.TreeNodeVO;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResult;
import site.teamo.biu.infinity.fweb.common.util.SqlEngineType;
import site.teamo.biu.infinity.web.service.SqlService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sql")
public class SqlController {

    @Autowired
    private SqlService sqlService;

    @GetMapping(value = "/datasource/info",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BiuJSONResult info() {
        List<TreeNodeVO> info = new ArrayList<>();
        info.add(TreeNodeVO.builder()
                .title(SqlEngineType.HIVE.name())
                .type("datasource")
                .children(buildDatabaseInfo(SqlEngineType.HIVE))
                .build());

        return BiuJSONResult.ok(info);
    }

    @PostMapping(value = "/query",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BiuJSONResult execute(@RequestBody SqlQueryBO sqlQueryBO) {
        SqlVO sqlVO = sqlService.query(sqlQueryBO.getPageNo(),
                sqlQueryBO.getPageSize(),
                SqlBO.builder()
                        .type(sqlQueryBO.getType())
                        .sql(sqlQueryBO.getSql())
                        .build())
                .getData();
        List<String> schema = sqlVO.getSchema();
        List<Map<String, String>> list = sqlVO.getList().stream()
                .map(row -> {
                    Map<String, String> rowMap = new HashMap<>();
                    for (int i = 0; i < row.size(); i++) {
                        rowMap.put(schema.get(i), row.get(i));
                    }
                    return rowMap;
                })
                .collect(Collectors.toList());

        return BiuJSONResult.ok(SqlQueryVO.builder()
                .count(sqlVO.getTotal())
                .pageNo(sqlVO.getPageNo())
                .pageSize(sqlVO.getPageSize())
                .schema(schema)
                .list(list)
                .build());
    }


    private List<TreeNodeVO> buildDatabaseInfo(SqlEngineType engine) {

        List<TreeNodeVO> databaseList = sqlService.getDatabase(engine).getData().stream()
                .map(database -> TreeNodeVO.builder().title(database).type("database").build())
                .collect(Collectors.toList());
        databaseList.forEach(database -> database.setChildren(buildTableInfo(engine, database.getTitle())));
        return databaseList;
    }

    private List<TreeNodeVO> buildTableInfo(SqlEngineType engine, String database) {
        List<TreeNodeVO> tableList = sqlService.getTable(engine, database).getData().stream()
                .map(table -> TreeNodeVO.builder().title(table).type("table").build())
                .collect(Collectors.toList());
        tableList.forEach(table -> table.setChildren(buildSchemaInfo(engine, database, table.getTitle())));
        return tableList;
    }

    public List<TreeNodeVO> buildSchemaInfo(SqlEngineType engine, String database, String table) {
        return sqlService.getSchema(engine, database, table).getData().getFields().stream()
                .map(field -> TreeNodeVO.builder()
                        .title(field.getName() + " - " + field.getType())
                        .type("field").build())
                .collect(Collectors.toList());
    }
}
