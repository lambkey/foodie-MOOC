package lamb.key.pojo.center.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/8/2 3:39
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsCommentsForUserVO {
    private String specName;
    private String createdTime;
    private String itemName;
    private String content;
    private String itemImg;
}
