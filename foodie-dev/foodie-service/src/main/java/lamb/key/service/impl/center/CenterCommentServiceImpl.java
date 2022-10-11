package lamb.key.service.impl.center;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lamb.key.mapper.ItemsCommentsMapper;
import lamb.key.mapper.OrderItemsMapper;
import lamb.key.mapper.OrderStatusMapper;
import lamb.key.mapper.OrdersMapper;
import lamb.key.pojo.ItemsComments;
import lamb.key.pojo.OrderItems;
import lamb.key.pojo.Orders;
import lamb.key.pojo.center.bo.OrderItemsCommentBO;
import lamb.key.pojo.center.vo.ItemsCommentsForUserVO;
import lamb.key.service.center.CenterCommentService;
import lamb.key.utils.PagedGridResult;
import lamb.key.utils.enums.YesOrNo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/8/2 0:19
 * @Version 1.0
 */
@Service
public class CenterCommentServiceImpl implements CenterCommentService {

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<OrderItems> queryPendingComment(String orderId) {
        Map<String,Object> map = new HashMap<>();
        map.put("order_id",orderId);
        List<OrderItems> orderItems = orderItemsMapper.selectByMap(map);
        return orderItems;
    }

    /**
     * 查询我的订单
     * @param userId
     * @param orderId
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Orders queryMyOrder(String userId, String orderId) {
        Map<String, Object> orders = new HashMap();
        orders.put("user_id",userId);
        orders.put("id",orderId);
        orders.put("is_delete",YesOrNo.NO.type);
        List<Orders> orders1 = ordersMapper.selectByMap(orders);
        return orders1.get(0);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void doTheComment(String orderId, String userId, List<OrderItemsCommentBO> orderItemsCommentBO) {

        Map<String, Object> map = new HashMap();
        map.put("isComment",1);
        map.put("orderId",orderId);
        map.put("updateTime",new Date());
        ordersMapper.updateIsCommented(map);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("orderId",orderId);
        map1.put("commentTime",new Date());
        orderStatusMapper.updateCommentTime(map1);

        for (OrderItemsCommentBO itemsCommentBO : orderItemsCommentBO) {
            ItemsComments itemsComments = new ItemsComments();
            itemsComments.setId(sid.nextShort());
            itemsComments.setItemId(itemsCommentBO.getItemId());
            itemsComments.setItemName(itemsCommentBO.getItemName());
            itemsComments.setCommentLevel(itemsCommentBO.getCommentLevel());
            itemsComments.setItemSpecId(itemsCommentBO.getItemSpecId());
            itemsComments.setSepcName(itemsCommentBO.getItemSpecName());
            itemsComments.setCreatedTime(new Date());
            itemsComments.setContent(itemsCommentBO.getContent());
            itemsComments.setUpdatedTime(new Date());
            itemsComments.setUserId(userId);
            itemsCommentsMapper.insert(itemsComments);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult  queryCommented(String userId,Integer page,Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        PageHelper.startPage(page,pageSize);
        List<ItemsCommentsForUserVO> itemsComments = itemsCommentsMapper.queryItemCommentsByUserId(map);
        PagedGridResult pagedGridResult = pageHelper(page, itemsComments);
        return pagedGridResult;
    }

    private PagedGridResult pageHelper(Integer page, List<?> list){

        PageInfo<?> pageInfo = new PageInfo<>(list);
        PagedGridResult pagedGridResult = new PagedGridResult();

        pagedGridResult.setPage(page);
        pagedGridResult.setRecords(pageInfo.getTotal());
        // 总页数
        pagedGridResult.setTotal(pageInfo.getPages());
        pagedGridResult.setRows(list);

        return pagedGridResult;
    }
}
