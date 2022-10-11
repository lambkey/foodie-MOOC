package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/22 0:52
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemsCommentsCountVO {
    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
}
