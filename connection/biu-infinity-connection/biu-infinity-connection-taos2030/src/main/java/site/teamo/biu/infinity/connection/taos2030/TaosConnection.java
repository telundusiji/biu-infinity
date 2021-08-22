package site.teamo.biu.infinity.connection.taos2030;

import site.teamo.biu.infinity.connection.jdbc.JdbcConnection;
import site.teamo.biu.infinity.connection.jdbc.JdbcResource;
import site.teamo.biu.infinity.connection.sdk.core.MetadataOperator;
import site.teamo.biu.infinity.connection.sdk.core.ResourceConnection;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 22:52
 * @Description:
 */
public class TaosConnection extends JdbcConnection {
    public TaosConnection(JdbcResource resource) {
        super(resource);
    }

    @Override
    public MetadataOperator getMetadataOperator() {
        return null;
    }
}
