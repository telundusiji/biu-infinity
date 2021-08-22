package site.teamo.biu.infinity.connection.mysql8025;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import site.teamo.biu.infinity.connection.jdbc.JdbcResource;
import site.teamo.biu.infinity.connection.jdbc.JdbcUtils;
import site.teamo.biu.infinity.connection.sdk.bean.mysql.CreateRequest;
import site.teamo.biu.infinity.connection.sdk.bean.mysql.ExampleData;
import site.teamo.biu.infinity.connection.sdk.bean.mysql.MysqlTableInfo;
import site.teamo.biu.infinity.connection.sdk.bean.mysql.UpdateRequest;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;
import site.teamo.biu.infinity.connection.sdk.exception.MetadataOperationException;

import java.util.List;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 21:21
 * @Description:
 */
public class MysqlMetadataOperator implements MetadataOperator<String, CreateRequest, UpdateRequest, Long, ExampleData, MysqlTableInfo> {

    private DruidDataSource dataSource;
    private JdbcResource resource;

    public MysqlMetadataOperator(DruidDataSource dataSource, JdbcResource resource) {
        this.dataSource = dataSource;
        this.resource = resource;
    }

    @Override
    public boolean create(String s, CreateRequest createRequest) {
        return false;
    }

    @Override
    public boolean delete(String name) {
        try (final DruidPooledConnection connection = dataSource.getConnection()) {
            JdbcUtils.simpleDropTable(connection, resource.getDatabase(), name);
            return true;
        } catch (Exception e) {
            throw new MetadataOperationException(e);
        }
    }

    @Override
    public boolean update(String s, UpdateRequest updateRequest) {
        return false;
    }

    @Override
    public List<MysqlTableInfo> listAll() {
        return null;
    }

    @Override
    public List<MysqlTableInfo> list(List<String> strings) {
        return null;
    }

    @Override
    public MysqlTableInfo detail(String s) {
        return null;
    }

    @Override
    public boolean exist(String s) {
        return false;
    }

    @Override
    public ExampleData exampleData(String s, Long number) {
        return null;
    }
}
