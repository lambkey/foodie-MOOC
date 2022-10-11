package lamb.key.service;

import lamb.key.pojo.Items;
import lamb.key.pojo.ItemsImg;
import lamb.key.pojo.ItemsParam;
import lamb.key.pojo.ItemsSpec;
import lamb.key.pojo.vo.ItemsCommentsCountVO;
import lamb.key.pojo.vo.ItemsCommentsVO;
import lamb.key.pojo.vo.SearchItemVO;
import lamb.key.utils.PagedGridResult;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/20 13:38
 * @Version 1.0
 */
public interface ItemService {

    /*
    * 根据商品id查询出商品的具体信息
    * */
     public Items queryItemDetailByItemId(String itemId);


     /*
     * 根据商品id查询出该商品的所有规格
     * */
     public List<ItemsSpec> queryItemSpecListByItemID(String itemId);

     /*
     * 根据商品id查询商品的参数
     * */
    public ItemsParam queryItemParamByItemId(String itemId);

    /*
    * 根据商品id查询商品的所有图片
    * */
    public List<ItemsImg> queryItemImageByItemId(String itemId);

    /*
    * 根据商品id查询商品评价的 商品评价总数 商品好评评价数 商品良好评价数 商品差评评价数
    * */
    public ItemsCommentsCountVO queryItemCommentCount(String itemId);

    /*
    * 根据商品的id查询商品的评价
    * */
    public PagedGridResult queryPageComments(String itemId, Integer level, Integer page, Integer pageSize);

    /*
    * 搜索框 关键词 查询项目的 价格 销量 图片 项目名称 (sort为按某种方式排序 k项目名 c销售数量 p按价格)
    * */
    public PagedGridResult searchItemByKeywordsPage(String keyword,String sort,Integer page,Integer pageSize);

    /*
    * 首页三级分类搜索相应的分类商品
    * */
    public PagedGridResult searchItemByThirdCatPage(Integer catId,String sort,Integer page,Integer pageSize);

    /*
    * 根据商品规格id获取商品规格对象的具体信息
    * */
    public ItemsSpec queryItemSpecInfo(String specId);

    /*
    * 根据商品id获取商品信息
    * */
    public Items queryItemByItemId(String itemId);

    /*
    * 根据商品id获取商品图片
    * */
    public ItemsImg queryItemImgIsMainByItemId(String itemId);


    /*
    * 创建某个商品的订单,会对某商品的某种规格的数量做相减处理
    *
    * */
    public void decreaseItemSpecStock(String specId,int buyCounts);
}
