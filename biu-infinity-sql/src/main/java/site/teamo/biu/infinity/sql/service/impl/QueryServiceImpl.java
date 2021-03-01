package site.teamo.biu.infinity.sql.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.teamo.biu.infinity.common.util.MD5Util;
import site.teamo.biu.infinity.common.util.Tuple2;
import site.teamo.biu.infinity.fweb.common.entity.SqlHistory;
import site.teamo.biu.infinity.sql.dao.SqlHistoryMapper;
import site.teamo.biu.infinity.sql.entity.vo.SqlVO;
import site.teamo.biu.infinity.sql.service.QueryService;
import site.teamo.biu.infinity.sql.util.ResultSetUtil;
import site.teamo.biu.infinity.sql.util.SqlEngineType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QueryServiceImpl implements QueryService {

    @Autowired
    private SqlHistoryMapper sqlHistoryMapper;

    @Override
    public List<String> database(SqlEngineType engine) {
        return null;
    }

    @Override
    public List<String> table(SqlEngineType engine, String database) {
        return null;
    }

    @Override
    public Map<String, String> schema(SqlEngineType engine, String database, String table) {
        return null;
    }

    @Override
    public Tuple2<String, SqlVO> sql(String sql, SqlEngineType engine, int pageSize, int pageNo) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        long startTime = System.currentTimeMillis();
        try {
            connection = engine.dataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> schema = ResultSetUtil.readSchema(resultSet);
            Tuple2<Integer, List<List<String>>> data = ResultSetUtil.readData(resultSet, pageSize);
            long count = count(sql, connection);
            sqlHistoryMapper.insert(SqlHistory.builder()
                    .sqlContent(sql)
                    .startTime(startTime)
                    .costTime(System.currentTimeMillis() - startTime)
                    .createTime(System.currentTimeMillis())
                    .queryEngine(engine.name())
                    .build());
            return Tuple2.of(MD5Util.toMD5(sql), SqlVO.builder()
                    .schema(schema)
                    .pageNo(pageNo)
                    .pageSize(data._1)
                    .total(count)
                    .list(data._2)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            engine.dataSource().returnConnection(connection);
        }
//        SqlRowSet sqlRowSet = engine.jdbcTemplate().queryForRowSet(sql);

    }

    public long count(String sql, Connection connection) {
        try (PreparedStatement statement = connection.prepareStatement(String.format("select count(1) as number from ( %s ) counter", sql))) {
            ResultSet countResultSet = statement.executeQuery();
            countResultSet.next();
            return countResultSet.getLong("number");
        } catch (Exception e) {
            throw new RuntimeException("count sql[" + sql + "] failed", e);
        }
    }

}
