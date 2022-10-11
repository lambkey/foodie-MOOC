package lamb.key.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/7/14 23:42
 * @Version 1.0
 */
@Configuration
@Component
public class PasswordUtils {
    @Bean
    public PasswordEncoder passwordEncoder(){
      return  new BCryptPasswordEncoder();
    }
}
