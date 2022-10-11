package lamb.key;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;



/**
 * @author JoinYang
 * @date 2022/7/13 2:29
 * @Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "lamb.key.mapper")
@ComponentScan(basePackages = {"lamb.key","org.n3r.idworker"})
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}

