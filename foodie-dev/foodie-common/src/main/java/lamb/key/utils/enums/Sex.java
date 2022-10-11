package lamb.key.utils.enums;

/**
 * @author JoinYang
 * @date 2022/7/15 1:26
 * @Version 1.0
 */
public enum Sex {
    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");

    public final Integer type;
    public final String value;

    Sex(Integer type,String value){
        this.type=type;
        this.value=value;
    }


}
