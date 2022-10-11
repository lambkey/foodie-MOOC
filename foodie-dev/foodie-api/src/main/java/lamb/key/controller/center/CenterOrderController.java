package lamb.key.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.pojo.center.vo.OrderStatusCountsVO;
import lamb.key.service.center.CenterOrderService;
import lamb.key.utils.JSONResult;
import lamb.key.utils.PagedGridResult;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JoinYang
 * @date 2022/7/31 2:57
 * @Version 1.0
 */
@Api(value = "用户中心-用户管理订单api", tags = {"用户中心-用户管理订单api"})
@RestController
@RequestMapping("/myorders")
public class CenterOrderController {

    @Autowired
    private CenterOrderService centerOrderService;


    @ApiOperation(value = "查询所有订单", notes = "查询所有订单")
    @PostMapping("/query")
    public JSONResult queryAllOrderByOrderStatus(
            @RequestParam("userId") String userId,
            @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        PagedGridResult pagedGridResult = centerOrderService.queryOrderStatusPageForUser(userId, orderStatus, page, pageSize);

        return JSONResult.successNeedData(pagedGridResult);
    }

    @ApiOperation(value = "删除已近关闭的订单", notes = "删除已近关闭的订单")
    @PostMapping("/delete")
    public JSONResult deleteUserOrder(
            @RequestParam("userId") String userId,
            @RequestParam("orderId") String orderId
    ) {
        centerOrderService.deleteOrderOfUser(userId, orderId);

        return JSONResult.successWithoutData();
    }

    // 仅仅只是模拟客服后端发货
    @ApiOperation(value = "模拟订单已付款后，设置订单状态为已发货", notes = "模拟订单已付款后，设置订单状态为已发货")
    @PostMapping("/deliver")
    public JSONResult deliver(
            @RequestParam String orderId
    ) {
        if (StringUtils.isBlank(orderId)) {
            return JSONResult.FailureNeedMessage("订单号不能为空");
        }
        centerOrderService.updateOrderDeliverStatus(orderId);
        return JSONResult.successWithoutData();
    }

    @ApiOperation(value = "确认收货", notes = "确认收货")
    @PostMapping("/confirmReceive")
    public JSONResult confirmReceive(
            @RequestParam("userId") String userId,
            @RequestParam("orderId") String orderId){
        centerOrderService.confirmReceive(userId,orderId);
        return JSONResult.successWithoutData();
    }

    @ApiOperation(value = "用户中心显示订单的状态个数",notes ="用户中心显示订单的状态个数" )
    @PostMapping("/statusCounts")
    public JSONResult statusCounts(
            @RequestParam("userId") String userId
    ){
        OrderStatusCountsVO orderStatusCounts = centerOrderService.getOrderStatusCounts(userId);
        return JSONResult.successNeedData(orderStatusCounts);
    }

    @ApiOperation(value = "用户订单动向显示",notes = "用户订单动向显示")
    @PostMapping("/trend")
    public JSONResult trend(
            @RequestParam("userId") String userId,
            @RequestParam(name = "page",defaultValue = "1") Integer page,
            @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize
    ){
        PagedGridResult myOrderTrend = centerOrderService.getMyOrderTrend(userId, page, pageSize);

        return JSONResult.successNeedData(myOrderTrend);
    }
}
