package site.teamo.biu.infinity.sql.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import site.teamo.biu.infinity.fweb.common.util.BiuDataSource;

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
