package lamb.key.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.pojo.UserAddress;
import lamb.key.pojo.vo.AddressVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户地址表  Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
public interface UserAddressMapper extends BaseMapper<UserAddress> {
    List<AddressVO> queryAddressByUserId(@Param("userId") String userId);
    void updateDefault(@Param("userId") String userId);
    void updateDefaultToSelect(@Param("userId") String userId, @Param("addressId") String addressId);
}
