package lamb.key.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.pojo.OrderStatus;
import lamb.key.pojo.bo.SubmitOrderBO;
import lamb.key.pojo.vo.MerchantOrdersVO;
import lamb.key.pojo.vo.OrderVO;
import lamb.key.service.OrderService;
import lamb.key.utils.JSONResult;
import lamb.key.utils.enums.OrderStatusEnum;
import lamb.key.utils.enums.PayMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JoinYang
 * @date 2022/7/26 23:11
 * @Version 1.0
 */
@Api(value = "订单api",tags = {"订单信息api"})
@RestController
@RequestMapping("/orders")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "用户下单",notes = "用户下单")
    @PostMapping("/create")
    public JSONResult create(@RequestBody SubmitOrderBO submitOrderBO,
                             HttpServletRequest request,
                             HttpServletResponse response){

        if (submitOrderBO.getPayMethod() != PayMethod.WEIXIN.type
                &&
            submitOrderBO.getPayMethod() != PayMethod.ALIPAY.type
        ){
            return JSONResult.FailureNeedMessage("支付方式不支持");
        }

        // 1.创建订单
        OrderVO order = orderService.createOrder(submitOrderBO);
        String orderId = order.getOrderId();
        // 2.创建订单以后,移除购物车中已经结算(已提交)的商品
        /*
        * 1001->剩下
        * 1002->剩下
        * 1003->用户购买
        * 1004->用户购买
        * */
        // TODO 整合redis之后,完善购物车中的已经结算商品清除,并且同步到前端的cookie中
        // CookieUtils.setCookie(request,response,"shopcart","",true);

        // 3.向支付中心发送当前订单，用于保存支付中心的订单数据
        MerchantOrdersVO merchantOrdersVO = order.getMerchantOrdersVO();
        // 为了方便测试，所有的支付都改为1分钱
        merchantOrdersVO.setAmount(1);
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("imoocUserId","10010");
        httpHeaders.add("password","123456");

        HttpEntity<MerchantOrdersVO> httpEntity = new HttpEntity<>(merchantOrdersVO,httpHeaders);
        ResponseEntity<JSONResult> jsonResultResponseEntity = restTemplate.postForEntity(paymentUrl, httpEntity, JSONResult.class);

        JSONResult jsonResult = jsonResultResponseEntity.getBody();
        if (jsonResult.getStatus() != 200){
            return JSONResult.FailureNeedMessage("支付中心订单创建失败,请联系管理员!");
        }

        return JSONResult.successNeedData(orderId);
    }

    // 第三方回调地址
    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId){

        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_DELIVER.type);

        return HttpStatus.OK.value();
    }

    // 获取订单状态信息,如果订单状态为20就表示订单支付成功
    @PostMapping("/getPaidOrderInfo")
    public JSONResult getPaidOrderInfo(String orderId){
        OrderStatus orderStatus = orderService.queryOrderStatus(orderId);
        return JSONResult.successNeedData(orderStatus);
    }
}
