package lamb.key.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lamb.key.pojo.Users;
import lamb.key.pojo.bo.UserBO;
import lamb.key.service.UserService;
import lamb.key.utils.CookieUtils;
import lamb.key.utils.JSONResult;
import lamb.key.utils.JsonUtils;
import org.json.JSONObject;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JoinYang
 * @date 2022/7/14 15:59
 * @Version 1.0
 */
@Api(value = "注册登录",tags = "用于注册登录的相关接口") // swagger2
@RestController
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "用户退出登录",notes = "用户退出登录",httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(
                    @ApiParam(name = "userId",value = "用户id")
                    @RequestParam("userId") String userId,
                    HttpServletResponse response,
                    HttpServletRequest request){

        CookieUtils.deleteCookie(request,response,"user");

        // 退出会清空购物车
        // 分布式退出会清除用户数据

        return JSONResult.successWithoutData();
    }
    @ApiOperation(value = "用户登录",notes = "用户登录",httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO, HttpServletRequest request, HttpServletResponse response){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        if (StringUtils.isBlank(username)){
            return JSONResult.FailureNeedMessage("用户名不能为空");
        }
        // 1.实现登录
        Users users = userService.queryUserForLogin(username, password);
        if (users==null){
            return JSONResult.FailureNeedMessage("用户名或密码错误");
        }

        // 出于安全考虑 将用户部分敏感数据设置为null
        Users users1 = setNullForUsers(users);

        String s = JsonUtils.objectToJson(users1);

        // isEncode，cookie是否加密
        CookieUtils.setCookie(request,response,"user",s,true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据
        return JSONResult.successNeedData(s);
    }

    private Users setNullForUsers(Users users){
        users.setPassword(null);
        users.setMobile(null);
        users.setBirthday(null);
        users.setEmail(null);
        users.setCreatedTime(null);
        users.setUpdatedTime(null);
        return users;
    }

    @ApiOperation(value = "用户名是否存在",notes = "用户名是否存在",httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(
                        @ApiParam(name = "username",value = "用户名")
                        @RequestParam("username") String username){
        // 1.判断用户名是否为空
        if (StringUtils.isBlank(username)){
            return JSONResult.FailureNeedMessage("用户名不能为空");
        }

        // 2、查找注册的用户名是否存在
        // 如果没存在就true
        boolean b = userService.queryUsernameIsExist(username);
        if (b){
            return JSONResult.successWithoutData();
        }

        // 存在就true
        return JSONResult.FailureNeedMessage("用户名已存在");

    }

    @ApiOperation(value = "用户注册",notes = "用户注册",httpMethod = "POST")
    @PostMapping("/registry")
    public JSONResult registry(@RequestBody UserBO userBO,HttpServletRequest request,HttpServletResponse response){
        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword=userBO.getConfirmPassword();

        // 0.判断用户名和密码必须不为空
        if (StringUtils.isBlank(username)||
            StringUtils.isBlank(password)||
            StringUtils.isBlank(confirmPassword)
        ){
            return JSONResult.FailureNeedMessage("用户名和密码不能为空");
        }

        // 1.查询用户名是否存在
        boolean b = userService.queryUsernameIsExist(username);
        if (!b){
            return JSONResult.FailureNeedMessage("用户名已存在");
        }

        // 2.密码长度不能少于6位
        if (password.length()<6){
            return JSONResult.FailureNeedMessage("密码长度不能少于6位");
        }
        // 3.判断两次密码是否一致
        if (!password.equals(confirmPassword)){
            return JSONResult.FailureNeedMessage("两次密码输入不一致");
        }
        // 4.实现注册
        Users user = userService.createUser(userBO);

        // 出于安全考虑 将用户部分敏感数据设置为null
        Users users1 = setNullForUsers(user);

        String s = JsonUtils.objectToJson(users1);

        // isEncode，cookie是否加密
        CookieUtils.setCookie(request,response,"user",s,true);

        // TODO 生成用户token，存入redis会话
        // TODO 同步购物车数据

        return JSONResult.successWithoutData();
    }
}
