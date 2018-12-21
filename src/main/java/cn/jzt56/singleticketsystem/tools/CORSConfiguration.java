package cn.jzt56.singleticketsystem.tools;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IDEA
 * 前后台整合后，全局CORS配置：跨域
 * @author: CHENG QI
 * @Date: 2018/12/21
 * Time: 11:53
 */
@Configuration
public class CORSConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**");
                registry.addMapping("/**")
                        .allowedOrigins("*")   //加载的控制类？
                        .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                        .allowCredentials(false).maxAge(3600);
            }
            };
        }
    }

