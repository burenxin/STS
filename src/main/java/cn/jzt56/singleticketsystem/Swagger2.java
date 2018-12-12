package cn.jzt56.singleticketsystem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created with IDEA
 * 通过访问：http://localhost:8080/swagger-ui.html#/
 * @author: CHENG QI
 * @Date: 2018/12/11
 * Time: 8:49
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //选择controller包
                .apis(RequestHandlerSelectors.basePackage("cn.jzt56.singleticketsystem.swaggercontroller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                //自定义信息可按需求填写
                .title("SpringBoot中使用Swagger构建RESTful APIs")
                .description("测试")
                .termsOfServiceUrl("https://www.cnblogs.com/fengli9998/p/7522973.html")
                .contact("ChengQi")
                .version("1.0")
                .build();
    }
}
