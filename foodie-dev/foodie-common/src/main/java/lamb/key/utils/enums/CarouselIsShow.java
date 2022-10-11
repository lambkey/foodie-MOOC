package lamb.key.utils.enums;

/**
 * @author JoinYang
 * @date 2022/7/18 17:28
 * @Version 1.0
 */
public enum CarouselIsShow {

    noShow(0,"不展示"),
    isShow(1,"展示");


    public final Integer type;
    public final String value;

    CarouselIsShow(Integer type,String value){
        this.type=type;
        this.value=value;
    }
}
