package lamb.key.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.pojo.ItemsComments;
import lamb.key.pojo.OrderItems;
import lamb.key.pojo.Orders;
import lamb.key.pojo.center.bo.OrderItemsCommentBO;
import lamb.key.service.center.CenterCommentService;
import lamb.key.utils.JSONResult;
import lamb.key.utils.PagedGridResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/8/2 0:00
 * @Version 1.0
 */
@Api(value = "用户评价api", tags = {"用户评价api"})
@RestController
@RequestMapping("/mycomments")
public class CenterCommentController {

    @Autowired
    private CenterCommentService centerCommentService;


    @ApiOperation(value = "查询可以评论的订单", notes = "查询可以评论的订单")
    @PostMapping("/pending")
    public JSONResult myNeedCommentOrder(
            @RequestParam("userId") String userId,
            @RequestParam("orderId") String orderId
    ) {
        Orders orders = centerCommentService.queryMyOrder(userId, orderId);
        if (orders == null) {
            return JSONResult.FailureNeedMessage("订单不存在");
        }

        List<OrderItems> orderItems = centerCommentService.queryPendingComment(orderId);

        return JSONResult.successNeedData(orderItems);
    }


    @ApiOperation(value = "评论订单的具体商品", notes = "评论订单的具体商品")
    @PostMapping("/saveList")
    public JSONResult makeComment(
            @RequestParam("userId") String userId,
            @RequestParam("orderId") String orderId,
            @RequestBody List<OrderItemsCommentBO> orderItemsCommentBO
    ) {
        Orders orders = centerCommentService.queryMyOrder(userId, orderId);
        if (orders == null) {
            return JSONResult.FailureNeedMessage("订单不存在");
        }

        if (orderItemsCommentBO == null || orderItemsCommentBO.isEmpty() || orderItemsCommentBO.size() == 0) {
            return JSONResult.FailureNeedMessage("评论内容不能为空");
        }

        centerCommentService.doTheComment(orderId, userId, orderItemsCommentBO);

        return JSONResult.successWithoutData();
    }

    @ApiOperation(value = "根据用户id查询所有已经评价的商品", notes = "根据用户id查询所有已经评价的商品")
    @PostMapping("/query")
    public JSONResult queryCommented(
           @RequestParam("userId") String userId,
           @RequestParam(name = "page",defaultValue = "1") Integer page,
           @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize
    ) {
        PagedGridResult pagedGridResult = centerCommentService.queryCommented(userId,page,pageSize);

        return JSONResult.successNeedData(pagedGridResult);
    }

}
