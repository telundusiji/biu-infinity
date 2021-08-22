package site.teamo.biu.infinity.connection.jdbc;

import site.teamo.biu.infinity.connection.sdk.exception.MetadataOperationException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Created: 爱做梦的锤子
 * @Date: 2021/8/21 22:29
 * @Description:
 */
public class JdbcUtils {

    public static void execute(Connection connection, String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            throw new MetadataOperationException("Execute sql[" + sql + "]", e);
        }
    }

    private static final String SIMPLE_DROP_TABLE_SQL = "drop table if exists %s.%s";

    public static void simpleDropTable(Connection connection, String database, String tableName){
        String sql = String.format(SIMPLE_DROP_TABLE_SQL, database, tableName);
        execute(connection, sql);
    }
}
