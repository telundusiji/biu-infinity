package site.teamo.biu.infinity.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author 爱做梦的锤子
 * @create 2020/8/4
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${infinity.swagger.enable:false}")
    private boolean swaggerEnable;

    @Value("${infinity.project.version}")
    private String version;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(swaggerEnable)
                .select()
                .apis(RequestHandlerSelectors.basePackage("site.teamo.biu.infinity.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Infinity Platform")
                .description("Infinity is a big data platform")
                .termsOfServiceUrl("http://te-amo.site")
                .version(version)
                .build();
    }

}
