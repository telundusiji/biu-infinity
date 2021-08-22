package site.teamo.biu.infinity.connection.mysql8025;

import site.teamo.biu.infinity.connection.jdbc.JdbcConnection;
import site.teamo.biu.infinity.connection.jdbc.JdbcResource;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 21:20
 * @Description:
 */
public class MysqlConnection extends JdbcConnection {
    public MysqlConnection(JdbcResource resource) {
        super(resource);
    }

    @Override
    public MetadataOperator getMetadataOperator() {
        return new MysqlMetadataOperator(dataSource, resource);
    }
}
