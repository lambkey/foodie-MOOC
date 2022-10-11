package lamb.key.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.pojo.bo.ShopCartBO;
import lamb.key.utils.JSONResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JoinYang
 * @date 2022/7/23 23:44
 * @Version 1.0
 */
@Api(value = "购物车相关接口",tags = {"购物车相关api"})
@RequestMapping("/shopcart")
@RestController
@Slf4j
public class ShopCartController {


    @ApiOperation(value = "从购物车中删除商品",notes = "从购物车中删除商品")
    @PostMapping("/del")
    public JSONResult delFromShopCart(
            @RequestParam("userId") String userId,
            @RequestParam("itemSpecId") String itemSpecId
    ){

        if (StringUtils.isBlank(userId)||StringUtils.isBlank(itemSpecId)){
            return JSONResult.FailureNeedMessage("参数不能为空");
        }
        // TODO 用户在页面删除购物车的商品数据，如果用户是出于登录状态的，则需要同步删除后端购物车的数据

        return JSONResult.successWithoutData();

    }

    @ApiOperation(value = "添加商品到购物车",notes = "添加商品到购物车")
    @PostMapping("/add")
    public JSONResult add(
            @RequestParam("userId") String userId,
            @RequestBody ShopCartBO shopCartBO,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse){

        if (StringUtils.isBlank(userId)){
            return JSONResult.FailureNeedMessage("您还没有登录~~");
        }

        System.out.println(shopCartBO);
        // TODO 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存


        return JSONResult.successWithoutData();
    }



}
