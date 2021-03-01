package site.teamo.biu.infinity.sql.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import site.teamo.biu.infinity.sql.util.BiuDataSource;

;

@Configuration
public class HiveDataSourceInitialization {

    @Autowired
    private Environment env;

    @Bean(name = "hiveDataSource")
    public BiuDataSource hiveDataSource() {
        return BiuDataSource.builder()
                .url(env.getProperty("infinity.hive.jdbc.url"))
                .driverClassName(env.getProperty("infinity.hive.jdbc.driver-class-name"))
                .username(env.getProperty("infinity.hive.jdbc.username"))
                .password(env.getProperty("infinity.hive.jdbc.password"))
                .build()
                .init();
    }
}
