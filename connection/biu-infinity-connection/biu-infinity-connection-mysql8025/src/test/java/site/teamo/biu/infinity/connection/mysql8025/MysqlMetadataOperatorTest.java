package site.teamo.biu.infinity.connection.mysql8025;

import site.teamo.biu.infinity.connection.jdbc.JdbcConnection;
import site.teamo.biu.infinity.connection.jdbc.JdbcResource;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 21:31
 * @Description:
 */
public class MysqlMetadataOperatorTest {

    @org.junit.Test
    public void listAll() {
        JdbcConnection jdbcConnection = new MysqlConnection(new JdbcResource());
        MysqlMetadataOperator metadataOperator = jdbcConnection.getMetadataOperator(MysqlMetadataOperator.class);
    }
}