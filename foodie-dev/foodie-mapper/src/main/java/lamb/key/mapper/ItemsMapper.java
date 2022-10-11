package lamb.key.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import lamb.key.pojo.Items;
import lamb.key.pojo.vo.SearchItemVO;
import lamb.key.pojo.vo.ShopCartVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品表 商品信息相关表：分类表，商品图片表，商品规格表，商品参数表 Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
public interface ItemsMapper extends BaseMapper<Items> {
   public List<SearchItemVO> searchItemByKeywords(@Param("paramsMap") Map<String, Object> paramsMap);
   public List<SearchItemVO> searchItemByThirdCat(@Param("paramsMap") Map<String, Object> paramsMap);
   public List<ShopCartVO> queryItemsBySpecIds(@Param("paramsMap") List<String> paramsMap);
}
