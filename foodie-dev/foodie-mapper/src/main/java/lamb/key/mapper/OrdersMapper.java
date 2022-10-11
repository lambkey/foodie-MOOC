package lamb.key.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.pojo.Orders;
import lamb.key.pojo.center.vo.OrderManagerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表; Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    public List<OrderManagerVO> queryOrderStatusForUser(@Param("paramsMap")Map<String, Object> map);
    public void updateOrdersIsDelete(@Param("paramMap") Map<String, Object> map);
    public void updateIsCommented(@Param("paramsMap") Map<String, Object> map);
}
