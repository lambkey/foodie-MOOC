package lamb.key.utils.enums;

/**
 * @author JoinYang
 * @date 2022/7/26 23:18
 * @Version 1.0
 */
public enum PayMethod {

    WEIXIN(1,"微信"),
    ALIPAY(2,"支付宝");

    public final Integer type;
    public final String value;

    PayMethod(Integer type,String value){
        this.type=type;
        this.value=value;
    }
}
