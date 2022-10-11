package lamb.key.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.pojo.ItemsComments;
import lamb.key.pojo.vo.CategoryCustomVO;
import lamb.key.pojo.vo.NewsItemVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/19 2:56
 * @Version 1.0
 */
public interface CategoryMapperCustom extends BaseMapper<CategoryCustomVO> {

    List<CategoryCustomVO> queryFatherAndKidCategory(@Param("rootId") Integer rootId);

    List <NewsItemVO>queryNewsItemCommodityOfRootCategory(@Param("rootId")Integer rootId);
}
