package site.teamo.biu.infinity.sql.controller.hive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.teamo.biu.infinity.fweb.common.util.BiuJSONResult;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@RestController
@RequestMapping("/sql/hive")
public class HiveSqlController {

    @Autowired
    @Qualifier("hiveDataSource")
    private DataSource dataSource;

    @GetMapping("/execute")
    public BiuJSONResult execute(@RequestParam String sql) throws SQLException {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        return BiuJSONResult.ok();
    }
}
