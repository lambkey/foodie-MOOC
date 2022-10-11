package lamb.key.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.config.COSPropertiesConfig;
import lamb.key.pojo.Users;
import lamb.key.pojo.center.bo.CenterUserBO;
import lamb.key.service.center.CenterUserService;
import lamb.key.utils.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/7/29 18:04
 * @Version 1.0
 */
@Api(value = "用户中心-用户信息api",tags = {"用户中心的用户信息api"})
@RestController
@RequestMapping("/userInfo")
public class CenterUserController {

    @Autowired
    private CenterUserService centerUserService;

    @Autowired
    private COSPropertiesConfig cosPropertiesConfig;

    @ApiOperation(value = "更新用户信息",notes = "更新用户信息api")
    @PostMapping("/update")
    public JSONResult updateUserInfo(
                    @RequestParam String userId,
                    @RequestBody @Valid CenterUserBO centerUserBO,
                    // @Valid验证的结果在 BindingResult
                    BindingResult bindingResult,
                    HttpServletResponse httpServletResponse,
                    HttpServletRequest httpServletRequest
                ){

        // 判断BindingResult中是否存在错误信息
        if (bindingResult.hasErrors()){
            Map<String, Object> errors = getErrors(bindingResult);
            return JSONResult.errorMap(errors);
        }


        Users users = centerUserService.updateUsersInfo(userId, centerUserBO);
        Users users1 = setNullForUsers(users);
        String s = JsonUtils.objectToJson(users1);
        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"user",s,true);

        // TODO 后续要改，增加令牌token,会整合进redis，分布式会话

        return JSONResult.successWithoutData();
    }

    private Map<String,Object> getErrors(BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, Object> map = new HashMap();
        for (FieldError fieldError : fieldErrors) {
            // 发生验证错误所对应的某一个属性
            String field = fieldError.getField();
            // 验证错误的信息
            String defaultMessage = fieldError.getDefaultMessage();
            map.put(field,defaultMessage);
        }
        return map;
    }

    @ApiOperation(value = "更新用户头像api",tags = "用户上传头像更新原来的头像")
    @PostMapping("/uploadFace")
    public JSONResult uploadFile(
           @RequestParam("userId") String userId,
           @RequestParam("file") MultipartFile file,
           HttpServletRequest httpServletRequest,
           HttpServletResponse httpServletResponse
    ){
        JSONResult<String> stringJSONResult = UploadUtils.doUpload(cosPropertiesConfig.getAccesskey(), cosPropertiesConfig.getSecretKey(), cosPropertiesConfig.getBucket(), cosPropertiesConfig.getBucketName(), cosPropertiesConfig.getPath(), file);
        if (stringJSONResult.getStatus() == -1){
            return JSONResult.FailureNeedMessage("头像上传失败,请联系管理员");
        }
        UploadMsg uploadMsg = (UploadMsg) stringJSONResult.getData();
        Users users = centerUserService.updateUserFace(userId, uploadMsg.getPath());
        Users users1 = setNullForUsers(users);
        String s = JsonUtils.objectToJson(users1);
        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"user",s,true);
        return JSONResult.successWithoutData();
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
}
