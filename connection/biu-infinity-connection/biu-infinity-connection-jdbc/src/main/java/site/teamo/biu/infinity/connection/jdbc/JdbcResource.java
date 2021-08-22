package site.teamo.biu.infinity.connection.jdbc;

import lombok.Data;
import lombok.experimental.Accessors;
import site.teamo.biu.infinity.connection.sdk.core.Resource;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 20:48
 * @Description:
 */
@Data
@Accessors(chain = true)
public class JdbcResource implements Resource {

    private String name;

    private String url;

    private String username;

    private String password;

    private String database;

    private String driver;

    @Override
    public String name() {
        return this.name;
    }
}
