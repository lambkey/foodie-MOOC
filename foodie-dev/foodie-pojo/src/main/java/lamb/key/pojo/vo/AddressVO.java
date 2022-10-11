package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/26 0:50
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressVO {
    private String id;
    private String receiver;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String detail;
    private int isDefault;
}
