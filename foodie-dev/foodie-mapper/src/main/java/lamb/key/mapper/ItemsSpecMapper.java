package lamb.key.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import lamb.key.pojo.ItemsSpec;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 商品规格 每一件商品都有不同的规格，不同的规格又有不同的价格和优惠力度，规格表为此设计 Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
public interface ItemsSpecMapper extends BaseMapper<ItemsSpec> {
    public int decreaseItemSpecStock(@Param("specId") String specId,@Param("pendingCounts") int pendingCounts);
}
