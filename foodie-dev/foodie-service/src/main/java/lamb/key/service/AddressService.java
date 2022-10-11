package lamb.key.service;

import lamb.key.pojo.UserAddress;
import lamb.key.pojo.bo.AddressBO;
import lamb.key.pojo.vo.AddressVO;

import java.util.List;

/**
 * @author JoinYang
 * @date 2022/7/26 0:49
 * @Version 1.0
 */
public interface AddressService {
    /*
    * 回显用户地址信息
    * */
    public List<AddressVO> queryAddressByUserId(String userId);

    /*
    * 设置用户默认地址
    * */
    public void updateDefaultAddress(String userId,String addressId);

    /*
    * 编辑用户地址
    * */
    public void updateAddress(AddressBO addressBO);

    /*
    * 删除用户地址信息
    * */
    public void deleteAddress(String userId,String addressId);

    /*
    * 添加用户地址
    * */
    public List<UserAddress> queryAll(String userId);
    public void addAddress(AddressBO addressBO);

    /*
    * 根据用户id,地址id,查询订单地址
    * */
    public UserAddress queryOrderAddress(String userId,String addressId);
}
