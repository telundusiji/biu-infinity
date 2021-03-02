package site.teamo.biu.infinity.sql.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.teamo.biu.infinity.common.util.MD5Util;
import site.teamo.biu.infinity.common.util.Tuple2;
import site.teamo.biu.infinity.common.util.Tuple3;
import site.teamo.biu.infinity.fweb.common.entity.SqlHistory;
import site.teamo.biu.infinity.sql.dao.SqlHistoryMapper;
import site.teamo.biu.infinity.sql.entity.vo.SqlVO;
import site.teamo.biu.infinity.sql.service.QueryService;
import site.teamo.biu.infinity.sql.util.QueryCacheUtil;
import site.teamo.biu.infinity.sql.util.ResultSetUtil;
import site.teamo.biu.infinity.sql.util.SqlEngineType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class QueryServiceImpl implements QueryService {

    @Autowired
    private SqlHistoryMapper sqlHistoryMapper;

    @Autowired
    private QueryCacheUtil cacheUtil;

    @Override
    public List<String> database(SqlEngineType engine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = engine.dataSource().getConnection();
            preparedStatement = connection.prepareStatement("show  databases");
            ResultSet resultSet = preparedStatement.executeQuery();
            Tuple2<Integer, List<List<String>>> result = ResultSetUtil.readData(resultSet);
            List<String> databases = result._2.stream()
                    .filter(row -> row != null && row.size() > 0)
                    .map(row -> row.get(0))
                    .collect(Collectors.toList());
            return databases;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            engine.dataSource().returnConnection(connection);
        }
    }

    @Override
    public List<String> table(SqlEngineType engine, String database) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = engine.dataSource().getConnection();
            preparedStatement = connection.prepareStatement("show tables from " + database);
            ResultSet resultSet = preparedStatement.executeQuery();
            Tuple2<Integer, List<List<String>>> result = ResultSetUtil.readData(resultSet);
            List<String> tables = result._2.stream()
                    .filter(row -> row != null && row.size() > 0)
                    .map(row -> row.get(0))
                    .collect(Collectors.toList());
            return tables;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            engine.dataSource().returnConnection(connection);
        }
    }

    @Override
    public Map<String, String> schema(SqlEngineType engine, String database, String table) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = engine.dataSource().getConnection();
            preparedStatement = connection.prepareStatement(String.format("desc %s.%s", database, table));
            ResultSet resultSet = preparedStatement.executeQuery();
            Tuple2<Integer, List<List<String>>> result = ResultSetUtil.readData(resultSet);
            Map<String, String> schema = result._2.stream()
                    .filter(row -> row != null && row.size() > 0)
                    .map(row -> Tuple2.of(row.get(0), row.get(1)))
                    .collect(Collectors.toMap(tuple2 -> tuple2._1, tuple2 -> tuple2._2));
            return schema;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            engine.dataSource().returnConnection(connection);
        }
    }

    @Override
    public Tuple2<String, SqlVO> sql(String sql, SqlEngineType engine, int pageNo, int pageSize) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        long startTime = System.currentTimeMillis();
        try {
            String sqlMd5 = MD5Util.toMD5(sql);
            /**
             * 先从缓存中查看
             */
            Tuple2<Tuple3<Integer, Integer, Long>, Tuple2<List<String>, List<List<String>>>> page = cacheUtil.getPage(sqlMd5, pageNo, pageSize);
            if (page != null) {
                return Tuple2.of(sqlMd5, SqlVO.builder()
                        .schema(page._2._1)
                        .pageNo(page._1._1)
                        .pageSize(page._1._2)
                        .total(page._1._3)
                        .list(page._2._2)
                        .build());
            }
            /**
             * 缓存中不存在，则连接数据源查询
             */
            connection = engine.dataSource().getConnection();
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<String> schema = ResultSetUtil.readSchema(resultSet);
            Tuple2<Integer, List<List<String>>> data = ResultSetUtil.readData(resultSet, pageSize);
            long count = count(sql, connection);
            sqlHistoryMapper.insert(SqlHistory.builder()
                    .sqlContent(sql)
                    .sqlMd5(sqlMd5)
                    .startTime(startTime)
                    .costTime(System.currentTimeMillis() - startTime)
                    .createTime(System.currentTimeMillis())
                    .queryEngine(engine.name())
                    .build());
            List<List<String>> cacheList = new ArrayList<>();
            cacheList.addAll(data._2);
            cacheList = ResultSetUtil.readData(resultSet, cacheList)._2;
            cacheUtil.cache(sqlMd5, cacheList, schema);
            return Tuple2.of(sqlMd5, SqlVO.builder()
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

    /**
     * 查询一个sql的查询结果条数
     *
     * @param sql
     * @param connection
     * @return
     */
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
