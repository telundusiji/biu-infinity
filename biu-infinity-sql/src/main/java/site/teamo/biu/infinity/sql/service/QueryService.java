package site.teamo.biu.infinity.sql.service;

import com.github.pagehelper.PageInfo;
import site.teamo.biu.infinity.common.util.Tuple2;
import site.teamo.biu.infinity.sql.entity.vo.SqlVO;
import site.teamo.biu.infinity.sql.util.SqlEngineType;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface QueryService {

    /**
     * 查询数据源下的数据库
     *
     * @return
     */
    List<String> database(SqlEngineType engine);

    /**
     * 查询数据库下的表
     *
     * @param engine
     * @param database
     * @return
     */
    List<String> table(SqlEngineType engine, String database);

    /**
     * 查询表的字段信息
     *
     * @param engine
     * @param database
     * @param table
     * @return
     */
    Map<String, String> schema(SqlEngineType engine, String database, String table);

    /**
     * sql查询
     *
     * @param sql
     * @param engine
     * @param pageSize
     * @param pageNo
     * @return
     */
    Tuple2<String, SqlVO> sql(String sql, SqlEngineType engine, int pageSize, int pageNo) throws SQLException;
}
