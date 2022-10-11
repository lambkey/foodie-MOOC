package lamb.key.service.center;

import lamb.key.pojo.OrderStatus;
import lamb.key.pojo.Orders;
import lamb.key.pojo.center.vo.OrderStatusCountsVO;
import lamb.key.utils.PagedGridResult;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/31 0:08
 * @Version 1.0
 */
public interface CenterOrderService {
    /*
    * 根据用户的id、用户订单的状态查询用户的订单
    * */
    public PagedGridResult queryOrderStatusPageForUser(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /*
    * 删除已经关闭的用户订单
    * */
    public void deleteOrderOfUser(String userId,String orderId);

    /*
    * 模拟商品发货
    *
    * */
    public void updateOrderDeliverStatus(String orderId);

    /*
    * 用户确认收获
    * */
    public void confirmReceive(String userId, String orderId);

    /*
    * 查询用户订单数
    * */
    public OrderStatusCountsVO getOrderStatusCounts(String userId);

    /*
    * 查询订单的动向
    * */
    public PagedGridResult getMyOrderTrend(String userId,Integer page,Integer pageSize);

}
