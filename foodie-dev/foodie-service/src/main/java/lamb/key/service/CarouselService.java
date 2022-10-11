package lamb.key.service;

import lamb.key.pojo.Carousel;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/18 16:45
 * @Version 1.0
 */
public interface CarouselService {

    /*
    * 获取所有可以展示的轮播图轮播图 isShow=1代表着可以展示的状态
    * */
    List<Carousel> getCarousel(Integer isShow);

}
