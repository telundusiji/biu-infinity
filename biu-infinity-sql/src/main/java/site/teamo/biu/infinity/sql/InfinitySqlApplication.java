package site.teamo.biu.infinity.sql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication(scanBasePackages = "site.teamo.biu.infinity")
@MapperScan(basePackages = "site.teamo.biu.infinity.sql.dao")
@EnableDiscoveryClient
public class InfinitySqlApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfinitySqlApplication.class, args);
    }
}
