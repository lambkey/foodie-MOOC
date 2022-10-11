package lamb.key.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import lamb.key.pojo.Users;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表  Mapper 接口
 * </p>
 *
 * @author lamb
 * @since 2022-07-13
 */
public interface UsersMapper extends BaseMapper<Users> {
    Users queryUserForLogin(@Param("username") String username);

    void updateUserFace(@Param("face") String face,@Param("userId") String userId);
}
