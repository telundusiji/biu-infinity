package site.teamo.biu.infinity.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "site.teamo.biu.infinity.monitor.dao")
@EnableDiscoveryClient
@EnableScheduling
public class InfinityMonitorApplication {
    public static void main(String[] args) {
        SpringApplication.run(InfinityMonitorApplication.class, args);
    }
}
