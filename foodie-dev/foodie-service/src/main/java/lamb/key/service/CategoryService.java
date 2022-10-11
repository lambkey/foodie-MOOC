package lamb.key.service;

import lamb.key.pojo.Category;
import lamb.key.pojo.vo.CategoryCustomVO;
import lamb.key.pojo.vo.NewsItemVO;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/19 1:32
 * @Version 1.0
 */
public interface CategoryService {
    /*
    * 获取商品分类,大分类
    *
    * */
    List<Category> queryRootCategory();

    /*
    * 根据一级分类id查询子分类信息
    * */
    List<CategoryCustomVO> queryFatherAndKidCategory(Integer rootId);


    /*
    * 根据大分类的id获得一级分类的最新的6个商品
    * */
    List<NewsItemVO> queryNewsItemCommodityOfRootCategory(Integer rootId);

}
