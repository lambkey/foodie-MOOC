package lamb.key.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lamb.key.mapper.*;
import lamb.key.pojo.*;
import lamb.key.pojo.bo.SubmitOrderBO;
import lamb.key.pojo.vo.MerchantOrdersVO;
import lamb.key.pojo.vo.OrderVO;
import lamb.key.service.AddressService;
import lamb.key.service.ItemService;
import lamb.key.service.OrderService;
import lamb.key.utils.DateUtil;
import lamb.key.utils.enums.OrderStatusEnum;
import lamb.key.utils.enums.YesOrNo;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author JoinYang
 * @date 2022/7/26 23:29
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersMapper ordersMapper;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderItemsMapper orderItemsMapper;

    @Autowired
    private OrderStatusMapper orderStatusMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public OrderVO createOrder(SubmitOrderBO submitOrderBO) {

        String userId = submitOrderBO.getUserId();
        String addressId = submitOrderBO.getAddressId();
        String itemSpecIds = submitOrderBO.getItemSpecIds();
        Integer payMethod = submitOrderBO.getPayMethod();
        String leftMsg = submitOrderBO.getLeftMsg();

        // 包邮费用设置为0
        Integer postAmount = 0;

        String orderId = sid.nextShort();
        // 1.新订单数据保存
        Orders orders = new Orders();
        orders.setId(orderId);
        orders.setUserId(userId);
        UserAddress userAddress = addressService.queryOrderAddress(userId, addressId);
        orders.setReceiverName(userAddress.getReceiver());
        orders.setReceiverMobile(userAddress.getMobile());
        orders.setReceiverAddress(userAddress.getProvince()+" "+userAddress.getCity()+" "+userAddress.getDistrict()+" "+userAddress.getDetail());
        orders.setPostAmount(postAmount);
        orders.setPayMethod(payMethod);
        orders.setLeftMsg(leftMsg);
        orders.setIsComment(YesOrNo.NO.type);
        orders.setIsDelete(YesOrNo.NO.type);
        orders.setCreatedTime(new Date());
        orders.setUpdatedTime(new Date());

        // 2.循环根据itemSpecIds保存订单商品信息表
        String[] split = itemSpecIds.split(",");
        Integer totalAmount = 0; // 商品原价累计
        Integer realPayAmount = 0; // 优惠后的实际支付价格累计

        for (String s : split) {

            //  TODO 整合redis后，商品购买的数量重新从redis的购物车中年获取

            int buyCounts = 1;

            // 2.1 根据规格id，查询规格的具体信息,主要获取价格
            ItemsSpec itemsSpec = itemService.queryItemSpecInfo(s);
            totalAmount += itemsSpec.getPriceNormal()*buyCounts;
            realPayAmount += itemsSpec.getPriceDiscount()*buyCounts;

            // 2.2 根据商品id,获得商品信息以及商品图片
            String itemId = itemsSpec.getItemId();
            Items items = itemService.queryItemByItemId(itemId);
            ItemsImg itemsImg = itemService.queryItemImgIsMainByItemId(itemId);

            // 2.3 循环保存子订单数据到数据库
            String subOrderId = sid.nextShort();
            OrderItems orderItems = new OrderItems();
            orderItems.setId(subOrderId);
            orderItems.setOrderId(orderId);
            orderItems.setItemId(itemId);
            orderItems.setItemName(items.getItemName());
            orderItems.setItemImg(itemsImg.getUrl());
            orderItems.setBuyCounts(buyCounts);
            orderItems.setItemSpecId(s);
            orderItems.setItemSpecName(itemsSpec.getName());
            orderItems.setPrice(itemsSpec.getPriceDiscount());
            orderItemsMapper.insert(orderItems);

            // 2.4 用户提交订单以后，规格表需要扣除库存
            itemService.decreaseItemSpecStock(s,buyCounts);
        }

        orders.setTotalAmount(totalAmount);
        orders.setRealPayAmount(realPayAmount);
        ordersMapper.insert(orders);

        // 3.保存订单状态表
        OrderStatus waitPayOrderStatus = new OrderStatus();
        waitPayOrderStatus.setOrderId(orderId);
        waitPayOrderStatus.setOrderStatus(OrderStatusEnum.WAIT_PAY.type);
        waitPayOrderStatus.setCreatedTime(new Date());
        orderStatusMapper.insert(waitPayOrderStatus);

        // 4.构建商户订单，用于传给支付中心
        MerchantOrdersVO merchantOrdersVO = new MerchantOrdersVO();
        merchantOrdersVO.setMerchantOrderId(orderId);
        merchantOrdersVO.setMerchantUserId(userId);
        merchantOrdersVO.setAmount(totalAmount+postAmount);
        merchantOrdersVO.setPayMethod(payMethod);

        OrderVO orderVO = new OrderVO();
        orderVO.setOrderId(orderId);
        orderVO.setMerchantOrdersVO(merchantOrdersVO);

        return orderVO;
    }

    @Transactional(propagation =Propagation.REQUIRED)
    @Override
    public void updateOrderStatus(String orderId, Integer orderStatus) {
        OrderStatus paidStatus = new OrderStatus();
        paidStatus.setOrderId(orderId);
        paidStatus.setOrderStatus(orderStatus);
        paidStatus.setPayTime(new Date());
        orderStatusMapper.updateById(paidStatus);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public OrderStatus queryOrderStatus(String orderId) {
        OrderStatus orderStatus = orderStatusMapper.selectById(orderId);

        return orderStatus;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void closeOrder() {
        Map<String, Object> map = new HashMap<>();
        map.put("order_status",OrderStatusEnum.WAIT_PAY.type);
        List<OrderStatus> orderStatuses = orderStatusMapper.selectByMap(map);
        for (OrderStatus orderStatus : orderStatuses) {
            Date createdTime = orderStatus.getCreatedTime();
            int i = DateUtil.daysBetween(createdTime, new Date());
            if (i>=1){
                doCloseOrder(orderStatus);
            }
        }
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void doCloseOrder(OrderStatus orderStatus){
        orderStatus.setCloseTime(new Date());
        orderStatus.setOrderStatus(OrderStatusEnum.CLOSE.type);
        orderStatusMapper.updateById(orderStatus);
    }

}
