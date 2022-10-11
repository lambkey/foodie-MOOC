package lamb.key.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lamb.key.mapper.CarouselMapper;
import lamb.key.pojo.Carousel;
import lamb.key.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/7/18 16:51
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Carousel> getCarousel(Integer isShow) {

        QueryWrapper queryWrapper = new QueryWrapper();

        queryWrapper.eq("is_show",isShow);
        queryWrapper.orderByDesc("sort");

        List<Carousel> carouselList = carouselMapper.selectList(queryWrapper);

        return carouselList;
    }
}
