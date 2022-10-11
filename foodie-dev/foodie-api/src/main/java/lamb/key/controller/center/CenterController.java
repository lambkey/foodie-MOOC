package lamb.key.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.pojo.Users;
import lamb.key.service.center.CenterUserService;
import lamb.key.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

/**
 * @author JoinYang
 * @date 2022/7/29 14:52
 * @Version 1.0
 */
@Api(value = "center - 用户中心",tags = {"用户中心展示的相应的接口"})
@RestController
@RequestMapping("/center")
public class CenterController {
    @Autowired
    private CenterUserService centerUserService;

    @ApiOperation(value = "获取用户信息",notes = "获取用户信息")
    @GetMapping("/userInfo")
    public JSONResult userInfo(@RequestParam("userId") String userId){
        Users users = centerUserService.queryUserInfo(userId);
        return JSONResult.successNeedData(users);
    }

}
