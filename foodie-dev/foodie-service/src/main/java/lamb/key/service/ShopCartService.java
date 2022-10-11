package lamb.key.service;

import lamb.key.pojo.vo.ShopCartVO;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/25 14:01
 * @Version 1.0
 */
public interface ShopCartService {
    /*
    * 根据前端用户添加进去cookie里面商品的规格id
    * 查询商品具体信息充填购物车
    * */
    public List<ShopCartVO> queryItemsBySpecIds(String specIds);
}
