package lamb.key.service.center;

import lamb.key.pojo.ItemsComments;
import lamb.key.pojo.OrderItems;
import lamb.key.pojo.Orders;
import lamb.key.pojo.center.bo.OrderItemsCommentBO;
import lamb.key.utils.PagedGridResult;
import lamb.key.utils.enums.YesOrNo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/8/2 0:19
 * @Version 1.0
 */
public interface CenterCommentService {

    /*
    * 根据订单di查询orderItems
    * */
    public List<OrderItems> queryPendingComment(String orderId);

    /*
    * 根据用户id 订单id查询用户的订单
    * */
    public Orders queryMyOrder(String userId, String orderId);

    /*
    * 用户进行填写评价
    * */
    public void doTheComment(String orderId, String userId, List<OrderItemsCommentBO> orderItemsCommentBO);

    /*
    * 用户已经做出的评价分页
    * */
    public PagedGridResult queryCommented(String userId, Integer page, Integer pageSize);
}
