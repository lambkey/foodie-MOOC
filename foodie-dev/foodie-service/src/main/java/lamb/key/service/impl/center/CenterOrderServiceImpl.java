package lamb.key.service.impl.center;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lamb.key.mapper.OrderStatusMapper;
import lamb.key.mapper.OrdersMapper;
import lamb.key.pojo.OrderStatus;
import lamb.key.pojo.Orders;
import lamb.key.pojo.center.vo.OrderManagerVO;
import lamb.key.pojo.center.vo.OrderStatusCountsVO;
import lamb.key.service.center.CenterOrderService;
import lamb.key.utils.PagedGridResult;
import lamb.key.utils.enums.OrderStatusEnum;
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
 * @date 2022/7/31 0:09
 * @Version 1.0
 */
@Service
public class CenterOrderServiceImpl implements CenterOrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryOrderStatusPageForUser(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("orderStatus",orderStatus);
        PageHelper.startPage(page, pageSize);
        List<OrderManagerVO> list = ordersMapper.queryOrderStatusForUser(map);
        PagedGridResult pagedGridResult = pageHelper(page, list);
        return pagedGridResult;
    }

    private PagedGridResult pageHelper(Integer page,List<?> list){

        PageInfo<?> pageInfo = new PageInfo<>(list);

        PagedGridResult pagedGridResult = new PagedGridResult();

        pagedGridResult.setPage(page);
        pagedGridResult.setRecords(pageInfo.getTotal());
        // 总页数
        pagedGridResult.setTotal(pageInfo.getPages());
        pagedGridResult.setRows(list);

        return pagedGridResult;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteOrderOfUser(String userId,String orderId){
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);
        map.put("orderId",orderId);
        ordersMapper.updateOrdersIsDelete(map);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateOrderDeliverStatus(String orderId) {
        Map<String, Object> map = new HashMap<>();
        map.put("deliverTime",new Date());
        map.put("orderId",orderId);
        map.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.type);
        orderStatusMapper.updateOrderStatusToDeliver(map);
    }

    @Override
    public void confirmReceive(String userId, String orderId) {
        Map <String, Object>map = new HashMap();
        map.put("orderId",orderId);
        map.put("orderStatus",OrderStatusEnum.SUCCESS.type);
        map.put("successTime",new Date());
        orderStatusMapper.confirmReceive(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId",userId);


        map.put("orderStatus", OrderStatusEnum.WAIT_PAY.type);
        int waitPayCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus",OrderStatusEnum.WAIT_DELIVER.type);
        int waitDeliverCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus",OrderStatusEnum.WAIT_RECEIVE.type);
        int waitConfirmCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus",OrderStatusEnum.SUCCESS.type);
        map.put("isComment",0);
        int waitCommentCounts = orderStatusMapper.getMyOrderStatusCounts(map);

        OrderStatusCountsVO orderStatusCountsVO = new OrderStatusCountsVO();
        orderStatusCountsVO.setWaitPayCounts(waitPayCounts);
        orderStatusCountsVO.setWaitDeliverCounts(waitDeliverCounts);
        orderStatusCountsVO.setWaitReceiveCounts(waitConfirmCounts);
        orderStatusCountsVO.setWaitCommentsCounts(waitCommentCounts);
        return orderStatusCountsVO;
    }

    @Override
    public PagedGridResult getMyOrderTrend(String userId,Integer page,Integer pageSize) {
        Map<String, Object> map = new HashMap();
        map.put("userId",userId);
        PageHelper.startPage(page,pageSize);
        List<OrderStatus> myOrderTrend = orderStatusMapper.getMyOrderTrend(map);
        PagedGridResult pagedGridResult = pageHelper(page, myOrderTrend);
        return pagedGridResult;
    }

}
