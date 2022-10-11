package lamb.key.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author JoinYang
 * @date 2022/6/9 14:42
 * @Version 1.0
 *
 * 腾讯COS存储服务配置类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties("cos.tencent")
@Configuration
public class COSPropertiesConfig {
    private String accesskey;
    private String secretKey;
    private String bucket;
    private String bucketName;
    private String path;
}
