package lamb.key.utils.enums;

/**
 * @author JoinYang
 * @date 2022/7/27 0:07
 * @Version 1.0
 */
public enum YesOrNo {

    YES(1,"是"),
    NO(0,"否");

    public final Integer type;
    public final String value;

    YesOrNo(Integer type,String value){
        this.type=type;
        this.value=value;
    }
}
