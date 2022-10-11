package lamb.key.controller;

import org.springframework.stereotype.Component;

/**
 * @author JoinYang
 * @date 2022/7/28 1:34
 * @Version 1.0
 */
@Component
public class BaseController {

    // 支付中心的调用地址
    public final String paymentUrl = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    // 微信支付成功 ->支付中心 -> 天天吃货平台 -> 回调通知的url(内网穿透)
    public final String payReturnUrl="http://fuyt6z.natappfree.cc/orders/notifyMerchantOrderPaid";
}
