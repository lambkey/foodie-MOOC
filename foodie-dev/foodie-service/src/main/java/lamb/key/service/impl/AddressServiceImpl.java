package lamb.key.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import lamb.key.mapper.UserAddressMapper;
import lamb.key.pojo.UserAddress;
import lamb.key.pojo.bo.AddressBO;
import lamb.key.pojo.vo.AddressVO;
import lamb.key.service.AddressService;
import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoinYang
 * @date 2022/7/26 0:54
 * @Version 1.0
 */
@SuppressWarnings({"all"})
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<AddressVO> queryAddressByUserId(String userId) {
        List<AddressVO> addressVOS = userAddressMapper.queryAddressByUserId(userId);
        return addressVOS;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateDefaultAddress(String userId, String addressId) {
        userAddressMapper.updateDefault(userId);
        userAddressMapper.updateDefaultToSelect(userId,addressId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateAddress(AddressBO addressBO) {
        UpdateWrapper updateWrapper = new UpdateWrapper();

        updateWrapper.eq("id",addressBO.getAddressId());
        updateWrapper.eq("user_id",addressBO.getUserId());

        updateWrapper.set("receiver",addressBO.getReceiver());
        updateWrapper.set("mobile",addressBO.getMobile());
        updateWrapper.set("province",addressBO.getProvince());
        updateWrapper.set("city",addressBO.getCity());
        updateWrapper.set("district",addressBO.getDistrict());
        updateWrapper.set("detail",addressBO.getDetail());
        updateWrapper.set("updated_time",new Date());

        int update = userAddressMapper.update(null, updateWrapper);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteAddress(String userId, String addressId) {
        Map map = new HashMap();
        map.put("id",addressId);
        map.put("user_id",userId);
        userAddressMapper.deleteByMap(map);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<UserAddress> queryAll(String userId) {
        Map map = new HashMap();
        map.put("user_id",userId);
        List<UserAddress> list = userAddressMapper.selectByMap(map);
        return list;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public void addAddress(AddressBO addressBO) {
        // 1.判断用户是否已经添加地址,如果没有则新增为‘默认地址’
        Integer isDefault=0;
        List<UserAddress> userAddresses = this.queryAll(addressBO.getUserId());
        if (userAddresses == null || userAddresses.isEmpty()||userAddresses.size()==0){
            isDefault = 1;
        }

        // 2.保存地址到数据库
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressBO,userAddress);
        String addressId = sid.nextShort();
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddress.setId(addressId);
        userAddressMapper.insert(userAddress);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public UserAddress queryOrderAddress(String userId, String addressId) {

        Map<String, Object> map = new HashMap<>();
        map.put("id",addressId);
        map.put("user_id",userId);
        List<UserAddress> userAddresses = userAddressMapper.selectByMap(map);

        return userAddresses.get(0);
    }
}
