package lamb.key.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoinYang
 * @date 2022/7/28 1:16
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MerchantOrdersVO {
    private String merchantOrderId;         // 商户订单号
    private String merchantUserId;          // 商户方的发起用户的用户主键id
    private Integer amount;                 // 实际支付总金额
    private Integer payMethod;              // 支付方式 1:微信 2:支付宝
    private String returnUrl;               // 支付成功后的回调地址(自定义)
}
