package site.teamo.biu.infinity.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/4
 */

@SpringBootApplication
@MapperScan(basePackages = {"site.teamo.biu.infinity.web.dao"})
public class InfinityApplication {

    public static void main(String[] args) {
        SpringApplication.run(InfinityApplication.class, args);
    }

}
