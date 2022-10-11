package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/20 14:16
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SixNewsItemVO {
    private String itemId;
    private String itemName;
    private String itemUrl;
    private String createdTime;
}
