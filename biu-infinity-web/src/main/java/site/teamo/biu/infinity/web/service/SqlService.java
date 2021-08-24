package site.teamo.biu.infinity.web.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import site.teamo.biu.infinity.fapi.common.BiuJSONResult;
import site.teamo.biu.infinity.fweb.common.entity.sql.bo.SqlBO;
import site.teamo.biu.infinity.fweb.common.entity.sql.vo.SchemaVO;
import site.teamo.biu.infinity.fweb.common.entity.sql.vo.SqlVO;
import site.teamo.biu.infinity.fweb.common.util.SqlEngineType;

import java.util.List;

@FeignClient(name = "infinity-sql")
public interface SqlService {
    @RequestMapping(method = RequestMethod.GET, value = "/query/database")
    BiuJSONResult<List<String>> getDatabase(@RequestParam("engine") SqlEngineType engine);

    @RequestMapping(method = RequestMethod.GET, value = "/query/table")
    BiuJSONResult<List<String>> getTable(@RequestParam("engine") SqlEngineType engine,
                                         @RequestParam("database") String database);

    @RequestMapping(method = RequestMethod.GET, value = "/query/schema")
    BiuJSONResult<SchemaVO> getSchema(@RequestParam("engine") SqlEngineType engine,
                                      @RequestParam("database") String database,
                                      @RequestParam("table") String table);

    @RequestMapping(method = RequestMethod.POST, value = "/query/sql")
    BiuJSONResult<SqlVO> query(@RequestParam(name = "pageNo") Integer pageNo,
                               @RequestParam(name = "pageSize") Integer pageSize,
                               @RequestBody SqlBO sqlBo);


}
