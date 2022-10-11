package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/19 2:47
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubCategoryVO {
    private Integer subId;
    private String subName;
    private Integer subType;
    private Integer subFatherId;
}
