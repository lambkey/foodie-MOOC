package lamb.key.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.mapper.CategoryMapper;
import lamb.key.mapper.CategoryMapperCustom;
import lamb.key.pojo.Category;
import lamb.key.pojo.vo.CategoryCustomVO;
import lamb.key.pojo.vo.NewsItemVO;
import lamb.key.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/19 1:37
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CategoryMapperCustom categoryMapperCustom;


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewsItemVO> queryNewsItemCommodityOfRootCategory(Integer rootId) {

        List<NewsItemVO> newsItemVOList = categoryMapperCustom.queryNewsItemCommodityOfRootCategory(rootId);

        return newsItemVOList;
    }


    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryRootCategory() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type",1);
        List<Category> categoryList = categoryMapper.selectList(queryWrapper);
        return categoryList;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryCustomVO> queryFatherAndKidCategory(Integer rootId) {
        List<CategoryCustomVO> categoryCustomVOList = categoryMapperCustom.queryFatherAndKidCategory(rootId);
        return categoryCustomVOList;
    }


}
