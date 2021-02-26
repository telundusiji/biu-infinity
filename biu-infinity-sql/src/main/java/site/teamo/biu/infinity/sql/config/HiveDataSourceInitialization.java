package site.teamo.biu.infinity.sql.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class HiveDataSourceInitialization {

    @Bean(name = "hiveDataSource")
    @ConfigurationProperties(prefix = "infinity.hive.jdbc")
    public DataSource hiveDataSource() {
        return new DataSourceProperties().initializeDataSourceBuilder()
                .build();
    }

}
