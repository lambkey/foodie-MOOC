package lamb.key.config;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author JoinYang
 * @date 2022/7/16 19:38
 * @Version 1.0
 */
/*
* 从前端请求到后端是前端和后端为不同的端口，所以涉及到跨域的问题
* */
@Configuration
@NoArgsConstructor
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        // 1.添加Cors配置信息
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8848");

        // 2.设置是否发送cookie信息
        corsConfiguration.setAllowCredentials(true);

        // 3.设置允许请求的方式
        corsConfiguration.addAllowedMethod("*");

        // 4.设置允许的header
        corsConfiguration.addAllowedHeader("*");

        // 5.为url添加映射路径
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);

        // 6.返回重新定义好的source
        return new CorsFilter(source);
    }

}
