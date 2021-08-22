package site.teamo.biu.infinity.connection.jdbc;

import com.alibaba.druid.pool.DruidDataSource;
import site.teamo.biu.infinity.connection.sdk.core.ResourceConnection;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 20:50
 * @Description:
 */
public  abstract class JdbcConnection extends ResourceConnection<JdbcResource> {

    protected DruidDataSource dataSource;

    public JdbcConnection(JdbcResource resource) {
        super(resource);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUsername(resource.getUsername());
        druidDataSource.setPassword(resource.getPassword());
        druidDataSource.setUrl(resource.getUrl());
        druidDataSource.setDriverClassName(resource.getDriver());
        this.dataSource = druidDataSource;
    }

    @Override
    public synchronized void close() throws Exception {
        if (!isOpen) {
            return;
        }
        dataSource.close();
        isOpen = false;
    }
}
