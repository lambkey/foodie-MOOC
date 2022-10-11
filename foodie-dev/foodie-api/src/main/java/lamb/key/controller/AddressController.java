package lamb.key.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lamb.key.pojo.bo.AddressBO;
import lamb.key.pojo.vo.AddressVO;
import lamb.key.service.AddressService;
import lamb.key.utils.JSONResult;
import lamb.key.utils.MobileEmailUtils;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/26 0:29
 * @Version 1.0
 */
@Api(value = "用户收货地址api", tags = {"用户收货地址api"})
@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "用户添加新地址",notes = "用户添加新地址")
    @PostMapping("/add")
    public JSONResult addAddress(@RequestBody AddressBO addressBO){

        if (StringUtils.isBlank(addressBO.getReceiver())){
            return JSONResult.FailureNeedMessage("用户名不为空");
        }
        if (addressBO.getReceiver().length()>12){
            return JSONResult.FailureNeedMessage("收货姓名不能太长");
        }
        if (StringUtils.isBlank(addressBO.getMobile())){
            return JSONResult.FailureNeedMessage("手机不能为空");
        }
        if (addressBO.getMobile().length() != 11){
            return JSONResult.FailureNeedMessage("手机号长度要为11位");
        }
        boolean b = MobileEmailUtils.checkMobileIsOk(addressBO.getMobile());
        if (!b){
            return JSONResult.FailureNeedMessage("收货人手机号格式不正确");
        }
        if (StringUtils.isBlank(addressBO.getProvince())||StringUtils.isBlank(addressBO.getCity())||StringUtils.isBlank(addressBO.getDistrict())||StringUtils.isBlank(addressBO.getDetail())){
            return JSONResult.FailureNeedMessage("收货地址不能为空");
        }
        addressService.addAddress(addressBO);

        return JSONResult.successWithoutData();
    }

    @ApiOperation(value = "删除用户地址",notes = "删除用户地址")
    @PostMapping("/delete")
    public JSONResult deleteAddress(@RequestParam("userId") String userId,
                                 @RequestParam("addressId") String addressId){
        if (StringUtils.isBlank(addressId)){
            return JSONResult.FailureNeedMessage("删除地址失败:addressId为空");
        }
        addressService.deleteAddress(userId,addressId);
        return JSONResult.successWithoutData();
    }

    @ApiOperation(value = "重新编辑用户的收货地址",notes = "重新编辑用户的收货地址")
    @PostMapping("/update")
    public JSONResult updateAddress(@RequestBody AddressBO addressBO){

        if (StringUtils.isBlank(addressBO.getAddressId())){
            return JSONResult.FailureNeedMessage("修改地址错误:AddressId不能为空");
        }
        if (StringUtils.isBlank(addressBO.getReceiver())){
            return JSONResult.FailureNeedMessage("用户名不为空");
        }
        if (addressBO.getReceiver().length()>12){
            return JSONResult.FailureNeedMessage("收货姓名不能太长");
        }
        if (StringUtils.isBlank(addressBO.getMobile())){
            return JSONResult.FailureNeedMessage("手机不能为空");
        }
        if (addressBO.getMobile().length() != 11){
            return JSONResult.FailureNeedMessage("手机号长度要为11位");
        }
        boolean b = MobileEmailUtils.checkMobileIsOk(addressBO.getMobile());
        if (!b){
            return JSONResult.FailureNeedMessage("收货人手机号格式不正确");
        }
        if (StringUtils.isBlank(addressBO.getProvince())||StringUtils.isBlank(addressBO.getCity())||StringUtils.isBlank(addressBO.getDistrict())||StringUtils.isBlank(addressBO.getDetail())){
            return JSONResult.FailureNeedMessage("收货地址不能为空");
        }
        addressService.updateAddress(addressBO);
        return JSONResult.successWithoutData();
    }

    @ApiOperation(value = "设置用户默认收货地址",notes = "设置用户默认收货地址")
    @PostMapping("/setDefalut")
    public JSONResult setDefaultAddress(
            @RequestParam("userId") String userId,
            @RequestParam("addressId") String addressId
    ){
        if (StringUtils.isBlank(userId)||StringUtils.isBlank(addressId)){
            return JSONResult.FailureNeedMessage("设置默认地址出错");
        }
        addressService.updateDefaultAddress(userId,addressId);

        return JSONResult.successWithoutData();
    }

    @ApiOperation(value = "回显用户地址",notes = "回显用户的地址")
    @PostMapping("/list")
    public JSONResult showAddress(@RequestParam("userId") String userId
    ){
        if (StringUtils.isBlank(userId)){
            return JSONResult.FailureNeedMessage("地址更新失败");
        }

        List<AddressVO> addressVOS = addressService.queryAddressByUserId(userId);

        return JSONResult.successNeedData(addressVOS);
    }
}
