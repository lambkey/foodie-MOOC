package lamb.key.pojo.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/26 23:05
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubmitOrderBO {
    private String userId;
    private String itemSpecIds;
    private String addressId;
    private Integer payMethod;
    private String leftMsg;
}
