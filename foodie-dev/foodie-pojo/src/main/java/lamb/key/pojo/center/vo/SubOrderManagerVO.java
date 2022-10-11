package lamb.key.pojo.center.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/31 2:40
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubOrderManagerVO {
    private String itemId;
    private String itemImg;
    private String itemName;
    private String itemSpecId;
    private String itemSpecName;
    private int price;
    private int buyCounts;
}
