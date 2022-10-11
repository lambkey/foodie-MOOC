package lamb.key.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.pojo.ItemsComments;
import lamb.key.pojo.center.vo.ItemsCommentsForUserVO;
import lamb.key.pojo.vo.ItemsCommentsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品评价表  Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
public interface ItemsCommentsMapper extends BaseMapper<ItemsComments> {
    public List<ItemsCommentsVO> queryItemComments(@Param("paramsMap") Map<String,Object> paramsMap);
    public List<ItemsCommentsForUserVO> queryItemCommentsByUserId(@Param("paramsMap") Map<String,Object> paramsMap);
}
