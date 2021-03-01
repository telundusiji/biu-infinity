package site.teamo.biu.infinity.sql.util;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
public class BiuDataSource {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
    private GenericObjectPool<Connection> pool;

    public BiuDataSource init() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(3);
        pool = new GenericObjectPool<>(new PoolFactory(), config);
        return this;
    }

    public Connection getConnection(long usageTime) {
        try {
            return pool.borrowObject(usageTime);
        } catch (Exception e) {
            throw new RuntimeException("get connection[" + usageTime + "ms] failed for jdbc url " + url, e);
        }
    }

    public Connection getConnection() {
        try {
            return pool.borrowObject();
        } catch (Exception e) {
            throw new RuntimeException("get connection failed for jdbc url " + url, e);
        }
    }

    public void returnConnection(Connection connection) {
        if (connection == null) {
            return;
        }
        pool.returnObject(connection);
    }


    public class PoolFactory implements PooledObjectFactory<Connection> {

        @Override
        public PooledObject<Connection> makeObject() throws Exception {
            Class.forName(driverClassName);
            Connection connection = DriverManager.getConnection(url, username, password);
            return new DefaultPooledObject<>(connection);
        }

        @Override
        public void destroyObject(PooledObject<Connection> pooledObject) throws Exception {
            log.info("Connection[{}] destroy", url);
            Connection connection = pooledObject.getObject();
            if (!connection.isClosed()) {
                connection.close();
            }
        }

        @Override
        public boolean validateObject(PooledObject<Connection> pooledObject) {
            try {
                return !pooledObject.getObject().isClosed();
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        public void activateObject(PooledObject<Connection> pooledObject) throws Exception {

        }

        @Override
        public void passivateObject(PooledObject<Connection> pooledObject) throws Exception {

        }
    }
}
