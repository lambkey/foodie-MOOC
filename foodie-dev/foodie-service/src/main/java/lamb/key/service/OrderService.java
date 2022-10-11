package lamb.key.service;

import lamb.key.pojo.OrderStatus;
import lamb.key.pojo.bo.SubmitOrderBO;
import lamb.key.pojo.vo.OrderVO;

/**
 * @author JoinYang
 * @date 2022/7/26 23:29
 * @Version 1.0
 */
public interface OrderService {
    /*
    * 用于创建订单相关信息
    * */
    public OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /*
    * 修改订单状态
    * */
    public void updateOrderStatus(String orderId,Integer orderStatus);

    /*
    * 前端显示二维码的时候会轮询，定时向后端发送请求，当订单状态为20时，则为已支付
    * 查询订单状态
    * */
    public OrderStatus queryOrderStatus(String orderId);

    /*
    * 关闭未付款的订单的状态 10-->50
    * */
    public void closeOrder();
}
