package lamb.key.utils.enums;

/**
 * @author JoinYang
 * @date 2022/7/22 1:10
 * @Version 1.0
 * 支付方式
 */
public enum CommentsLevel {

    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"差评");

    public final Integer type;
    public final String value;

    CommentsLevel(Integer type,String value){
        this.type=type;
        this.value=value;
    }
}
