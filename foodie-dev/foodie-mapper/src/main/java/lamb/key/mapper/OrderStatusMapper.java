package lamb.key.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.pojo.OrderStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单状态表;订单的每个状态更改都需要进行记录
10：待付款  20：已付款，待发货  30：已发货，待收货（7天自动确认）  40：交易成功（此时可以评价）50：交易关闭（待付款时，用户取消 或 长时间未付款，系统识别后自动关闭）
退货/退货，此分支流程不做，所以不加入 Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
public interface OrderStatusMapper extends BaseMapper<OrderStatus> {
    void updateOrderStatusToDeliver(@Param("paramsMap")Map<String, Object> map);
    void confirmReceive(@Param("paramsMap")Map<String, Object> map);
    void updateCommentTime(@Param("paramsMap")Map<String, Object> map);
    int getMyOrderStatusCounts(@Param("paramsMap")Map<String, Object> map);
    List<OrderStatus> getMyOrderTrend(@Param("paramsMap")Map<String, Object> map);
}
