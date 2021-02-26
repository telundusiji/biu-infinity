package site.teamo.biu.infinity.sql;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = "site.teamo.biu.infinity")
@EnableDiscoveryClient
public class InfinitySqlApplication {
}
